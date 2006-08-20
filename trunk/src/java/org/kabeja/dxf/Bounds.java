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

import org.kabeja.dxf.helpers.Point;

/**
 * This class is a helper class and reflect a viewport of a entity/layer or
 * document
 *
 * @author <a href="mailto:simon.mieth@gmx.de>Simon Mieth</a>
 *
 */
public class Bounds {
	protected double max_x = Double.NEGATIVE_INFINITY;

	protected double min_x = Double.POSITIVE_INFINITY;

	protected double max_y = Double.NEGATIVE_INFINITY;

	protected double min_y = Double.POSITIVE_INFINITY;

	protected double max_z = Double.NEGATIVE_INFINITY;

	protected double min_z = Double.POSITIVE_INFINITY;

	protected boolean set = true;

	/**
	 *
	 */
	public Bounds() {
	}

	public Bounds(double max_x, double min_x, double max_y, double min_y) {
		this.max_x = max_x;
		this.min_x = min_x;
		this.max_y = max_y;
		this.min_y = min_y;
	}

	public Bounds(Bounds b) {
		this.max_x = b.getMaximumX();
		this.min_x = b.getMinimumX();
		this.max_y = b.getMaximumY();
		this.min_y = b.getMinimumY();
	}

	/**
	 * @return Returns the max_x.
	 */
	public double getMaximumX() {
		return max_x;
	}

	/**
	 * @param max_x
	 *            The max_x to set.
	 */
	public void setMaximumX(double max_x) {
		this.max_x = max_x;
	}

	/**
	 * @return Returns the max_y.
	 */
	public double getMaximumY() {
		return max_y;
	}

	/**
	 * @param max_y
	 *            The max_y to set.
	 */
	public void setMaximumY(double max_y) {
		this.max_y = max_y;
	}

	/**
	 * @return Returns the min_x.
	 */
	public double getMinimumX() {
		return min_x;
	}

	/**
	 * @param min_x
	 *            The min_x to set.
	 */
	public void setMinimumX(double min_x) {
		this.min_x = min_x;
	}

	/**
	 * @return Returns the min_y.
	 */
	public double getMinimumY() {
		return min_y;
	}

	/**
	 * @param min_y
	 *            The min_y to set.
	 */
	public void setMinimumY(double min_y) {
		this.min_y = min_y;
	}

	/**
	 * Enlarge the Bounds to the given Bounds if they enlarge the area.
	 *
	 * @param bounds
	 */
	public void addToBounds(Bounds bounds) {
		if (bounds.getMaximumX() > this.getMaximumX()) {
			this.setMaximumX(bounds.getMaximumX());
		}

		if (bounds.getMaximumY() > this.getMaximumY()) {
			this.setMaximumY(bounds.getMaximumY());
		}

		if (bounds.getMinimumX() < this.getMinimumX()) {
			this.setMinimumX(bounds.getMinimumX());
		}

		if (bounds.getMinimumY() < this.getMinimumY()) {
			this.setMinimumY(bounds.getMinimumY());
		}
	}

	/**
	 * Enlarge the Bounds if the given bounds enlarge the Bounds.
	 *
	 * @param x
	 * @param y
	 */
	public void addToBounds(double x, double y) {
		if (x > this.getMaximumX()) {
			this.setMaximumX(x);
		}

		if (x < this.getMinimumX()) {
			this.setMinimumX(x);
		}

		if (y > this.getMaximumY()) {
			this.setMaximumY(y);
		}

		if (y < this.getMinimumY()) {
			this.setMinimumY(y);
		}
	}

	public void addToBounds(Point p) {
		addToBounds(p.getX(), p.getY());
	}

	public double getWidth() {
		return Math.abs(getMaximumX() - getMinimumX());
	}

	public double getHeight() {
		return Math.abs(getMaximumY() - getMinimumY());
	}

	/**
	 * @return Returns the set.
	 */
	public boolean isValid() {
		if ((this.max_x == Double.NEGATIVE_INFINITY)
				|| (this.max_y == Double.NEGATIVE_INFINITY)
				|| (this.min_x == Double.POSITIVE_INFINITY)
				|| (this.min_y == Double.POSITIVE_INFINITY)) {
			return false;
		}

		return set;
	}

	/**
	 * @param set
	 *            The set to set.
	 */
	public void setValid(boolean set) {
		this.set = set;
	}

	public void debug() {
		System.out.println("DEBUG Bounds");
		System.out.println("MAX_x=" + max_x);
		System.out.println("MAX_y=" + max_y);
		System.out.println("MIN_x=" + min_x);
		System.out.println("MIN_y=" + min_y);
		System.out.println("Width=" + getWidth() + " Height:" + getHeight());
	}

	/**
	 * Determines if the given bounding box part or inside the bounds.
	 *
	 * @param bounds
	 * @return
	 */
	public boolean contains(Bounds bounds) {
		if ((bounds.getMaximumX() <= this.min_x)
				|| (bounds.getMinimumX() >= this.max_x)) {
			// the given bounds are on the left or right side of the bounds
			return false;
		}

		if ((bounds.getMaximumY() <= this.min_y)
				|| (bounds.getMinimumY() >= this.max_y)) {
			// the given bounds are above or below
			return false;
		}

		return true;
	}

	public boolean enclose(Bounds bounds) {
		if ((bounds.getMaximumX() <= this.max_x)
				&& (bounds.getMinimumX() >= this.min_x)) {
			// the given bounds are on the left or right side of the bounds

			if ((bounds.getMaximumY() <= this.max_y)
					&& (bounds.getMinimumY() >= this.min_y)) {
				// the given bounds are above or below
				return true;
			}
		}

		return false;
	}
}