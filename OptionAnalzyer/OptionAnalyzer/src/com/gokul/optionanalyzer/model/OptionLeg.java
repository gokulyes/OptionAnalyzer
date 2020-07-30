package com.gokul.optionanalyzer.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class OptionLeg {
	
	@Override
	public String toString() {
		return "OptionLeg [nId=" + nId + ", strName=" + strName + ", strSymbol=" + strSymbol + ", bPosition="
				+ bPosition + ", nPrice=" + nPrice + ", bCovered=" + bCovered + ", iCoverPrice=" + iCoverPrice + "]";
	}


	private int nId;
	private String strName; 				// Strategy name
	private String strSymbol;				// Option Symbol
	private boolean bPosition = true;		// Long: true, Short: false
	private int nPrice;
	private boolean bCovered = false;		// Position covered?
	private int iCoverPrice = 0;			// Position covered at price.
	
	public OptionLeg () {
		
	}

	
	public OptionLeg(String strSym, boolean bPosition, int nPrice) {
		this.strSymbol = strSym;
		this.bPosition = bPosition;
		this.nPrice = nPrice;

	}	

	public OptionLeg(int id, String sname, String strSym, boolean bPosition, int nPrice) {
		this.nId = id;
		this.strName = sname;		
		this.strSymbol = strSym;
		this.bPosition = bPosition;
		this.nPrice = nPrice;

	}	
	
	public OptionLeg(int id, String sname, String strSym, boolean bPosition, int nPrice, boolean bCover, int coverPrice) {
		this.nId = id;
		this.strName = sname;				
		this.strSymbol = strSym;
		this.bPosition = bPosition;
		this.nPrice = nPrice;
		this.bCovered = bCover;
		this.iCoverPrice = coverPrice;

	}
	
	public void setID(int id) {
		this.nId = id;
	}
	
	public int getID () {
		return this.nId;
	}
	
	public void setStrategyName(String sName) {
		this.strName = sName;
	}
	
	public String getStrategyName() {
		return this.strName ;
	}
	
	public String getSymbol() {
		return strSymbol;
	}
	
	public void setSymbol(String strSym) {
		this.strSymbol = strSym;
	}

	public boolean getPosition() {
		return bPosition;
	}

	public void setiPosition(boolean bPosition) {
		this.bPosition = bPosition;
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
	
	public void setPostionCovered(boolean cover) {
		this.bCovered = cover;
	}
	
	public boolean getPostionCover() { 
		return this.bCovered;
	}
	
	public void setPostionCoverPrice(int price) {
		this.nPrice = price;
	}
	
	public int getPostionCoverPrice() {
		return this.iCoverPrice;
	}
	
	
	public List<Integer> getPayOffData() {
		List<Integer> listOLPayOff = new ArrayList<>();
//		String strType = this.strSymbol.substring(this.strSymbol.length() -2);
		int nStrike = strSymbol.length() > 12 ? Integer.parseInt(strSymbol.substring(0, strSymbol.length()-2).substring(12)) : 0;		
		
		for(int nUnderlying =8000; nUnderlying <= 12000; nUnderlying+=100) {
			if(this.strSymbol.substring(this.strSymbol.length() -2).equals("CE")) { // 0: Call, 1: Put
				if (bPosition) { // Long ------ 		true: Long, False: Short 
					if (nUnderlying > nStrike) { // In the Money
						listOLPayOff.add(nUnderlying - nStrike - nPrice);
					} else { // Out of the Money
						listOLPayOff.add(- nPrice);
					}

					
				} else if (!bPosition) { //Short
					if (nUnderlying > nStrike) { // In the Money
						listOLPayOff.add(-(nUnderlying - nStrike - nPrice));
					} else { // Out of the Money
						listOLPayOff.add(nPrice);
					}

				}

			} else { // Put
				if (bPosition) { // Long
					if (nUnderlying < nStrike) { // In the Money
						listOLPayOff.add( nStrike - nUnderlying - nPrice);
					} else { // Out of the Money
						listOLPayOff.add(- nPrice);
					}

					
				} else if (!bPosition) { //Short
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
