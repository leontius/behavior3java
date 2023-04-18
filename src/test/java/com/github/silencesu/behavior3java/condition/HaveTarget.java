package com.github.silencesu.behavior3java.condition;

import com.github.silencesu.behavior3java.annotations.ExtendNode;
import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Condition;
import com.github.silencesu.behavior3java.core.Tick;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
@ExtendNode
public class HaveTarget extends Condition {

    private String index;

    @Override
    public void initialize(BTNodeCfg nodeCfg) {
        super.initialize(nodeCfg);
        index = nodeCfg.getProperties().get("index");
    }

    @Override
    public B3Status onTick(Tick tick) {
        log.info("HaveTarget Action: index:{}", index);
        if ("item".equals(index)) {
            return B3Status.SUCCESS;
        }
        return B3Status.FAILURE;
    }
}
