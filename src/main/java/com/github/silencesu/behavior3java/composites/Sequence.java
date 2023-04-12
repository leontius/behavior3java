package com.github.silencesu.behavior3java.composites;

import com.github.silencesu.behavior3java.constant.B3Status;
import com.github.silencesu.behavior3java.core.Composite;
import com.github.silencesu.behavior3java.core.Tick;

/**
 * 顺序节点，当执行本类型Node时，它将从begin到end迭代执行自己的Child Node：
 * 如遇到一个Child Node执行后返回False，那停止迭代， 本Node向自己的Parent Node也返回False；
 * 否则所有Child Node都返回True， 那本Node向自己的Parent Node返回True。
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public class Sequence extends Composite {
    @Override
    public B3Status onTick(Tick tick) {
        for (int i = 0; i < this.getChildCount(); i++) {
            B3Status status = this.getChild(i).execute(tick);
            if (status != B3Status.SUCCESS) {
                return status;
            }
        }
        return B3Status.SUCCESS;
    }
}
