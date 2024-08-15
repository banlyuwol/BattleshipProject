
public class prototype {

	public static void main(String[] args) 
	{
	String [][] array = new String [8][8];
	array = resetBoard(array);
	
	surround(array);
	
	} //end main 
	
	public static String[][] resetBoard(String [][] array) {
		//initialize variables
		String Empty = " ";

		//for each row
		for (int row = 0; row < array.length; row++) {
			//for each col
			for ( int col = 0; col < array[row].length; col++) {
				//to fill space in each column
				array[row][col] = Empty;
			} //end for col
		} //end for row

		return array; //return array filled with spaces

	} //end parameter
	
	public static void print (String [][] array)
	{
		String [] COLUMN = {"A", "B", "C", "D", "E", "F", "G", "H"}; //header for column
		String EMPTY = " "; 
		System.out.println("   1  2  3  4  5  6  7  8 "); //header for row
		//for each row in md array
		for(int row = 0; row < array.length; row++)
		{
			System.out.print(COLUMN[row] + EMPTY); //header for column
			//for each element in the current row print the current column
			for(int col = 0; col < array[row].length; col++)
			{
				if (array[row][col].equalsIgnoreCase("H")) { //if "H" is stored
					System.out.print("[" + array[row][col] + "]"); //print RED
				} else if (array[row][col].equalsIgnoreCase("M")) { //if "M" is stored
					System.out.print("[" + array[row][col] + "]"); //print YELLOW
				} else if (array[row][col].equalsIgnoreCase("B")) { //if "B" is stored
					System.out.print("[" + array[row][col] + "]"); //print CYAN
				} else { 
					System.out.print("[" + array[row][col] + "]"); //print normally
				} //end if & else
			} //end for col
			System.out.println(); //moves cursor to a new line
		} //end for row

	}//end print md array
		
//		String [] COLUMN = {"A", "B", "C", "D", "E", "F", "G", "H"}; //header for column
//		String EMPTY = " "; 
//		String RESET = "\u001B[0m"; //back to black
//		String RED = "\u001B[31m"; //red -> hit
//		String YEL = "\u001B[33m"; //yellow -> miss
//		String CYAN = "\u001B[36m"; //boat
//
//		System.out.println("   1  2  3  4  5  6  7  8 "); //header for row
//		//for each row in md array
//		for(int row = 0; row < array.length; row++)
//		{
//			System.out.print(COLUMN[row] + EMPTY); //header for column
//			//for each element in the current row print the current column
//			for(int col = 0; col < array[row].length; col++)
//			{
//				if (array[row][col].equalsIgnoreCase("H")) { //if "H" is stored
//					System.out.print("[" + RED + array[row][col] + RESET + "]"); //print RED
//				} else if (array[row][col].equalsIgnoreCase("M")) { //if "M" is stored
//					System.out.print("[" + YEL + array[row][col] + RESET + "]"); //print YELLOW
//				} else if (array[row][col].equalsIgnoreCase("B")) { //if "B" is stored
//					System.out.print("[" + CYAN + array[row][col] + RESET + "]"); //print CYAN
//				} else { 
//					System.out.print("[" + array[row][col] + "]"); //print normally
//				} //end if & else
//			} //end for col
//			System.out.println(); //moves cursor to a new line
//		} //end for row
//
//	}//end print md array
	
	public static void surround (String[][] array)
	{
		//initialize variables
		int row;
		int col;
		int boats = 3;
		
		row = 3;
		col = 5;
		
		for(int i = 0; i < boats; i++) {
			array[row + i][col] = "B";
		} //end for loop
		
		print(array);
		
		return;
	} // end surround
	
} //end main
