package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.config.BTNodeCfg;
import com.github.silencesu.behavior3java.constant.B3Status;

/**
 * <p>INode interface.</p>
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
public interface INode extends IBaseWrapper{


    /**
     * <p>initialize.</p>
     *
     * @param nodeCfg a {@link com.github.silencesu.behavior3java.config.BTNodeCfg} object.
     */
    void initialize(BTNodeCfg nodeCfg);

    /**
     * <p>getCategory.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getCategory();

    /**
     * <p>execute.</p>
     *
     * @param tick a {@link com.github.silencesu.behavior3java.core.Tick} object.
     * @return a {@link com.github.silencesu.behavior3java.constant.B3Status} object.
     */
    B3Status execute(Tick tick);

    /**
     * <p>getName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getName();

    /**
     * <p>getTitle.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    String getTitle();


}
