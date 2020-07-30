package com.gokul.optionanalyzer.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class StrategyTable extends AbstractTableModel {

		
		private String[] columnNames = {	"Strategy","Symbol", "Position", "Price" };
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
	      		return strStrategyName;
	      	case 1:
	      		return objOptionLeg.getSymbol();
	        case 2: 
	        	if (objOptionLeg.getPosition())
	        		return "Long";
	        	else
	        		return "Short";
	        case 3: return objOptionLeg.getnPrice();
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
//	    		System.out.print( "\n StrategyTable data.size: " + data.size() );
	    }

		@Override
		public String toString() {
			return "StrategyTable [columnNames=" + Arrays.toString(columnNames) + ", data=" + data.toArray() + "]";
		}	
	
		

		
}

//public class StrategyTable extends AbstractTableModel {
//	private boolean DEBUG = false;
////   private String[] columnNames = {"Name",
////           "Position",
////           "Strike",
////           "Price",
////           "Vegetarian"};
////	private Object[][] data = {
////	{"Kathy", "Smith",
////	"Snowboarding", new Integer(5), new Boolean(false)}};
//	
//   private String[] columnNames = {	"Name", "Position", "Type", "Strike", "Price" };
//   private List<OptionLeg> data = new ArrayList<OptionLeg>();
//   
//	public int getColumnCount() {
//		return columnNames.length;
//	}
//	
//	public int getRowCount() {
//		return data.length;
//	}
//	
//	public String getColumnName(int col) {
//		return columnNames[col];
//	}
//	
//	public String[] getColumnNames() {
//		
//		return columnNames;
//		
//	}
//	public Object getValueAt(int row, int col) {
//		return data[row][col];
//	}
//	
//	public Object[][] getObjects() {
//		return data;
//	}
//	
//	/*
//	* JTable uses this method to determine the default renderer/
//	* editor for each cell.  If we didn't implement this method,
//	* then the last column would contain text ("true"/"false"),
//	* rather than a check box.
//	*/
//	public Class getColumnClass(int c) {
//		return getValueAt(0, c).getClass();
//	}
//	
//	/*
//	* Don't need to implement this method unless your table's
//	* editable.
//	*/
//	public boolean isCellEditable(int row, int col) {
//	//Note that the data/cell address is constant,
//	//no matter where the cell appears onscreen.
//	if (col < 2) {
//		return false;
//	} else {
//		return true;
//	}
//	}
//	
//	/*
//	* Don't need to implement this method unless your table's
//	* data can change.
//	*/
//	public void setValueAt(Object value, int row, int col) {
//		if (DEBUG) {
//		System.out.println("Setting value at " + row + "," + col
//		          + " to " + value
//		          + " (an instance of "
//		          + value.getClass() + ")");
//		}
//	
//		data[row][col] = value;
//		fireTableCellUpdated(row, col);
//		
//		if (DEBUG) {
//		System.out.println("New value of data:");
//		printDebugData();
//		}
//	}
//	
//	private void printDebugData() {
//		int numRows = getRowCount();
//		int numCols = getColumnCount();
//		
//		for (int i=0; i < numRows; i++) {
//		System.out.print("    row " + i + ":");
//		for (int j=0; j < numCols; j++) {
//		System.out.print("  " + data[i][j]);
//		}
//		System.out.println();
//		}
//		System.out.println("--------------------------");
//	}
//	
//	public void setDataObject(OptionStrategy optionStrategy) {
//		
////		Object[][] tempObj;
//		Object[][] tempObj = {
//				{"Kathy", "Smith",
//				"Snowboarding", new Integer(5), new Boolean(false)},
//				{"John", "Doe",
//				"Rowing", new Integer(3), new Boolean(true)},
//				{"Sue", "Black",
//				"Knitting", new Integer(2), new Boolean(false)},
//				{"Jane", "White",
//				"Speed reading", new Integer(20), new Boolean(true)},
//				{"Joe", "Brown",
//				"Pool", new Integer(10), new Boolean(false)}
//				};		
//
////		int row =  optionStrategy.getListOptLeg().size();
////		tempObj = new Object[row][5];
////		
////		int nRowCount = 0;
////		
////		for (OptionLeg element : optionStrategy.getListOptLeg()) {
////			Object rowData[] = new Object[5];
////			rowData[0]= element.getiPosition();
////			rowData[1]= element.getiType();
////			rowData[2]= element.getnPrice();
////			rowData[3]= element.getnStrike();
////			tempObj[nRowCount][0] = rowData;
////			nRowCount++;
////		}	
//		
//		
//	}
//	
////    public void addRow(RowData rowData) {
////        if (rowData == null) {
////            throw new IllegalArgumentException("rowData cannot be null");
////        }
////        Vector<Object> rowVector = new Vector<>();
////        rowVector.add(rowData.getName());
////        rowVector.add(rowData.getCc());
////        rowVector.add(rowData.getAge());
////        rowVector.add(rowData.getPhone());
////        rowVector.add(rowData.getDate());
////        rowVector.add(rowData.getAmount());
////        rowVector.add(rowData.getCc());
////
////        super.addRow(rowVector);
////    }		
//	
//
//	
//}
