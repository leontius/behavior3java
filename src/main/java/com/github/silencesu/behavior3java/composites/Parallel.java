package com.github.silencesu.behavior3java.composites;

import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Composite;
import com.github.silencesu.behavior3java.core.Tick;

/**
 *一False则返回False，全True才返回True
 *
 * @author leontius
 */
public class Parallel extends Composite {
    @Override
    public B3Status onTick(Tick tick) {
        boolean flag = true;
        for (int i = 0; i < this.getChildCount(); i++) {
            B3Status status = this.getChild(i).execute(tick);
            if (status == B3Status.FAILURE) {
                return B3Status.FAILURE;
            } else if (status != B3Status.SUCCESS) {
                flag = false;
            }
        }

        if (flag) {
            return B3Status.SUCCESS;
        }
        return B3Status.RUNNING;
    }
}
