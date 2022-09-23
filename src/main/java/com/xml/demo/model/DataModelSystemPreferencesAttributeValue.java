package com.xml.demo.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DataModelSystemPreferencesAttributeValue {
	private String system_preferences_id;
	private String domain_id;
	private String user_id;
	private String product_id;
	private String attribute_key;
	private String attribute_value;
	
	public ArrayList<String> listModel() {
		ArrayList<String> litsObject = new ArrayList<String>();
		litsObject.add(getSystem_preferences_id());
		litsObject.add(getSystem_preferences_id());
		litsObject.add(getSystem_preferences_id());
		litsObject.add(getSystem_preferences_id());
		litsObject.add(getSystem_preferences_id());
		litsObject.add(getSystem_preferences_id());
		return litsObject;
	}
}
