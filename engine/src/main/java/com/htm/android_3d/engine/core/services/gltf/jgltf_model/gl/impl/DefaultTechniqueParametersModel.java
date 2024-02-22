/*
 * www.javagl.de - JglTF
 *
 * Copyright 2015-2017 Marco Hutter - http://www.javagl.de
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.htm.android_3d.engine.core.services.gltf.jgltf_model.gl.impl;

import com.htm.android_3d.engine.core.services.gltf.jgltf_model.NodeModel;
import com.htm.android_3d.engine.core.services.gltf.jgltf_model.gl.Semantic;
import com.htm.android_3d.engine.core.services.gltf.jgltf_model.gl.TechniqueParametersModel;

/**
 * Implementation of a {@link TechniqueParametersModel}
 */
public class DefaultTechniqueParametersModel implements TechniqueParametersModel
{
    /**
     * The type
     */
    private final int type;
    
    /**
     * The count
     */
    private final int count;
    
    /**
     * The {@link Semantic} semantic
     */
    private final String semantic;
    
    /**
     * The value
     */
    private final Object value;
    
    /**
     * The {@link NodeModel}
     */
    private final NodeModel nodeModel;
    
    /**
     * Default constructor
     * 
     * @param type The type
     * @param count The count
     * @param semantic The {@link Semantic}
     * @param value The value
     * @param nodeModel The {@link NodeModel}
     */
    public DefaultTechniqueParametersModel(
        int type, int count, String semantic, 
        Object value, NodeModel nodeModel)
    {
        this.type = type;
        this.count = count;
        this.semantic = semantic;
        this.value = value;
        this.nodeModel = nodeModel;
    }

    @Override
    public int getType()
    {
        return type;
    }
    
    @Override
    public int getCount()
    {
        return count;
    }

    @Override
    public String getSemantic()
    {
        return semantic;
    }

    @Override
    public Object getValue()
    {
        return value;
    }
    
    @Override
    public NodeModel getNodeModel()
    {
        return nodeModel;
    }

}
