/*******************************************************************************
 * Copyright 2010 Simon Mieth
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package org.kabeja.entities;

import java.util.ArrayList;
import java.util.List;

import org.kabeja.common.Type;
import org.kabeja.math.Bounds;
import org.kabeja.math.TransformContext;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 */
public class Region extends Entity {
    protected List<String> acisData = new ArrayList<String>();

    /**
     *
     *
     * @return always invalid bounds
     */
    public Bounds getBounds() {
        Bounds bounds = new Bounds();
        bounds.setValid(false);

        return bounds;
    }

    /**
     *
     *
     * @see org.kabeja.entities.Entity#getType()
     */
    public Type<?> getType() {
        return Type.TYPE_REGION;
    }

    /**
     * The ACIS commands as a list of lines
     *
     * @return the list
     */
    public List<String> getACISDATA() {
        return acisData;
    }

    public void appendACISDATA(String data) {
        acisData.add(data);
    }

    /**
     * This entity is only a container of ACIS data.
     *
     * @return always 0
     */
    public double getLength() {
        return 0;
    }
    
    
    /**
     * Not implemented yet
     */
    
    public void transform(TransformContext context) {
        
    }
}
