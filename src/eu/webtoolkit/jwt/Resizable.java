/*
 * Copyright (C) 2009 Emweb bvba, Leuven, Belgium.
 *
 * See the LICENSE file for terms of use.
 */
package eu.webtoolkit.jwt;

import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.lang.ref.*;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.http.*;
import javax.servlet.*;
import eu.webtoolkit.jwt.*;
import eu.webtoolkit.jwt.chart.*;
import eu.webtoolkit.jwt.utils.*;
import eu.webtoolkit.jwt.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Resizable {
	private static Logger logger = LoggerFactory.getLogger(Resizable.class);

	public static void loadJavaScript(WApplication app) {
		app.loadJavaScript("js/Resizable.js", wtjs1());
	}

	static WJavaScriptPreamble wtjs1() {
		return new WJavaScriptPreamble(
				JavaScriptScope.WtClassScope,
				JavaScriptObjectType.JavaScriptConstructor,
				"Resizable",
				"function(c,a){function p(b){var d=c.pageCoordinates(b);b=d.y-f.y;d=Math.max(g+(d.x-f.x),k+(g-l));a.style.width=d+\"px\";b=Math.max(h+b,m+(h-n));a.style.height=b+\"px\";e&&e(d,b)}function q(){a.onmousemove=null;a.onmouseup=null;e&&e(c.pxself(a,\"width\"),c.pxself(a,\"height\"),true)}function r(b){var d=c.widgetCoordinates(a,b);if(a.offsetWidth-d.x<16&&a.offsetHeight-d.y<16){f=c.pageCoordinates(b);g=c.innerWidth(a);h=c.innerHeight(a);l=a.clientWidth; n=a.clientHeight;c.capture(null);c.capture(a);a.onmousemove=p;a.onmouseup=q}}var e=null,f=null,g,h,l,n,k,m,i=c.css(a,\"minWidth\"),j=c.css(a,\"minHeight\");if(c.isIE6){function o(b,d){return(b=(new RegExp(d+\":\\\\s*(\\\\d+(?:\\\\.\\\\d+)?)\\\\s*px\",\"i\")).exec(b.style.cssText))&&b.length==2?b[1]+\"px\":\"\"}i=o(a,\"min-width\");j=o(a,\"min-height\")}k=i==\"0px\"?a.clientWidth:c.parsePx(i);m=j==\"0px\"?a.clientHeight:c.parsePx(j);$(a).mousedown(r);this.onresize=function(b){e=b}}");
	}
}
