package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.annotations.ExtendNode;
import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.config.BTTreeCfg;
import com.github.silencesu.behavior3java.config.DefaultNodes;
import com.github.silencesu.behavior3java.constant.B3Const;
import com.github.silencesu.behavior3java.constant.B3Status;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 行为树
 *
 * @author SilenceSu
 * @version $Id: $Id
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Getter
@Setter
@Slf4j
public class BehaviorTree {

    private String id = UUID.randomUUID().toString().replaceAll("-", "");
    private String titile;
    private String description;
    private Map<String, Object> properties = new HashMap<>();

    private BaseNode root;

    /**
     * project 引用对象
     */
    private BehaviorTreeProject projectInfo;


    /**
     * 失败数量
     */
    private AtomicInteger failedCount = new AtomicInteger(0);

    /**
     * 线程池
     */
    private final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 30, 30L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(100), new B3ThreadFactory(), new B3RejectedExecutionHandler());

    /**
     * <p>load.</p>
     *
     * @param cfg a {@link com.github.silencesu.behavior3java.config.BTTreeCfg} object.
     */
    public void load(BTTreeCfg cfg) {
        Map<String, Class<? extends BaseNode>> hash = new HashMap<>();
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .scan()) {
            ClassInfoList classInfos = scanResult.getClassesWithAnnotation(ExtendNode.class);
            for (ClassInfo classInfo : classInfos) {
                Class<? extends BaseNode> clazz = (Class<? extends BaseNode>) classInfo.loadClass();
                hash.put(clazz.getSimpleName(), clazz);
            }
        }
        load(cfg, hash);
    }


    /**
     * <p>load.</p>
     *
     * @param cfg         a {@link com.github.silencesu.behavior3java.config.BTTreeCfg} object.
     * @param extendNodes a {@link java.util.Map} object.
     */
    public void load(BTTreeCfg cfg, Map<String, Class<? extends BaseNode>> extendNodes) {

        this.titile = cfg.getTitle();
        this.description = cfg.getDescription();
        this.properties = cfg.getProperties();

        Map<String, Class<? extends BaseNode>> nodeMaps = new HashMap<>(DefaultNodes.get());
        //加载扩展nodes
        if (extendNodes != null && extendNodes.size() > 0) {
            nodeMaps.putAll(extendNodes);
        }


        Map<String, BaseNode> nodes = new HashMap<>();
        //create  nodes
        for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {

            String id = nodeEntry.getKey();

            BTNodeCfg nodeCfg = nodeEntry.getValue();

            BaseNode node = null;

            //检查是或否为子树
            if (nodeCfg.getCategory() != null && nodeCfg.getCategory().equals(B3Const.SUBTREE)) {
                node = new SubTree();
            } else {
                //普通结点加载
                Class<? extends BaseNode> clazz = nodeMaps.get(nodeCfg.getName());
                if (clazz != null) {
                    try {
                        node = clazz.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }


            if (node == null) {
                log.error("create node erro:{}", nodeCfg.getName());
                break;
            }

            node.initialize(nodeCfg);

            if (projectInfo != null) {
                node.setProjectInfo(projectInfo);
            }

            nodes.put(id, node);

        }


        /**
         * connect the nodes
         */
        for (Map.Entry<String, BTNodeCfg> nodeEntry : cfg.getNodes().entrySet()) {
            BaseNode node = nodes.get(nodeEntry.getKey());
            BTNodeCfg nodeCfg = nodeEntry.getValue();

            if (node.getCategory().equals(B3Const.COMPOSITE) && nodeCfg.getChildren() != null) {
                for (String cid : nodeCfg.getChildren()) {
                    IComposite comp = (IComposite) node;
                    comp.addChild(nodes.get(cid));
                }

            } else if (node.getCategory().equals(B3Const.DECORATOR) && nodeCfg.getChild().length() > 0) {
                IDecorator deco = (IDecorator) node;
                deco.setChild(nodes.get(nodeCfg.getChild()));
            }


        }


        //设置root节点
        this.root = nodes.get(cfg.getRoot());

    }


    /**
     * <p>tick.</p>
     *
     * @param t          a T object.
     * @param blackboard a {@link com.github.silencesu.behavior3java.core.Blackboard} object.
     * @param <T>        a T object.
     * @return a {@link com.github.silencesu.behavior3java.constant.B3Status} object.
     */
    public <T> B3Status tick(T t, Blackboard blackboard) {

        if (blackboard == null) {
            log.error("The blackboard parameter is obligatory and must be an instance of b3.Blackboard");
            return B3Status.ERROR;
        }

        /* CREATE A TICK OBJECT */
        Tick tick = new Tick();
        tick.setTarget(t);
        tick.setBlackboard(blackboard);
        tick.setTree(this);


        B3Status status = this.root.run(tick);

        List<BaseNode> lastOpenNodes = blackboard.getTreeData(this.id).openNodes;

        List<BaseNode> currOpenNodes = tick.getOpenNodes();

        // does not close if it is still open in this tick
        int start = 0;
        for (int i = 0; i < (Math.min(lastOpenNodes.size(), currOpenNodes.size())); i++) {
            start = i + 1;
            if (lastOpenNodes.get(i) != currOpenNodes.get(i)) {
                break;
            }
        }

        // close the nodes
        for (int i = lastOpenNodes.size() - 1; i >= start; i--) {
            lastOpenNodes.get(i).close(tick);
        }

        /* POPULATE BLACKBOARD */
        blackboard.getTreeData(this.id).openNodes = currOpenNodes;
        blackboard.SetTree("nodeCount", tick.getNodeCount(), this.id);


        return status;
    }

    /**
     * 线程工厂类
     * 生成的线程名词前缀、是否为守护线程以及优先级等
     */
    private static class B3ThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = BehaviorTree.class.getSimpleName() + "-" + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }


    /**
     * 拒绝策略对象
     */
    private static class B3RejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
