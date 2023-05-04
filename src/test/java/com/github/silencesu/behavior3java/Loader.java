package com.github.silencesu.behavior3java;

import com.alibaba.fastjson.JSON;
import com.github.silencesu.behavior3java.annotations.ExtendNode;
import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.BehaviorTree;
import com.github.silencesu.behavior3java.core.BehaviorTreeProject;
import com.github.silencesu.behavior3java.core.Blackboard;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import io.github.classgraph.ClassInfoList;
import io.github.classgraph.ScanResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 测试用例
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
@Slf4j
public class Loader {

    @Test
    public void loadTree() {
        String confJson = Loader.class.getResource("/").getPath() + "tree.json";
        BehaviorTree behaviorTree = B3Loader.loadB3Tree(confJson);
        Blackboard blackboard = new Blackboard();
        behaviorTree.tick(new Object(), blackboard);
    }

    @Test
    public void loadProject() {
        String confJson = Loader.class.getResource("/").getPath() + "project.b3";
        BehaviorTreeProject behaviorTreeProject = B3Loader.loadB3Project(confJson);
        Blackboard blackboard = new Blackboard();
        BehaviorTree behaviorTree = behaviorTreeProject.findBTTreeByTitle("b3");
        for (;;) {
            B3Status status = behaviorTree.tick(new Object(), blackboard);
            log.info("status {}", status);
            if (B3Status.SUCCESS.equals(status)) {
                log.info("{}", status);
                break;
            }
        }
    }

    @Test
    public void scan() {
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .scan()) {
            ClassInfoList classInfos = scanResult.getClassesWithAnnotation(ExtendNode.class);
            for (ClassInfo classInfo : classInfos) {
                Class<?> hpLess = classInfo.loadClass();
                log.info("{}", JSON.toJSONString(hpLess.getSimpleName()));
            }
        }
    }
}
