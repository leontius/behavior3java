package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.config.BTTreeCfg;
import com.github.silencesu.behavior3java.config.BTTreeProjectCfg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 行为树工程
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
@Data
@Slf4j
public class BehaviorTreeProject {

    /**
     * 工程内的树
     */
    private Map<String, BehaviorTree> titleTreeMap = new HashMap<>();

    private Map<String, BehaviorTree> idTreeMap = new HashMap<>();

    /**
     * <p>findBTTreeByTitle.</p>
     *
     * @param treeTitle a {@link java.lang.String} object.
     * @return a {@link com.github.silencesu.behavior3java.core.BehaviorTree} object.
     */
    public BehaviorTree findBTTreeByTitle(String treeTitle) {
        return titleTreeMap.get(treeTitle.trim());
    }

    /**
     * <p>findBTTreeById.</p>
     *
     * @param id a {@link java.lang.String} object.
     * @return a {@link com.github.silencesu.behavior3java.core.BehaviorTree} object.
     */
    public BehaviorTree findBTTreeById(String id) {
        return idTreeMap.get(id);
    }


    /**
     * 初始化工程数据数据
     *
     * @param projectCfg  工程配置
     * @param extendNodes 扩展结点
     */
    public void initProject(BTTreeProjectCfg projectCfg, Map<String, Class<? extends BaseNode>> extendNodes) {
        for (BTTreeCfg treeCfg : projectCfg.getData().getTrees()) {
            BehaviorTree behaviorTree = new BehaviorTree();
            behaviorTree.setProjectInfo(this);
            if (extendNodes != null && !extendNodes.isEmpty()) {
                behaviorTree.load(treeCfg, extendNodes);
            } else {
                behaviorTree.load(treeCfg);
            }
            titleTreeMap.put(treeCfg.getTitle(), behaviorTree);
            idTreeMap.put(treeCfg.getId(), behaviorTree);
        }
    }
}
