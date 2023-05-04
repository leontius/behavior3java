package com.github.silencesu.behavior3java.condition;

import com.github.silencesu.behavior3java.annotations.ExtendNode;
import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Blackboard;
import com.github.silencesu.behavior3java.core.Condition;
import com.github.silencesu.behavior3java.core.Tick;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendNode
public class HpLess extends Condition {

    private int rate;

    @Override
    public void initialize(BTNodeCfg nodeCfg) {
        super.initialize(nodeCfg);
        rate = Integer.parseInt(nodeCfg.getProperties().get("rate"));
    }

    @Override
    public void onOpen(Tick tick) {
        super.onOpen(tick);
    }

    @Override
    public B3Status onTick(Tick tick) {
        log.info("HpLess rate = {}", rate);
        if (rate == 1) {
            tick.setTarget("test");
            Blackboard blackboard = tick.getBlackboard();
            String val = blackboard.getParam("test", tick.getTree().getId(), "test");
            log.info("blackboard get val: {}", val);
            return B3Status.SUCCESS;
        }
        return B3Status.FAILURE;
    }
}
