package com.pogeyan.cmis.api.data.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DefaultValueImpl<T> implements Serializable {
	private static final long serialVersionUID = 5185025809016103495L;
	private List<T> value;

	public DefaultValueImpl(List<T> value) {
		super();
		this.value = value;
	}

	public List<T> getValue() {
		if (value == null) {
			value = new ArrayList<T>(0);
		}

		return value;
	}

	public void setValue(List<T> value) {
		this.value = value;
	}

	public void setValue(T value) {
		this.value = new ArrayList<T>(1);
		this.value.add(value);
	}

	@Override
	public String toString() {
		return "DefaultValueImpl [value=" + value + "]";
	}

}
