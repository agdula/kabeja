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
package org.kabeja.math;

import org.kabeja.util.Constants;


/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 *
 *
 */
public class Point3D {
    protected double x = 0.0;
    protected double y = 0.0;
    protected double z = 0.0;
   

    public Point3D() {
       
    }

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
       
    }

    /**
     * @return Returns the x.
     */
    public double getX() {
        return x;
    }

    /**
     * @param x
     *            The x to set.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return Returns the y.
     */
    public double getY() {
        return y;
    }

    /**
     * @param y
     *            The y to set.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return Returns the z.
     */
    public double getZ() {
        return z;
    }

    /**
     * @param z
     *            The z to set.
     */
    public void setZ(double z) {
        this.z = z;
    }

    public String toString() {
        return super.toString() + "[" + this.x + "," + this.y + "," + this.z +
        "]";
    }

    public boolean equals(Object obj) {
        if (obj instanceof Point3D) {
            Point3D p = (Point3D) obj;
            double d = Constants.POINT_CONNECTION_RADIUS;

            if ((Math.abs(x - p.getX()) <= d) && (Math.abs(y - p.getY()) <= d)) {
                return Math.abs(z - p.getZ()) <= d;
            }
        }

        return false;
    }
}
