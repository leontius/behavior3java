package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.constant.B3Status;

/**
 * 包装类
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/4.
 * @version $Id: $Id
 */
public interface IBaseWrapper {

    /**
     * <p>run.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     * @return a {@link com.github.silencesu.behavior3java.constant.B3Status} object.
     */
    B3Status run(Tick tick);

    /**
     * <p>enter.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void enter(Tick tick);

    /**
     * <p>open.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void open(Tick tick);

    /**
     * <p>tick.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     * @return a {@link com.github.silencesu.behavior3java.constant.B3Status} object.
     */
    B3Status tick(Tick tick);

    /**
     * <p>close.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void close(Tick tick);

    /**
     * <p>exit.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void exit(Tick tick);
}
