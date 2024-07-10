package battleship;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;
import java.util.Random;

public class Methods {

	/* Name: GetInt
	 * Description: Get correct input (integer).
	 * 				if string is entered, print "invalid input" and let the user re-type until the correct value
	 * Parameters: N/A
	 * Returns: int User (= the right input)
	 */
	public static int GetInt() throws InterruptedException {
		// initialize variables
		Scanner myInput = new Scanner(System.in); //open scanner for input
		int User = 0;

		do {
			try { //try & catch to get "integer", not string
				User = myInput.nextInt(); //if integer is entered, collect it
				System.out.println();
				break; //then break the loop to return the value
			} catch (Exception e) {
				myInput.nextLine(); //if string is entered, collect it 
				System.out.println(); //and print invalid input, continue the loop
				textWriter("Invalid input, please put correct value again.", 10);	
			} //end try & catch
		} while(true); //end do while loop

		return User; //return the value

	} //end parameter



	/* Name: Rules
	 * Description: print rules regarding to the decision made by user 
	 * Parameters: int decision
	 * Returns: N/A
	 */
	public static void Rules(int decision) throws InterruptedException {

		if (decision == 1 ) { //Player vs. Player
			//System.out.println();
			textWriter("Here are the rules: ", 10);
			textWriter("There is a 8x8 square grid, where each player can hide their battleships.", 10);
			textWriter("The first player places their battleships first.", 10);
			textWriter("Player 2 picks a certain part (1x1) in the grid to attack.", 10);
			textWriter("It counts how many attempts player 2 takes to destroy all the battleships.", 10);
			textWriter("Now, it is player 2’s turn to hide their battleships.", 10);
			textWriter("Then player 1 attacks each spot until all the ships are destroyed.", 10);
			textWriter("Who takes less attempts to destroy battleships wins!", 10);

		} else if (decision == 2 ) { //Player vs. AI
			textWriter("Here are the rules", 10); 
			textWriter("There is a 8x8 square grid, where you and AI can hide the battleships.", 10);
			textWriter("Then you and AI will have a turn to attack each other's grid.", 10);
			textWriter("Whoever found all of battleships will win the game!", 10);
		} //end if & else
		return;
	} //end parameter



	/* Name: collectNames
	 * Description: Collect users' name in an array and return the array
	 * Parameters: int numPlayer
	 * Returns: String [] p (name array)
	 */
	public static String[] collectNames(int numPlayer) throws InterruptedException {
		//initialize variables
		String []p = new String[numPlayer]; //array to collect names
		Scanner myInput = new Scanner(System.in); //open scanner for input

		for(int i = 0; i < numPlayer; i++) { //repeat collecting until all the players' names are collected
			textWriter("What is the name of Player " + (i+1) + "?", 10);
			p[i] = myInput.nextLine(); //collect
			System.out.println(); //spacing
		} //end for loop

		for (int i = 0; i< numPlayer; i++) { //display their name(s)
			System.out.println("Player" + (i+1) +": " +p[i]); //print collected values
		} //end for loop

		return p; //return name array 
	} //end parameter



	/* Name: createEmptyBoard
	 * Description: Create new empty board
	 * Parameters: N/A
	 * Returns: String [][] board (emnpty)
	 */
	public static String[][] createEmptyBoard() {
		//initialize variables
		int size = 8; //the size of boat

		String [][] board = new String[size][size]; //create new board with right size

		return board; //return the empty array

	} //end parameter



	/* Name: resetBoard
	 * Description: The empty arrays are filled with "null"s, therefore reset the board with space: " "
	 * Parameters: String [][] array
	 * Returns: String [][] array
	 */
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



