package com.github.silencesu.behavior3java.actions;

import com.github.silencesu.behavior3java.annotations.ExtendNode;
import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Action;
import com.github.silencesu.behavior3java.core.Tick;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

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
        if (this.finished()) {
            return this.getCurrentStatus();
        }
        this.runAsyncAction(() -> {
            try {
                log.info("执行 FindItem 行为");
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, tick.getTree().getThreadPool());
        log.info("FindItem Action: eytpe={}, range={}, index={} tick:{}", etype, range, index, tick.getTarget());
        return B3Status.RUNNING;
    }
}
