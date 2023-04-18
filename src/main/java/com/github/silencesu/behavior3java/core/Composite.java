package com.github.silencesu.behavior3java.core;

import com.github.silencesu.behavior3java.constant.B3Const;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 组合节点
 *
 * @author SilenceSu
 * @Email Silence.Sx@Gmail.com
 * Created by Silence on 2019/3/2.
 * @version $Id: $Id
 */
public abstract class Composite extends BaseNode implements IComposite {

    private List<BaseNode> children = new ArrayList<>();

    /** {@inheritDoc} */
    @Override
    public int getChildCount() {
        return children.size();
    }

    /** {@inheritDoc} */
    @Override
    public BaseNode getChild(int index) {
        return children.get(index);
    }

    /** {@inheritDoc} */
    @Override
    public List<BaseNode> getChildList() {
        return this.children;
    }

    /** {@inheritDoc} */
    @Override
    public void shuffle() {
        Collections.shuffle(this.children);
    }

    /** {@inheritDoc} */
    @Override
    public void addChild(BaseNode baseNode) {
        children.add(baseNode);
    }

    /** {@inheritDoc} */
    @Override
    public String getCategory() {
        return B3Const.COMPOSITE;
    }

}