	/* Name: print
	 * Description: print the board with elements in it 
	 * Parameters: String [][] array
	 * Returns: N/A
	 */
	public static void print (String [][] array)
	{
		String [] COLUMN = {"A", "B", "C", "D", "E", "F", "G", "H"}; //header for column
		String EMPTY = " "; 
		String RESET = "\u001B[0m"; //back to black
		String RED = "\u001B[31m"; //red -> hit
		String YEL = "\u001B[33m"; //yellow -> miss
		String CYAN = "\u001B[36m"; //boat

		System.out.println("   1  2  3  4  5  6  7  8 "); //header for row
		//for each row in md array
		for(int row = 0; row < array.length; row++)
		{
			System.out.print(COLUMN[row] + EMPTY); //header for column
			//for each element in the current row print the current column
			for(int col = 0; col < array[row].length; col++)
			{
				if (array[row][col].equalsIgnoreCase("H")) { //if "H" is stored
					System.out.print("[" + RED + array[row][col] + RESET + "]"); //print RED
				} else if (array[row][col].equalsIgnoreCase("M")) { //if "M" is stored
					System.out.print("[" + YEL + array[row][col] + RESET + "]"); //print YELLOW
				} else if (array[row][col].equalsIgnoreCase("B")) { //if "B" is stored
					System.out.print("[" + CYAN + array[row][col] + RESET + "]"); //print CYAN
				} else { 
					System.out.print("[" + array[row][col] + "]"); //print normally
				} //end if & else
			} //end for col
			System.out.println(); //moves cursor to a new line
		} //end for row

	}//end print md array



	/* Name: index
	 * Description: change string to integer / find index of column in the following array and return the index value
	 * Parameters: String column
	 * Returns: int index
	 */
	public static int index(String ROW) {
		//initialize variables
		String [] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H"}; //array with alphabets with order

		for (int index = 0; index<alphabet.length; index++) { //find the index of column
			if (alphabet[index].equalsIgnoreCase(ROW)) { //check if the given string in in the alphabet array
				return index; //if so, return its index/position from the array
			} //end if
		} //end for loop

		//if the column and elements in the array don't match, it means incorrect value is entered
		return -1; //Therefore, return -1

	} //end parameter 



