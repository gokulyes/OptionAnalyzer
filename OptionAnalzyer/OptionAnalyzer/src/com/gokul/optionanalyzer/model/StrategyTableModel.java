package com.gokul.optionanalyzer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StrategyTableModel extends AbstractTableModel {

		
		private String[] columnNames = {"Symbol", "Position", "Price" };
		private List<OptionLeg> data = new ArrayList<OptionLeg>();
		private String strStrategyName;

	 //Abstract method implementation
	    public int getRowCount() {
	       return data.size();
	    }

	    //Abstract method implementation
	    public int getColumnCount() {
	       return columnNames.length;
	    }

	    //Abstract method implementation
	    public Object getValueAt(int row, int colum) {
	    	OptionLeg objOptionLeg = data.get(row);

	      switch(colum){
	      	case 0:
	      		return objOptionLeg.getSymbol();
	        case 1: 
	        	if (objOptionLeg.getiPosition() == 0)
	        		return "Long";
	        	else
	        		return "Short";
	        case 2: return objOptionLeg.getnPrice();
	        default : return null;
	      }
	    }

	    @Override
	    public String getColumnName(int column) {
	        return columnNames[column];
	    }
	    
	    @Override
	    public Class<?> getColumnClass(int columnIndex) {
	        if (getRowCount() > 0 && getValueAt(0, columnIndex) != null) {
	            return getValueAt(0, columnIndex).getClass();
	        }
	        return super.getColumnClass(columnIndex);
	    }


	    public void addRowData(String strName, OptionLeg objOptionLeg){
	    		
	    		strStrategyName = strName;
	    		data.add(objOptionLeg);
	    		System.out.print( "\n StrategyTable data.size: " + data.size() );
	    }
	    
	    public void clearAllRows() {
	    	data.clear();
	    }
	    
	    public String getStrategyName() {
	    	return strStrategyName;
	    }

		@Override
		public String toString() {
			return "StrategyTable [columnNames=" + Arrays.toString(columnNames) + ", data=" + data.toArray() + "]";
		}	

		
}

