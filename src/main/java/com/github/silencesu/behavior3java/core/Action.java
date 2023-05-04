package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.constant.B3Const;
import com.github.silencesu.behavior3java.constant.B3Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
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
    private final static Logger log = LoggerFactory.getLogger(Action.class);
    private B3Status currentStatus = B3Status.RUNNING;
    private final AtomicInteger failedCount = new AtomicInteger(0);
    private final AtomicInteger nodeFutureCount = new AtomicInteger(0);

    public void setCurrentStatus(B3Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public B3Status getCurrentStatus() {
        return currentStatus;
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

    public Boolean finished() {
        if (!B3Status.RUNNING.equals(this.getCurrentStatus())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 执行异步行为
     *
     * @param runnable           线程方法
     * @param threadPoolExecutor 线程池
     */
    public void runAsyncAction(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        if (this.addFutureCount() == 1) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(runnable, threadPoolExecutor);
            // 设置异常情况
            future.exceptionally(e -> {
                failedCount.incrementAndGet();
                this.setCurrentStatus(B3Status.ERROR);
                log.error("Action [" + this.getClass().getSimpleName() + "] Failed..." + e);
                // 释放异步标记
                this.setFutureCountZero();
                return null;
            });
            // 任务结束之后
            future.whenComplete((v, throwable) -> {
                this.setCurrentStatus(B3Status.SUCCESS);
                // 释放异步标记
                this.setFutureCountZero();
            });
        }
    }
}
