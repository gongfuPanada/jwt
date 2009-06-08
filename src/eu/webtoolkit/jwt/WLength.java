package eu.webtoolkit.jwt;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.http.*;
import eu.webtoolkit.jwt.*;
import eu.webtoolkit.jwt.chart.*;
import eu.webtoolkit.jwt.utils.*;
import eu.webtoolkit.jwt.servlet.*;

/**
 * A class that specifies a CSS length
 * 
 * 
 * The class combines a value with a unit. There is a special value <i>auto</i>
 * which has a different meaning depending on the context.
 */
public class WLength {
	/**
	 * The unit.
	 */
	public enum Unit {
		/**
		 * The relative font size.
		 */
		FontEm,
		/**
		 * The height of an &apos;x&apos; in the font.
		 */
		FontEx,
		/**
		 * Pixel, relative to canvas resolution.
		 */
		Pixel,
		/**
		 * Inche.
		 */
		Inch,
		/**
		 * Centimeter.
		 */
		Centimeter,
		/**
		 * Millimeter.
		 */
		Millimeter,
		/**
		 * Point (1/72 Inch).
		 */
		Point,
		/**
		 * Pica (12 Point).
		 */
		Pica,
		/**
		 * Percentage (meaning context-sensitive).
		 */
		Percentage;

		public int getValue() {
			return ordinal();
		}
	}

	/**
	 * Creates an &apos;auto&apos; length.
	 * 
	 * Specifies an &apos;auto&apos; length.
	 * <p>
	 * 
	 * @see WLength#Auto
	 */
	public WLength() {
		this.auto_ = true;
		this.value_ = -1;
	}

	/**
	 * Creates a length with value and unit.
	 * 
	 * This constructor will also provide the implicit conversion between a
	 * double and {@link WLength}, using a pixel unit.
	 */
	public WLength(double value, WLength.Unit unit) {
		this.auto_ = false;
		this.unit_ = unit;
		this.value_ = value;
	}

	public WLength(double value) {
		this(value, WLength.Unit.Pixel);
	}

	/**
	 * Returns whether the ength is &apos;auto&apos;.
	 * 
	 * @see WLength#WLength()
	 * @see WLength#Auto
	 */
	public boolean isAuto() {
		return this.auto_;
	}

	/**
	 * Returns the value.
	 * 
	 * @see WLength#getUnit()
	 */
	public double getValue() {
		return this.value_;
	}

	/**
	 * Returns the unit.
	 * 
	 * @see WLength#getValue()
	 */
	public WLength.Unit getUnit() {
		return this.unit_;
	}

	/**
	 * Returns the CSS text.
	 */
	public String getCssText() {
		if (this.auto_) {
			return "auto";
		} else {
			return String.valueOf(this.value_)
					+ unitText[this.unit_.getValue()];
		}
	}

	/**
	 * Comparison operator.
	 */
	public boolean equals(WLength other) {
		return this.auto_ == other.auto_ && this.unit_ == other.unit_
				&& this.value_ == other.value_;
	}

	public double getToPixels() {
		if (this.auto_) {
			return 0;
		} else {
			return this.value_ * unitFactor[this.unit_.getValue()];
		}
	}

	private boolean auto_;
	private WLength.Unit unit_;
	private double value_;
	private static String[] unitText = { "em", "ex", "px", "in", "cm", "mm",
			"pt", "pc", "%" };
	private static final double pxPerPt = 4.0 / 3.0;
	private static double[] unitFactor = { 16, 8, 1, 72 * pxPerPt,
			72 / 2.54 * pxPerPt, 72 / 25.4 * pxPerPt, pxPerPt, 12 * pxPerPt,
			0.16 };

	static WLength multiply(WLength l, double s) {
		return new WLength(l.getValue() * s, l.getUnit());
	}

	static WLength multiply(double s, WLength l) {
		return WLength.multiply(l, s);
	}

	static WLength divide(WLength l, double s) {
		return WLength.multiply(l, 1 / s);
	}

	/**
	 * An &apos;auto&apos; length.
	 * 
	 * @see WLength#WLength()
	 */
	public static WLength Auto = new WLength();
}
