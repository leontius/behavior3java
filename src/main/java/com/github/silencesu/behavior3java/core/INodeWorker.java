package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.constant.B3Status;

/**
 * <p>INodeWorker interface.</p>
 *
 * @author SilenceSu
 * @version $Id: $Id
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 */
public interface INodeWorker {

    /**
     * <p>onEnter.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void onEnter(Tick tick);

    /**
     * <p>onOpen.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void onOpen(Tick tick);

    /**
     * <p>onTick.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     * @return a {@link com.github.silencesu.behavior3java.constant.B3Status} object.
     */
    B3Status onTick(Tick tick);

    /**
     * <p>onClose.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void onClose(Tick tick);

    /**
     * <p>onExit.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     */
    void onExit(Tick tick);

}
