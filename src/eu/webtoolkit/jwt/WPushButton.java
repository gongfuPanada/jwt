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
 * A widget that represents a push button
 * 
 * 
 * To act on a button click, connect a slot to the
 * {@link WInteractWidget#clicked()} signal.
 * <p>
 * Usage example:
 * <p>
 * The widget corresponds to the HTML <code>&lt;button&gt;</code> tag.
 * <p>
 * WPushButton is an {@link WWidget#setInline(boolean inlined) inline} widget.
 */
public class WPushButton extends WFormWidget {
	/**
	 * Create a push button with empty label.
	 */
	public WPushButton(WContainerWidget parent) {
		super(parent);
		this.text_ = new WString();
		this.textChanged_ = false;
	}

	public WPushButton() {
		this((WContainerWidget) null);
	}

	/**
	 * Create a push button with given label.
	 */
	public WPushButton(CharSequence text, WContainerWidget parent) {
		super(parent);
		this.text_ = new WString(text);
		this.textChanged_ = false;
	}

	public WPushButton(CharSequence text) {
		this(text, (WContainerWidget) null);
	}

	/**
	 * Set the button text.
	 */
	public void setText(CharSequence text) {
		if (canOptimizeUpdates() && text.equals(this.text_)) {
			return;
		}
		this.text_ = WString.toWString(text);
		this.textChanged_ = true;
		this.repaint(EnumSet.of(RepaintFlag.RepaintInnerHtml));
	}

	/**
	 * Get the button text.
	 */
	public WString getText() {
		return this.text_;
	}

	public void refresh() {
		if (this.text_.refresh()) {
			this.textChanged_ = true;
			this.repaint(EnumSet.of(RepaintFlag.RepaintInnerHtml));
		}
		super.refresh();
	}

	private WString text_;
	private boolean textChanged_;

	protected void updateDom(DomElement element, boolean all) {
		if (all) {
			element.setAttribute("type", "button");
		}
		if (this.textChanged_ || all) {
			element.setProperty(Property.PropertyInnerHTML, this.text_
					.isLiteral() ? escapeText(this.text_, true).toString()
					: this.text_.toString());
			this.textChanged_ = false;
		}
		super.updateDom(element, all);
	}

	protected DomElementType getDomElementType() {
		return DomElementType.DomElement_BUTTON;
	}

	protected void propagateRenderOk(boolean deep) {
		this.textChanged_ = false;
		super.propagateRenderOk(deep);
	}
}
