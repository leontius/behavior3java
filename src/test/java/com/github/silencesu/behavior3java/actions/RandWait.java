package com.github.silencesu.behavior3java.actions;

import com.github.silencesu.behavior3java.annotations.ExtendNode;
import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Action;
import com.github.silencesu.behavior3java.core.Tick;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendNode
public class RandWait extends Action {

    private String timeMini;
    private String timeMax;

    @Override
    public void initialize(BTNodeCfg nodeCfg) {
        super.initialize(nodeCfg);
        timeMini = nodeCfg.getProperties().get("timemini");
        timeMax = nodeCfg.getProperties().get("timemax");
    }

    @Override
    public B3Status onTick(Tick tick) {
        log.info("RandMove Action: timeMini={}, timeMax={}", timeMini, timeMax);
        return B3Status.FAILURE;
    }
}
