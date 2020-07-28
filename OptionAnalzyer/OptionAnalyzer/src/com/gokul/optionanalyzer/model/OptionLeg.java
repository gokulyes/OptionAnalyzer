package com.gokul.optionanalyzer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class OptionLeg {
	
	private String strSymbol;
	private int iPosition;
	private int nPrice;
	
	
	public OptionLeg () {
		
	}

	
	public OptionLeg(String strSym, int iPosition, int nPrice) {
		this.strSymbol = strSym;
		this.iPosition = iPosition;
		this.nPrice = nPrice;

	}	
	
	public String getSymbol() {
		return strSymbol;
	}
	
	public void setSymbol(String strSym) {
		this.strSymbol = strSym;
	}

	public int getiPosition() {
		return iPosition;
	}

	public void setiPosition(int iPosition) {
		this.iPosition = iPosition;
	}

	public String getType() {
		if(this.strSymbol.substring(this.strSymbol.length() -2) == "CE") {
			return "CE"; // 0:Call ; 1: Put
		} else if(this.strSymbol.substring(this.strSymbol.length() -2) == "PE") {	
			return "PE";
		} else {
			return "";
		}
	}

	public int getnPrice() {
		return nPrice;
	}

	public void setnPrice(int nPrice) {
		this.nPrice = nPrice;
	}
	
	
	public List<Integer> getPayOffData() {
		List<Integer> listOLPayOff = new ArrayList<>();
//		String strType = this.strSymbol.substring(this.strSymbol.length() -2);
		int nStrike = strSymbol.length() > 12 ? Integer.parseInt(strSymbol.substring(0, strSymbol.length()-2).substring(12)) : 0;		
		
		for(int nUnderlying =8000; nUnderlying <= 12000; nUnderlying+=100) {
			if(this.strSymbol.substring(this.strSymbol.length() -2).equals("CE")) { // 0: Call, 1: Put
				if (iPosition == 0) { // Long ------ 		0: Long, 1: Short 
					if (nUnderlying > nStrike) { // In the Money
						listOLPayOff.add(nUnderlying - nStrike - nPrice);
					} else { // Out of the Money
						listOLPayOff.add(- nPrice);
					}

					
				} else if (iPosition == 1) { //Short
					if (nUnderlying > nStrike) { // In the Money
						listOLPayOff.add(-(nUnderlying - nStrike - nPrice));
					} else { // Out of the Money
						listOLPayOff.add(nPrice);
					}

				}

			} else { // Put
				if (iPosition == 0) { // Long
					if (nUnderlying < nStrike) { // In the Money
						listOLPayOff.add( nStrike - nUnderlying - nPrice);
					} else { // Out of the Money
						listOLPayOff.add(- nPrice);
					}

					
				} else if (iPosition == 1) { //Short
					if (nUnderlying < nStrike) { // In the Money
						listOLPayOff.add(-(nStrike - nUnderlying - nPrice));
					} else { // Out of the Money
						listOLPayOff.add(nPrice);
					}	

				}
				
			}
		}			
		
		return listOLPayOff;
	}
	



}
