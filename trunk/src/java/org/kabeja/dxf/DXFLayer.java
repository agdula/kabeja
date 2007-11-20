/*
   Copyright 2005 Simon Mieth

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package org.kabeja.dxf;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 *
 */
public class DXFLayer  {
    private Hashtable entities = new Hashtable();
    private String name = "";
    private int color = 7;
    private DXFDocument doc;
    private Bounds bounds = new Bounds();
    private String ltype = "";
    private int flags = 0;
    private int lineWeight=0;
    private String plotStyle="";
    

    public DXFLayer() {
    }

    /**
     * @return Returns the name.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    public void addDXFEntity(DXFEntity entity) {
    	entity.setDXFDocument(this.doc);
        if (entities.containsKey(entity.getType())) {
            ((ArrayList) entities.get(entity.getType())).add(entity);
        } else {
            ArrayList list = new ArrayList();

            list.add(entity);
            entities.put(entity.getType(), list);
        }
    }

    public void removeDXFEntity(DXFEntity entity){
    	  if (entities.containsKey(entity.getType())) {
             ArrayList list = (ArrayList) entities.get(entity.getType());
             list.remove(entity);
             if(list.isEmpty()){
            	 entities.remove(entity.getType());
             }
          } 
    }
    
    
    public void setDXFDocument(DXFDocument doc) {
        this.doc = doc;
    }



    public Bounds getBounds() {
        Enumeration e = entities.elements();

        while (e.hasMoreElements()) {
            ArrayList list = (ArrayList) e.nextElement();

            Iterator i = list.iterator();

            while (i.hasNext()) {
                DXFEntity entity = (DXFEntity) i.next();
                Bounds b = entity.getBounds();

                if (b.isValid()) {
                    bounds.addToBounds(b);
                }
            }
        }

        return bounds;
    }

    /**
     * Returns the list of the DXFenetities of the Type or null.
     *
     * @param type
     * @return List or null
     */
    public List getDXFEntities(String type) {
        if (entities.containsKey(type)) {
            return (ArrayList) entities.get(type);
        }

        return null;
    }

    public boolean hasDXFEntities(String type) {
        return entities.containsKey(type);
    }

    /**
     *
     * @return a iterator over all entity types of this layer
     */
    public Iterator getDXFEntityTypeIterator() {
        return entities.keySet().iterator();
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setLineType(String ltype) {
        this.ltype = ltype;
    }

    public String getLineType() {
        return ltype;
    }

    /**
     * @return Returns the flags.
     */
    public int getFlags() {
        return flags;
    }

    /**
     * @param flags The flags to set.
     */
    public void setFlags(int flags) {
        this.flags = flags;
    }

    public boolean isVisible() {
        return color >= 0;
    }

    public boolean isFrozen() {
        return ((this.flags & 1) == 1);
    }

	public int getLineWeight() {
		return lineWeight;
	}

	public void setLineWeight(int lineWeight) {
		this.lineWeight = lineWeight;
	}

	public String getPlotStyle() {
		return plotStyle;
	}

	public void setPlotStyle(String plotStyle) {
		this.plotStyle = plotStyle;
	}
}