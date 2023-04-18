package com.github.silencesu.behavior3java.actions;

import com.github.silencesu.behavior3java.annotations.ExtendNode;
import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Action;
import com.github.silencesu.behavior3java.core.Tick;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ExtendNode
public class FindItem extends Action {

    private String etype;
    private String range;
    private String index;

    @Override
    public void initialize(BTNodeCfg nodeCfg) {
        super.initialize(nodeCfg);
        etype = nodeCfg.getProperties().get("etype");
        range = nodeCfg.getProperties().get("range");
        index = nodeCfg.getProperties().get("index");
    }

    @Override
    public B3Status onTick(Tick tick) {
        log.info("FindItem Action: eytpe={}, range={}, index={}", etype, range, index);
        return B3Status.SUCCESS;
    }
}
