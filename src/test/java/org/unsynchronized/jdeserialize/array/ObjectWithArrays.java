package org.unsynchronized.jdeserialize.array;

import java.io.Serializable;

public class ObjectWithArrays implements Serializable {

	private static final long serialVersionUID = 2993837144418188970L;
	private final String[] stringArray;
	private final int[] intArray;
	private final Object[] objectArray;

	public ObjectWithArrays(String[] theStringArray, int[] theIntArray, Object[] theObjectArray) {
		super();
		this.stringArray = theStringArray;
		this.intArray = theIntArray;
		this.objectArray = theObjectArray;
	}

}
