package eu.webtoolkit.jwt.chart;

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
 * Abstract base class for styling rendered data series in charts.
 * 
 * 
 * This class provides an interface for a palette which sets strokes and fill
 * strokes for data in a chart. A palette is an ordered list of styles, which is
 * indexed by the chart to get a suitable style for a particular series (in case
 * of {@link WCartesianChart}) or data row (in case of {@link WPieChart}). Each
 * style is defined by a brush, two pen styles (one for borders, and one for
 * plain lines), and a font color that is appropriate for drawing text within
 * the brushed area.
 * <p>
 * To use a custom palette, you should reimplement this class, and then use
 * {@link WAbstractChart#setPalette(WChartPalette palette)} to use an instance
 * of the palette.
 */
public interface WChartPalette {
	/**
	 * Returns a brush from the palette.
	 * 
	 * Returns the brush for the style with given <i>index</i>.
	 */
	public WBrush brush(int index);

	/**
	 * Returns a border pen from the palette.
	 * 
	 * Returns the pen for stroking borders around an area filled using the
	 * brush at the same <i>index</i>.
	 * <p>
	 * 
	 * @see WChartPalette#strokePen(int index)
	 * @see WChartPalette#brush(int index)
	 */
	public WPen borderPen(int index);

	/**
	 * Returns a stroke pen from the palette.
	 * 
	 * Returns the pen for stroking lines for the style with given <i>index</i>.
	 * <p>
	 * 
	 * @see WChartPalette#strokePen(int index)
	 */
	public WPen strokePen(int index);

	/**
	 * Returns a font color from the palette.
	 * 
	 * Returns a font color suitable for rendering text in the area filled with
	 * the brush at the same <i>index</i>.
	 * <p>
	 * 
	 * @see WChartPalette#brush(int index)
	 */
	public WColor fontColor(int index);
}
