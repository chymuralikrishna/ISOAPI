package com.nfc.main.objects;

public class SelectItem {

	private String displayLabel;
	private Object objectValue;

	public SelectItem(String displayLabel, Object objectValue) {
		super();
		this.displayLabel = displayLabel;
		this.objectValue = objectValue;
	}

	public String getDisplayLabel() {
		return displayLabel;
	}

	public void setDisplayLabel(String displayLabel) {
		this.displayLabel = displayLabel;
	}

	public Object getObjectValue() {
		return objectValue;
	}

	public void setObjectValue(Object objectValue) {
		this.objectValue = objectValue;
	}

}
