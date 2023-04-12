package com.github.silencesu.behavior3java.constant;

/**
 * 行为树状态
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public enum B3Status {
    /**
     * 成功
     */
    SUCCESS(1),
    /**
     * 失败
     */
    FAILURE(2),
    /**
     * 运行中
     */
    RUNNING(3),
    /**
     * 异常
     */
    ERROR(4),
    ;

    private final int value;

    B3Status(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }
}