	/* Name: locateBattleship
	 * Description: collect row, column, and placement (required information for placing ships), then connect to another method to place ships. 
	 * Parameters: String [][] board
	 * Returns: String [][] board
	 */
	public static String[][] locateBattleship (String [][] array) throws InterruptedException {
		//initialize variables
		Scanner myInput = new Scanner(System.in); //open scanner 
		int row = 0; 
		int col = 0;
		String ROW = ""; //variable to store string input
		String placement = ""; //variable to store string input for placement
		int [] boats = {4, 3, 3, 2, 2, 2}; //the size of boats in order
		String RESET = "NO"; //reset the board
		String Layer = ""; //variable to check layered boats

		do { //loop to reset board later on
			array = resetBoard(array); //METHOD - reset board
			for (int i = 0; i < boats.length; i++) { //loop until all the required boats are placed. 
				print(array);
				//reset variables to 0 or ""
				row = 0;
				col = 0;
				ROW = "";
				placement = "";

				do { //loop until get the right value either H or V
					System.out.println(); //spacing
					textWriter("Type in \"V\" or \"H\" for vertical or horizontal placement of battleship of size " + boats[i] + ":", 10);
					textWriter("Consider that it will be placed from top-left.", 10);
					placement = myInput.next();
					System.out.println(); //spacing
					if (placement.equalsIgnoreCase("V") || placement.equalsIgnoreCase("H")) { 
						break; //if the entered value is either V or H, break the loop
					} else {
						textWriter("Please type either \"V\" or \"H\".", 10); 
						continue; //continue the loop to get right value
					} //end if & else
				} while(true); //successfully collected PLACEMENT 

				if (placement.equalsIgnoreCase("H")) { //if horizontal placement
					do { //repeat until get the valid value for row
						do { //repeat until get the number between 1 ~ 8
							textWriter("Pick a column (1~8):", 10);
							col = GetInt(); //get integer by using GETINT method
							if (col <= 8 && col >= 1) { //check if row is in a range of 1 ~ 8
								break; //break the loop
							} else { 
								textWriter("Please pick a value between 1 to 8.", 10);
								continue; //continue the loop
							} //end if & else
						} while(true); //end do while loop 


						if (ValidPlacement_H(col, boats[i]) == true) { //check the validity of row for horizontal placement					
							break; //break the loop if valid
						} else { //if not valid
							textWriter("It does not fit to the grid. Please try again.", 10);
							System.out.println();
							continue; //continue the loop
						} //end if & else
					} while(true); //end do while, completed collecting appropriate value for row
					col = col - 1; //the board starts with 0~7, therefore decrease 1 value for right placement

					do { //repeat until get the valid value for column
						textWriter("Pick a row (A~H):", 10); 
						ROW = myInput.next(); //collect STRING column
						row = index(ROW); //find the index of column, as well as if it is among A ~ H
						if (row == -1) { //getting -1 as index, meaning the value collected is not among A ~ H
							textWriter("Please choose from A ~ H.", 10);
							System.out.println(); 
							continue; //continue the loop
						} else {
							break; //meaning right value is collected, breaking the loop
						} //end if & else
					} while(true); //collected appropriate value for column

				} else { //if vertical placement
					do { //loop until get the right # for row
						textWriter("Pick a column (1~8):", 10);
						col = GetInt(); //get integer
						if (col <= 8 && col >= 1) { //check the value is in the right range
							break; //it is in the right range, breaking the loop
						} else { //if not in the range
							textWriter("Please pick a value between 1 to 8.", 10);
							continue; //continue the loop to get another input
						} //end if & else
					} while(true); //end loop for row, meaning got appropriate value
					col = col - 1; //the board starts with 0~7, therefore decrease 1 value for right placement

					do { //loop until getting the valid value for column
						do { //repeat until getting a value between A ~ H
							textWriter("Pick a row (A~H):", 10);
							ROW = myInput.next(); //collect STRING
							row = index(ROW); //find the index of column, as well as if it is among A ~ H
							if (row == -1) { //getting -1 as index, meaning the value collected is not among A ~ H
								textWriter("Please choose from A ~ H.", 10);
								System.out.println();
								continue; //repeat the process
							} else {
								break; //break loop
							} //end if & else
						} while(true); //end do while loop - input is between A ~ H

						if (ValidPlacement_V(row, boats[i]) == true) { //check the validity for vertical placement for collected coordinate					
							break; //if valid, break the loop
						} else { //if not valid
							textWriter("It does not fit to the grid. Please try again.", 10);
							System.out.println();
							continue; //continue the loop
						} //end if & else
					} while(true); //end do while loop, meaning appropriate coordinate is entered
				} //end if & else
				
				if (CheckLayered(row, col, placement, boats[i], array) == true) { //BOOLEAN - to check if there is any boats layered
					Layer = "YES"; //change variable to "YES"
					break; //break loop to reset 
				} //end if
				
				array = placed(row, col, placement, boats[i], array); //METHOD - placing ships in the coordinate given for the correct placement
				
			} //end for loop - until all the boats are placed

			
			if (Layer.equalsIgnoreCase("YES")) { //if there is any boats layered
				System.out.println(); //spacing
				textWriter("The battleships are layered. Reset is required.", 10);
				textWriter("Please place ships without getting layered.", 10);
				System.out.println();
				RESET = "YES"; //reset is required
			} else { //no layers
				System.out.println();
				textWriter("This is your board after all the placements:", 10);
				print(array); //show final appearance of board after all placements
				
				do { //loop until get input of either YES or NO		
					System.out.println();
					textWriter("Want to reset the board? (Yes/No): ", 10);
					RESET = myInput.next(); //collect STRING
					if (RESET.equalsIgnoreCase("YES") || RESET.equalsIgnoreCase("NO")) { 
						break; //if right input, break loop
					} else { //if not right, 
						textWriter("Please type \"YES\" or \"NO\".", 10);
						continue; //continue loop
					} //end if & else
				} while(true); //end do while loop
			} //end if & else

		} while(RESET.equalsIgnoreCase("YES")); //end RESET loop

		return array; //return board filled with battleships

	} //parameter



