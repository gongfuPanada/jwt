/*
 * Copyright (C) 2006 Pieter Libin, Leuven, Belgium.
 *
 * Licensed under the terms of the GNU General Public License,
 * see the LICENSE file for more details.
 */

package eu.webtoolkit.jwt;

import java.util.ArrayList;

/**
 * A signal passing 1 argument.
 * 
 * A signal implements the Observable pattern, allowing one or more listeners to listen for
 * events generated on the signal. The event may propagate an argument to the listeners.
 */
public class Signal1<A1> extends AbstractSignal {
	/**
	 * The listener interface.
	 * 
	 * This listener may be added to a {@link Signal1}, {@link EventSignal1} or {@link JSignal1},
	 * and its {@link #trigger(Object)} method will be invoked whenever the signal is triggered.
	 */
	public static interface Listener<A1> extends SignalImpl.Listener {
		/**
		 * Triggers the listener.
		 * 
		 * @param arg1 The argument
		 */
		public void trigger(A1 arg);
	}

	/**
	 * Creates a new signal.
	 */
	public Signal1() {
	}

	Signal1(WObject sender) {
		this();
	}

	/**
	 * Adds a listener for this signal.
	 * 
	 * Each listener will be triggered whenever the signal is triggered.
	 * 
	 * @param listenerOwner
	 *            the enclosing object for a listener implemented using an anonymous inner class
	 * @param listener
	 *            the listener
	 * @return a connection object that may be used to control the connection
	 * 
	 * @see AbstractSignal#addListener(WObject, eu.webtoolkit.jwt.Signal.Listener)
	 */
	public Connection addListener(WObject listenerOwner, Listener<A1> listener) {
		return getImpl(true).addListener(listenerOwner, listener);
	}

	/**
	 * Removes a listener.
	 * 
	 * @param listener a listener that was previously added
	 */
	public void removeListener(Listener<A1> listener) {
		getImpl(false).removeListener(listener);
	}

	/**
	 * Triggers the signal.
	 * 
	 * The argument is passed to the listeners.
	 * 
	 * @param arg The argument.
	 */
	@SuppressWarnings("unchecked")
	public void trigger(A1 arg) {
		SignalImpl impl = getImpl(false);
		if (impl == null)
			return;

		ArrayList<SignalImpl.Listener> listeners = impl.getListeners();

		for (SignalImpl.Listener listener : listeners)
			((Listener) (listener)).trigger(arg);
	}

	@Override
	public Connection addListener(WObject listenerOwner, final Signal.Listener listener) {
		Signal1.Listener<A1> l = new Signal1.Listener<A1>() {
			public void trigger(A1 a1) {
				listener.trigger();
			}
		};

		return getImpl(true).addWrappedListener(listenerOwner, l, listener);
	}

	@Override
	public void removeListener(Signal.Listener listener) {
		getImpl(true).removeWrappedListener(listener);
	}
}