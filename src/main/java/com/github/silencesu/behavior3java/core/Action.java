package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.constant.B3Const;
import com.github.silencesu.behavior3java.constant.B3Status;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 行为节点  叶节点
 *
 * @author SilenceSu
 * @version $Id: $Id
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public abstract class Action extends BaseNode implements IAction {

    private B3Status status;
    private AtomicInteger nodeFutureCount = new AtomicInteger(0);

    public void setStatus(B3Status status) {
        this.status = status;
    }

    public B3Status getStatus() {
        return status;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getCategory() {
        return B3Const.ACTION;
    }

    public int addFutureCount() {
        return this.nodeFutureCount.incrementAndGet();
    }

    public void setFutureCountZero() {
        this.nodeFutureCount.getAndSet(0);
    }
}