	/* Name: placed
	 * Description: According to type of placement, locate boards
	 * Parameters: int row, int col, String placement, int boats, String [][] board
	 * Returns: String [][] board with battleships placed
	 */
	public static String[][] placed(int row, int col, String placement, int boats, String [][] array) {
		//initialize variables
		String Ship = "B";

		if (placement.equalsIgnoreCase("H")) { //if horizontal
			for (int i = 0; i<boats; i++) {
				array[row][col+i] = Ship; //by moving cursor to right to place ships
			} //end for loop
		} else if (placement.equalsIgnoreCase("V")) { //if vertical
			for (int i = 0; i<boats; i++) {
				array[row+i][col] = Ship; //by moving cursor to down to place ships
			} //end for loop
		} //end if & else
		return array;
	} //end parameter

	
	
	/* Name: ValidPlacement_H
	 * Description: Check if the chosen location/placement is valid (for horizontal)
	 * Parameters: int col, int boats
	 * Returns: true or false
	 */
	public static boolean ValidPlacement_H (int col, int boats) {
		//initialize variables
		int size = 8; //size of board

		if (col > ((size - boats) + 1)) { //from the column chosen, check if there is enough places to store boats
			return false; //there is not
		} //end if

		return true; //there is

	} //end parameter

	
	
	/* Name: ValidPlacement_V
	 * Description: Check if the chosen location/placement is valid (for vertical)
	 * Parameters: int row, int boats
	 * Returns: true or false
	 */
	public static boolean ValidPlacement_V (int row, int boats) {
		//initialize variables
		int size = 8; //size of board

		if (row > ((size - boats) + 1)) { //from the column chosen, check if there is enough places to store boats
			return false; //there is not
		} //end if

		return true; //there is

	} //end parameter

	
	
	/* Name: CheckLayered
	 * Description: Check if boats are layered
	 * Parameters: String [][] array
	 * Returns: true or false
	 */
	public static boolean CheckLayered (int row, int col, String placement, int boats, String [][] array) {
		//initialize variables
		String Ship = "B";

		if (placement.equalsIgnoreCase("H")) { //if horizontal
			for (int i = 0; i<boats; i++) {
				if (array[row][col+i].equals(Ship)) { //by moving cursor to right to place ships, check if any boats are stored
					return true; //if so, return true to indicate that it is layered
				} //end if
			} //end for loop
		} else if (placement.equalsIgnoreCase("V")) { //if vertical
			for (int i = 0; i<boats; i++) {
				if (array[row+i][col].equals(Ship)) { //by moving cursor to down to place ships, check if any boats are stored
					return true; //indicating that it is
				} //end if
			} //end for loop
		} //end if & else
		return false;
	} //end parameter


	
	/* Name: Guess
	 * Description: Ask and collect row and column from the user to attack/guess a spot
	 * 				It attacks that spot and shows if it is miss, hit, or taken attempts
	 * Parameters: String [][] array, String [][] array1, int attempt
	 * Returns: N/A
	 */
	public static void Guess (String [][] array, String [][] array1) throws InterruptedException {
		//initialize variables
		Scanner myInput = new Scanner(System.in); //open scanner
		boolean AllSink = false; //boolean to check if all the ships are sinked
		int row = 0; //row
		int col = 0; //column
		int i = 0; //counter for attempts
		String ROW = ""; //a variable to store string value from the user
		String result = ""; //results if it is miss or hit

		do { //repeat until appropriate value for column is entered
			print(array1); //show the user how the board looks like
			textWriter("Pick a column (1~8):", 10);
			col = GetInt(); //ensure to get integer
			if (col <= 8 && col >= 1) { //check the boundary of input
				break; //if so, break / out of loop
			} else { //if not
				textWriter("Invalid input. Please try again. ", 10);
				continue; //repeat the loop to get right value
			} //end if & else
		} while(true); //end do while to get correct column value
		col = col - 1; //subtract 1 from it to match the position in array

		do { //repeat until appropriate row value is entered
			textWriter("Pick a row (A~H):", 10);
			ROW = myInput.next(); //collect string
			row = index(ROW); //check if correct string is entered
			if (row == -1) { //if the value received from index method is "-1"
				textWriter("Invalid input. Please try again.", 10); //it is incorrect value
				System.out.println();
				continue; //looping
			} else { //else, got the correct value
				break; //break the loop
			} //end if & else
		} while(true); //get the right value for row

		System.out.println(); //spacing
		result = attack(row, col, array, array1); //compare AttackBoard and board to check the result
		if (result.equals("T")) { //if T is received, taken attempt
			textWriter("Already taken attempts.", 10);
		} else if(result.equals("H")) { //HIT
			textWriter("The result is...HIT! You successfully destroyed a boat.", 15);
			array1 = display(row, col, result, array1); //display HIT into AttackBoard 
		} else if (result.equals("M")) { //MISS
			textWriter("The result is...MISS! Try again!", 15); 
			array1 = display(row, col, result, array1); //display MISS into AttackBoard
		} // end if
		System.out.println(); //spacing

		return; //return 

	} //end parameter

	
	
