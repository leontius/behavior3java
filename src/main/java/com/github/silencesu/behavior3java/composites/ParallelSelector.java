package com.github.silencesu.behavior3java.composites;

import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Composite;
import com.github.silencesu.behavior3java.core.Tick;

/**
 * 一True则返回True，全False才返回False
 *
 * @author leontius
 */
public class ParallelSelector extends Composite {
    @Override
    public B3Status onTick(Tick tick) {
        boolean flag = true;
        for (int i = 0; i < this.getChildCount(); i++) {
            B3Status status = this.getChild(i).execute(tick);
            if (status == B3Status.SUCCESS) {
                return B3Status.SUCCESS;
            } else if (status != B3Status.FAILURE) {
                flag = false;
            }
        }

        if (flag) {
            return B3Status.FAILURE;
        }
        return B3Status.RUNNING;
    }
}
