package com.gokul.optionanalyzer.model;

import java.util.ArrayList;
import java.util.List;

public class OptionStrategy {


	private String strName;
	private List<OptionLeg> listOptLeg = new ArrayList<>();	
	private List<Integer> listOLPayOff = new ArrayList<>();
	
	public OptionStrategy() {

	}	
	
	public OptionStrategy(String strName, List<OptionLeg> listOptLeg) {
		super();
		this.strName = strName;
		this.listOptLeg = listOptLeg;
	}

	public String getStrName() {
		return strName;
	}


	public void setStrName(String strName) {
		this.strName = strName;
	}


	public List<OptionLeg> getListOptLeg() {
		return listOptLeg;
	}


	public void setListOptLeg(List<OptionLeg> listOptLeg) {
		this.listOptLeg = listOptLeg;
	} 
	
	public void setOptLeg(OptionLeg optionLeg) {
		this.listOptLeg.add(optionLeg);
	} 
	
	public List<Integer> getPayOffData() {

		boolean boolFirst = true;
		int nInnerCounter = 0;


		for (OptionLeg outerElement : listOptLeg) {
			
			nInnerCounter = 0;
			for (Integer intElement : outerElement.getPayOffData()) {

				if (boolFirst == true) {
					listOLPayOff.add(intElement);
				} else {
					listOLPayOff.set(nInnerCounter,  listOLPayOff.get(nInnerCounter) + intElement);
				}
				nInnerCounter++;
			}
			boolFirst = false;
			
		}
		
		return listOLPayOff;		

	}

	
	@Override
	public String toString() {
		return "OptionStrategy [strName=" + strName + ", listOptLeg=" + listOptLeg + "]";
	}
	

}