	/* Name: Attack
	 * Description: Compare board where ships are hidden and AttackBoard where the another user attacks on, to determine MISS, HIT, or TAKEN ATTEMPT
	 * Parameters:int row, int col, String [][] board, String [][] AttackBoard
	 * Returns: String result
	 */
	public static String attack(int row, int col, String [][] board, String [][] AttackBoard) {
		//initialize variables
		String result = "";
		String EMPTY = " ";

		if (!(AttackBoard[row][col].equalsIgnoreCase(EMPTY))) { //if AttackBoard is not empty, it means the attempt is already taken
			result = "T"; //already taken attempt
		} else if (board[row][col].equalsIgnoreCase("B")) { //if AttackBoard is empty, then check board to determine HIT & MISS
			result = "H"; //HIT
		} else { //out of all possibility would be "MISS"
			result = "M"; //MISS
		} //end if & else
		
		return result; //return result
	} //end parameter

	
	
	/* Name: display
	 * Description: Compare board where ships are hidden and AttackBoard where the another user attacks on, to determine MISS, HIT, or TAKEN ATTEMPT
	 * Parameters:int row, int col, String result, String [][] UserBoard
	 * Returns: String [][] UserBoard
	 */
	public static String[][] display(int row, int col, String result, String [][] UserBoard) {

		UserBoard[row][col] = result; //store result into the UserBoard

		return UserBoard;
	} //end parameter


	
	/* Name: AllSink
	 * Description: Check if all boats are sinked
	 * Parameters: String [][] AttackBoard
	 * Returns: true or false
	 */
	public static boolean AllSink (String [][] AttackBoard) {
		//initialize variables
		int i = 0; //count # of battleships present on the AttackBoard

		//for each row
		for(int row = 0; row < AttackBoard.length; row++)
		{   //row each col
			for(int col = 0; col < AttackBoard[row].length; col++)
			{   // if AttackBoard has "H", counts
				if (AttackBoard[col][row].equals("H")) {
					i++; //counter
				} //end if
			} //end for loop (col)
		} //end for loop (row)

		if ( i == 16 ) { //if the counter equals # of boats, which is 16
			return true; //all boats are sinked
		} //end if

		return false; //there are boats remaining

	} //end parameter



	/* Name: findWinner
	 * Description: based on the attempts users have, determine the winner
	 * Parameters: int attempt1, int attempt2
	 * Returns: int 0, 1, or -1
	 */
	public static int findWinner(int attempt1, int attempt2) throws InterruptedException {

		if (attempt1 < attempt2) {
			return 0; //the position of names array, player 1
		} else if (attempt1 > attempt2) {
			return 1; //player 2
		} else if (attempt1 == attempt2) {
			Thread.sleep(20); //slow down the printing
			textWriter("IT IS TIE!", 10); //tight 
			return -1; //none
		} //end if else

		return -1; //none
	} //end parameter
	
	
	
