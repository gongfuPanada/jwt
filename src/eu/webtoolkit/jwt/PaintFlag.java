/*
 * Copyright (C) 2009 Emweb bvba, Leuven, Belgium.
 *
 * See the LICENSE file for terms of use.
 */
package eu.webtoolkit.jwt;


/**
 * Enumeration that indicates how to change a selection.
 * <p>
 * 
 * @see WPaintedWidget#update(EnumSet flags)
 * @see WPaintDevice#getPaintFlags()
 */
public enum PaintFlag {
	/**
	 * The canvas is not cleared, but further painted on.
	 */
	PaintUpdate;

	public int getValue() {
		return ordinal();
	}
}
