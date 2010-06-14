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
package org.kabeja.entities;

import java.util.ArrayList;
import java.util.List;

import org.kabeja.common.Type;
import org.kabeja.entities.util.HatchBoundaryLoop;
import org.kabeja.math.Bounds;
import org.kabeja.math.Point3D;
import org.kabeja.math.TransformContext;

/**
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 * 
 */
public class Hatch extends Entity {
	private String name = "";
	private boolean solid = false;
	private int associativityFlag = 0;
	private int boundaryPathCount = 0;
	private int hatchStyle = 0;
	private int patternType = 0;
	private double patternAngle = 0.0;
	private double patternScaleSpacing = 1.0;
	private boolean boundaryAnnotation = false;
	private boolean patternDouble = false;
	private int definationLinesCount = 0;
	private double pixelSize = 0.0;
	private int seedPointCount = 0;
	private double offsetVector = 0.0;
	private int degenerateBoundaryPathCount = 0;
	private boolean gradientHatch = false;
	private Point3D elevationPoint = new Point3D();
	private List<HatchBoundaryLoop> boundaries = new ArrayList<HatchBoundaryLoop>();
	private String patternID = "";
	private double patternScale;

	public Hatch() {
	}

	/**
	 * @return Returns the associativityFlag.
	 */
	public int getAssociativityFlag() {
		return associativityFlag;
	}

	/**
	 * @param associativityFlag
	 *            The associativityFlag to set.
	 */
	public void setAssociativityFlag(int associativityFlag) {
		this.associativityFlag = associativityFlag;
	}

	/**
	 * @return Returns the boundaryAnnotation.
	 */
	public boolean isBoundaryAnnotation() {
		return boundaryAnnotation;
	}

	/**
	 * @param boundaryAnnotation
	 *            The boundaryAnnotation to set.
	 */
	public void setBoundaryAnnotation(boolean boundaryAnnotation) {
		this.boundaryAnnotation = boundaryAnnotation;
	}

	/**
	 * @return Returns the boundaryPathCount.
	 */
	public int getBoundaryPathCount() {
		return boundaryPathCount;
	}

	/**
	 * @param boundaryPathCount
	 *            The boundaryPathCount to set.
	 */
	public void setBoundaryPathCount(int boundaryPathCount) {
		this.boundaryPathCount = boundaryPathCount;
	}

	/**
	 * @return Returns the definationLinesCount.
	 */
	public int getDefinationLinesCount() {
		return definationLinesCount;
	}

	/**
	 * @param definationLinesCount
	 *            The definationLinesCount to set.
	 */
	public void setDefinationLinesCount(int definationLinesCount) {
		this.definationLinesCount = definationLinesCount;
	}

	/**
	 * @return Returns the degenerateBoundaryPathCount.
	 */
	public int getDegenerateBoundaryPathCount() {
		return degenerateBoundaryPathCount;
	}

	/**
	 * @param degenerateBoundaryPathCount
	 *            The degenerateBoundaryPathCount to set.
	 */
	public void setDegenerateBoundaryPathCount(int degenerateBoundaryPathCount) {
		this.degenerateBoundaryPathCount = degenerateBoundaryPathCount;
	}

	/**
	 * @return Returns the gradientHatch.
	 */
	public boolean isGradientHatch() {
		return gradientHatch;
	}

	/**
	 * @param gradientHatch
	 *            The gradientHatch to set.
	 */
	public void setGradientHatch(boolean gradientHatch) {
		this.gradientHatch = gradientHatch;
	}

	/**
	 * @return Returns the hatchStyle.
	 */
	public int getHatchStyle() {
		return hatchStyle;
	}

	/**
	 * @param hatchStyle
	 *            The hatchStyle to set.
	 */
	public void setHatchStyle(int hatchStyle) {
		this.hatchStyle = hatchStyle;
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

	/**
	 * @return Returns the offsetVector.
	 */
	public double getOffsetVector() {
		return offsetVector;
	}

	/**
	 * @param offsetVector
	 *            The offsetVector to set.
	 */
	public void setOffsetVector(double offsetVector) {
		this.offsetVector = offsetVector;
	}

	/**
	 * @return Returns the patternAngle.
	 */
	public double getPatternAngle() {
		return patternAngle;
	}

	/**
	 * @param patternAngle
	 *            The patternAngle to set.
	 */
	public void setPatternAngle(double patternAngle) {
		this.patternAngle = patternAngle;
	}

	/**
	 * @return Returns the patternDouble.
	 */
	public boolean isPatternDouble() {
		return patternDouble;
	}

	/**
	 * @param patternDouble
	 *            The patternDouble to set.
	 */
	public void setPatternDouble(boolean patternDouble) {
		this.patternDouble = patternDouble;
	}

	/**
	 * @return Returns the patternScaleSpacing.
	 */
	public double getPatternScaleSpacing() {
		return patternScaleSpacing;
	}

	/**
	 * @param patternScaleSpacing
	 *            The patternScaleSpacing to set.
	 */
	public void setPatternScaleSpacing(double patternScaleSpacing) {
		this.patternScaleSpacing = patternScaleSpacing;
	}

	/**
	 * @return Returns the patternType.
	 */
	public int getPatternType() {
		return patternType;
	}

	/**
	 * @param patternType
	 *            The patternType to set.
	 */
	public void setPatternType(int patternType) {
		this.patternType = patternType;
	}

	/**
	 * @return Returns the pixelSize.
	 */
	public double getPixelSize() {
		return pixelSize;
	}

	/**
	 * @param pixelSize
	 *            The pixelSize to set.
	 */
	public void setPixelSize(double pixelSize) {
		this.pixelSize = pixelSize;
	}

	/**
	 * @return Returns the seedPointCount.
	 */
	public int getSeedPointCount() {
		return seedPointCount;
	}

	/**
	 * @param seedPointCount
	 *            The seedPointCount to set.
	 */
	public void setSeedPointCount(int seedPointCount) {
		this.seedPointCount = seedPointCount;
	}

	/**
	 * @return Returns the solid.
	 */
	public boolean isSolid() {
		return this.flags == 1;
	}

	/**
	 * @param solid
	 *            The solid to set.
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	public void addBoundaryLoop(HatchBoundaryLoop loop) {
		this.boundaries.add(loop);
	}

	public List<HatchBoundaryLoop> getBoundaryLoops() {
		return this.boundaries;
	}

	public Bounds getBounds() {
		Bounds bounds = new Bounds();

		for (HatchBoundaryLoop loop : this.boundaries) {
			Bounds b = loop.getBounds();
			if (b.isValid()) {
				bounds.addToBounds(b);
			}
		}
		return bounds;
	}

	/**
	 * @return Returns the elevationPoint.
	 */
	public Point3D getElevationPoint() {
		return elevationPoint;
	}

	/**
	 * @param elevationPoint
	 *            The elevationPoint to set.
	 */
	public void setElevationPoint(Point3D elevationPoint) {
		this.elevationPoint = elevationPoint;
	}

	public Type<Hatch> getType() {
		return Type.TYPE_HATCH;
	}

	/**
	 * @return Returns the ID of the pattern (also called pattern name).
	 */
	public String getHatchPatternID() {
		return this.patternID;
	}

	/**
	 * @param patternID
	 *            The patternID to set.
	 */
	public void setHatchPatternID(String patternID) {
		this.patternID = patternID;
	}

	public double getLength() {
		return 0;
	}

	public double getPatternScale() {
		return patternScale;
	}

	public void setPatternScale(double patternScale) {
		this.patternScale = patternScale;
	}

	/**
	 * Not implemented yet
	 */

	public void transform(TransformContext context) {

	}
}