	/* Name: textWriter
	 * Description: add text writing effect
	 * Parameters: String text, int t
	 * Returns: N/A
	 */
	public static void textWriter (String text, int t) throws InterruptedException { 
		//initialize variables
		char [] charArray = text.toCharArray(); //change string into characteristics

		for (int i = 0; i < text.length(); i++) { //loop until all the characteristics are done
			System.out.print(charArray[i]); //print each characteristic
			Thread.sleep(t); //pause
		} //end for loop

		System.out.println(); //move cursor

		return; 
	} //end parameter	

	
	
	/* Name: Spacing
	 * Description: long spacing to block another user to see a board with battleships hidden (when it is right after locating battleships)
	 * Parameters: N/A
	 * Returns: N/A
	 */
	public static void Spacing () { 
		//initialize variables
		int i = 0; //counter
		
		while (i < 30) { //continue until i equals 30
			System.out.println(); //spacing
			i++; //add 1 in i value
		} //end while loop

		return;
	} //end parameter	



	/* Name: generate
	 * Description: User randomizer to allow AI to pick a place to place all ships
	 * Parameters: String [][] AI
	 * Returns: String [][] AI
	 */
	public static String[][] generate(String[][] AI) {
		// initialize variables
		Random rand = new Random(); //open randomizer
		int row = 0; 
		int col = 0;
		int [] boats = {4, 3, 3, 2, 2, 2}; //# and size of boats
		int placem = 0; //placement (vertical or horizontal) where ai will randomly choose
		String placement = "";
		String RESET = ""; 

		for (int i = 0; i < boats.length; i++) { //repeat until all of the boats are placed
			placem = (int) (Math.random()* (2)) -1; //randomizer between 1 to 2

			if (placem == 1) { // if 1 -> vertical placement
				placement = "V";
			} else { //if 2 -> horizontal placement
				placement = "H";
			} //end if & else

			if (placement.equals("V")) { //vertical placement
				do { //repeat until get valid coordinate for vertical placement
					row = rand.nextInt(8) + 1; //create random number for row
					col = rand.nextInt(8) + 1; //create random number for column
					if (ValidPlacement_V(row, boats[i]) == true) { //check if the given coordinate is valid for vertical
						break; //if so, break
					} else { //if not valid, continue the loop to get valid coordinate
						continue;
					} //end if & else
				} while(true); //end do while(true) -> finish of vertical placement
			} else { //horizontal placement
				do { //repeat until get valid coordinate for horizontal placement
					row = rand.nextInt(8) + 1; //create random number for row
					col = rand.nextInt(8) + 1; //create random number for column
					if (ValidPlacement_H(col, boats[i]) == true) { //checkk if the given coordinate is valid for horizontal
						break; //if so, break
					} else { //if not valid, continue to loop to get valid coordinate
						continue; 
					} //end if & else
				} while(true); //end do while(true) -> finish of horizontal placement
			} //end if & else
			row = row - 1; //subtract 1 to fit into grid
			col = col - 1; //subtract 1 to fit into grid

			if (OverLap(row, col, placement, boats[i], AI) == true) { //check if anything overlaps
				AI = placed(row, col, placement, boats[i], AI); //place ships into the grid
			} else { //if overlap
				i--; //replace the same size of ships
			} //end if & else

		} //end for loop

		return AI;
	} //end parameter


	
	/* Name: OverLap
	 * Description: Check if the given spots are overlapping with another ship
	 * Parameters: int row, int col, String placement, int boats, String [][] array
	 * Returns: true or false
	 */
	public static boolean OverLap(int row, int col, String placement, int boats, String [][] array) {
		//initialize variables
		String Ship = "B";

		if (placement.equalsIgnoreCase("H")) { //when horizontal placement
			for (int i = 0; i<boats; i++) { //for every step/unit going right
				if (array[row][col+i].equals(Ship)) { //check if there is ship placed
					return false; //if so, return false
				} //end if
			} //end for loop
		} else if (placement.equalsIgnoreCase("V")) { //when vertical placement 
			for (int i = 0; i<boats; i++) { //for every step/unit going down
				if (array[row+i][col].equals(Ship)) { //check if there is ship placed
					return false; //if so, return false
				} //end if
			} //end for
		} //end if & else

		return true; //nothing overlaps
	} //end parameter
	
	
	
