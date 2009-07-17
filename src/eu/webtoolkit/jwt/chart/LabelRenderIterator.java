/*
 * Copyright (C) 2009 Emweb bvba, Leuven, Belgium.
 *
 * See the LICENSE file for terms of use.
 */
package eu.webtoolkit.jwt.chart;

import java.util.EnumSet;
import eu.webtoolkit.jwt.AlignmentFlag;
import eu.webtoolkit.jwt.WColor;
import eu.webtoolkit.jwt.WPointF;
import eu.webtoolkit.jwt.WString;

class LabelRenderIterator extends SeriesIterator {
	public LabelRenderIterator(WChart2DRenderer renderer) {
		super();
		this.renderer_ = renderer;
	}

	public boolean startSeries(WDataSeries series, double groupWidth,
			int numBarGroups, int currentBarGroup) {
		if (series.isLabelsEnabled(Axis.XAxis)
				|| series.isLabelsEnabled(Axis.YAxis)) {
			this.groupWidth_ = groupWidth;
			this.numGroups_ = numBarGroups;
			this.group_ = currentBarGroup;
			return true;
		} else {
			return false;
		}
	}

	public void newValue(WDataSeries series, double x, double y, double stackY) {
		WString text = new WString();
		if (series.isLabelsEnabled(Axis.XAxis)) {
			text = this.renderer_.getChart().getAxis(Axis.XAxis).getLabel(x);
		}
		if (series.isLabelsEnabled(Axis.YAxis)) {
			if (!(text.length() == 0)) {
				text.append(": ");
			}
			text.append(this.renderer_.getChart().getAxis(series.getAxis())
					.getLabel(y - stackY));
		}
		if (!(text.length() == 0)) {
			WPointF p = this.renderer_.map(x, y, series.getAxis(), this
					.getCurrentXSegment(), this.getCurrentYSegment());
			if (series.getType() == SeriesType.BarSeries) {
				double g = this.numGroups_ + (this.numGroups_ - 1)
						* this.renderer_.getChart().getBarMargin();
				double width = this.groupWidth_ / g;
				double left = p.getX() - this.groupWidth_ / 2 + this.group_
						* width
						* (1 + this.renderer_.getChart().getBarMargin());
				p = new WPointF(left + width / 2, p.getY());
			}
			WColor c = WColor.black;
			EnumSet<AlignmentFlag> alignment = EnumSet
					.noneOf(AlignmentFlag.class);
			if (series.getType() == SeriesType.BarSeries) {
				if (y < 0) {
					alignment = EnumSet.copyOf(EnumSet.of(
							AlignmentFlag.AlignCenter,
							AlignmentFlag.AlignBottom));
				} else {
					alignment = EnumSet.copyOf(EnumSet.of(
							AlignmentFlag.AlignCenter, AlignmentFlag.AlignTop));
				}
				c = series.getLabelColor();
			} else {
				alignment = EnumSet.copyOf(EnumSet.of(
						AlignmentFlag.AlignCenter, AlignmentFlag.AlignBottom));
				p.setY(p.getY() - 3);
			}
			this.renderer_.renderLabel(text, p, c, alignment, 0, 3);
		}
	}

	private WChart2DRenderer renderer_;
	private double groupWidth_;
	private int numGroups_;
	private int group_;
}
