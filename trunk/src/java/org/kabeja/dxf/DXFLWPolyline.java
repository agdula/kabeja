/*
 * Created on 13.04.2005
 *
 */
package org.kabeja.dxf;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 */
public class DXFLWPolyline extends DXFPolyline {
    private double constantwidth = 0.0;
    private double elevation = 0.0;

    public DXFLWPolyline() {
    }

    public void setConstantWidth(double width) {
        this.constantwidth = width;
    }

    public double getContstantWidth() {
        return this.constantwidth;
    }





    /* (non-Javadoc)
     * @see de.miethxml.kabeja.dxf.DXFEntity#getType()
     */
    public String getType() {
        return DXFConstants.ENTITY_TYPE_LWPOLYLINE;
    }
}