	/* Name: AttemptsAI
	 * Description: Let AI picks a random spot to attack
	 * Parameters: String[][] AI, String [][] AttackAI, String [][] Board
	 * Returns: String [][] AI
	 */
	public static String[][] AttemptsAI(String[][] AI, String [][] AttackAI, String [][] Board) throws InterruptedException {
		// initialize variables
		Random rand = new Random(); //open randomizer
		boolean AllSink = false; //boolean to check if all the ships are sinked
		int row = 0; //row
		int col = 0; //column
		String result = ""; //results if it is miss or hit

		//let AI choose random number between 1 to 8 for row and column
		row = rand.nextInt(8) + 1;
		col = rand.nextInt(8) + 1;

		//subtract 1 from row and col to match them into grid
		row = row - 1;
		col = col - 1;

		System.out.println(); //spacing
		result = attack(row, col, Board, AttackAI); //compare User's Board and AttackAIboard to check the result
		if (result.equals("T")) { //if T is received, taken attempt
			textWriter("AI attacked the same spot.", 10);
		} else if(result.equals("H")) { //HIT
			textWriter("AI...HIT! one of your ships!", 15);
			AttackAI = display(row, col, result, AttackAI); //display HIT into AttackAI
			Board = display(row, col, result, Board); //display HIT into Board
		} else if (result.equals("M")) { //MISS
			textWriter("AI...MISSED!", 15); 
			AttackAI = display(row, col, result, AttackAI); //display MISS into AttackAI
			Board = display(row, col, result, Board); //display MISS into Board
		} // end if
		System.out.println(); //spacing

		return AI;
	} //end parameter



	/* Name: AllSinkAI
	 * Description: Check if all ships are sinked for Player vs. AI version
	 * Parameters: String[][] Attack, String[][] Board
	 * Returns: return -1, 0, 1, or 5.
	 */
	public static int AllSinkAI(String[][] Attack, String[][] Board) {
		//initialize variables
				int user = 0; //count # of battleships present on the AttackBoard
				int ai = 0; //count for AI
				
				//checking for user
				//for each row
				for(int row = 0; row < Attack.length; row++)
				{   //row each col
					for(int col = 0; col < Attack[row].length; col++)
					{   // if AttackBoard has "H", counts
						if (Attack[row][col].equals("H")) {
							user++; //counter
						} //end if
					} //end for loop (col)
				} //end for loop (row)
				
				//checking for AI
				//for each row
				for(int row = 0; row < Board.length; row++)
				{   //row each col
					for(int col = 0; col < Board[row].length; col++)
					{   // if AttackBoard has "H", counts
						if (Board[row][col].equals("H")) {
							ai++; //counter
						} //end if
					} //end for loop (col)
				} //end for loop (row)
				
				if ( ai == 16 && ai == user ) { //when all ships are sank, but # of attempts of ai and user are the same
					return 5; //return 5 to indicate tie
				} else if ( user == 16 ) { //if the counter equals # of boats, which is 16 
					return 0; //all boats are sank ( player won )
				} else if ( ai == 16 ) { //if the counter for AI equals # of boats
					return 1; //return 1 which indicates AI 
				} //end if & else

				return -1; //there are boats remaining

			} //end parameter
	
} //end class
