package com.gokul.optionanalyzer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class OptionLeg {
	


	private int iPosition;
	private int iType;
	private int nStrike;
	private int nPrice;

	
	private List<Integer> list = new ArrayList<>();
	
	
	public OptionLeg () {
		
	}

	public OptionLeg(int iPosition, int iType, int nStrike, int nPrice) {
		super();
		this.iPosition = iPosition;
		this.iType = iType;
		this.nStrike = nStrike;
		this.nPrice = nPrice;
		setPayOffData();
	}	
	
//	@Override
//	public String toString() {
//		return "OptionLeg [iPosition=" + iPosition + ", iType=" + iType + ", nStrike=" + nStrike + ", nPrice=" + nPrice
//				+ "]";
//	}

	public int getiPosition() {
		return iPosition;
	}

	public void setiPosition(int iPosition) {
		this.iPosition = iPosition;
	}

	public int getiType() {
		return iType;
	}

	public void setiType(int iType) {
		this.iType = iType;
	}

	public int getnStrike() {
		return nStrike;
	}

	public void setnStrike(int nStrike) {
		this.nStrike = nStrike;
	}

	public int getnPrice() {
		return nPrice;
	}

	public void setnPrice(int nPrice) {
		this.nPrice = nPrice;
	}
	
	public List<Integer> getPayOffData() {
		
		return list;
	}
	
	public void setPayOffData() {
//		System.out.print("\n");
		for(int nUnderlying =8000; nUnderlying <= 12000; nUnderlying+=100) {
			if(iType == 0) { // 0: Call, 1: Put
				if (iPosition > 0) { // Long
					if (nUnderlying > this.nStrike) { // In the Money
						list.add(nUnderlying - nStrike - nPrice);
					} else { // Out of the Money
						list.add(- nPrice);
					}
					
				} else if (iPosition < 0) { //Short
					if (nUnderlying > this.nStrike) { // In the Money
						list.add(-(nUnderlying - nStrike - nPrice));
					} else { // Out of the Money
						list.add(nPrice);
					}					
				}

			} else { // Put
				if (iPosition > 0) { // Long
					if (nUnderlying < this.nStrike) { // In the Money
						list.add( nStrike - nUnderlying - nPrice);
					} else { // Out of the Money
						list.add(- nPrice);
					}
					
				} else if (iPosition < 0) { //Short
					if (nUnderlying < this.nStrike) { // In the Money
						list.add(-(nStrike - nUnderlying - nPrice));
					} else { // Out of the Money
						list.add(nPrice);
					}					
				}
				
			}
		}		

	}
	

	@Override
	public String toString() {
		return "\n OptionLeg [iPosition=" + iPosition + ", iType=" + iType + ", nStrike=" + nStrike + ", nPrice=" + nPrice
				+ ", list=" + list + "]";
	}
	
	public void printPayOffData() {
		System.out.print("\n");
		for (Integer element : list) {
			System.out.print("\n " + element.toString());
		}
	
	}
	
}
