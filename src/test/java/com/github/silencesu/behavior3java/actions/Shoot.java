package com.github.silencesu.behavior3java.actions;

import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Action;
import com.github.silencesu.behavior3java.core.Tick;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Shoot extends Action {

    @Override
    public B3Status onTick(Tick tick) {
        log.info("Shoot Action");
        return B3Status.FAILURE;
    }
}
