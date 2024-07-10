package battleship;

import java.lang.reflect.Array;
import java.util.*;
import java.util.Scanner;
// import java.util.Random;
import java.util.Arrays; 

public class Battleship {

	public static void main(String[] args) throws InterruptedException  
	{
		//initialize variables
		Scanner myInput = new Scanner(System.in);
		int decision = 0; //decision made from menu
		int numPlayer = 0; //number of players
		int attempt1 = 0; //# of attempts made to destroy all ships by user 2
		int attempt2 = 0; //# of attempts by user 1
		int winner = 0; //the winner (for parameter)
		String playAgain = "NO"; //if the player(s) want to play again?

		do {
			//reset values
			//if played again, variables need to be reset.
			decision = 0; 
			numPlayer = 0;
			attempt1 = 0;
			attempt2 = 0;
			winner = 0;		
			
			//introduction - print menu and options the user can choose from
			Methods.textWriter("###########################################################", 3);
			Methods.textWriter("                          Welcome", 3);
			Methods.textWriter("                             to", 3);
			Methods.textWriter("                        Battleships!", 3);
			Methods.textWriter("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~", 3);
			Methods.textWriter("    -1. Player vs. Player           -2. Player vs. AI      ", 3);
			Methods.textWriter("###########################################################", 3);
			
			//ask user to choose from menu option 
			Methods.textWriter("Please choose one of the options above to start:", 10);
			while(true) { //check if the user chooses from the option
				decision = Methods.GetInt(); //by use of method, get integer rather than string
				if (decision == 1 || decision == 2) { //check the range
					break; //if so, break (== in the right range)
				} else { //when user does not choose from the option
					Methods.textWriter("Invalid input, please put correct value again.", 10);
					continue; //continue until the right value is entered
				} //end if & else
			} //end while loop
			
			//print rule
			//depending on which one the play chose,the rule slightly changes
			//Therefore, by use of method Rules, print the correct rule for each one. 
//			Methods.Rules(decision); 
//			System.out.println(); //for spacing in the console
			
			//play options
			switch (decision) {
			case 1: //player vs. player
				//Collect names of all the users
				while(true) {
					Methods.textWriter("How many players? (Please type 2)", 10); //add more players later
					numPlayer = Methods.GetInt(); //collect integer from the user
					if (!(numPlayer == 2)) { //this version of battleship is only compatible with two players
						Methods.textWriter("Sorry, this version of battleship is only available for two players.", 10);
						continue; //continue until the user inputs 2
					} else { //else = when user puts 2
						break; //break the loop
					} //end if & else
				} //end while loop
				String [] names = Methods.collectNames(numPlayer); //collect names of user by method
		
				//print instructions
				System.out.println();
				Methods.textWriter("Let's keep is going.", 10);
				Methods.textWriter("Round 1:", 10);
				Methods.textWriter(names[0] +" will be placing battleships and " + names[1] + " will find them!", 10);
				System.out.println();
				
				//locate battleships
				String [][] UserBoard = Methods.createEmptyBoard(); //create a new board
				UserBoard = Methods.resetBoard(UserBoard); //reset the board (replay null by space)
				
				Methods.textWriter(names[0] +"! The sea is empty so far.", 10);
				UserBoard = Methods.locateBattleship(UserBoard); //this method allows user to locate battleships
				
				//print instructions
				Methods.Spacing(); //space method to prevent another user from looking at the board
				Methods.textWriter("Alright, " + names[0] + "!", 10);
				Methods.textWriter("Let's start battleships", 10);
				
				//Attack
				System.out.println();
				Methods.textWriter(names[1] + ", choose a spot to attack!", 10);
				
				String [][] AttackBoard = Methods.createEmptyBoard(); //create a new board for the another user to see
				AttackBoard = Methods.resetBoard(AttackBoard); //reset the attack board
				
				do { //repeat until all the ships are sinked
					Methods.Guess(UserBoard, AttackBoard); //method to guess and find all of the ships
					attempt1++; //count # of attempts
					if (Methods.AllSink(AttackBoard) == true) { //check if all boats are sinked
						Methods.textWriter("Congratulations! You have found all battleships!", 10);
						break; //break the loop
					} else {
						continue; //continue looping
					} //end if & else
				}while(true); //end do while (guessing)
				
				Methods.textWriter("The total attempt taken by " + names[0] + " is " + attempt1, 10); //display # of attempts taken
				System.out.println(); //spacing
				
				//----------------------------------------------------------------------------
				
				//round 2
				Methods.textWriter("Now, it is " + names[1] + "'s turn to hide battleships.", 10);
				Methods.textWriter(names[0] + " will be finding baltteships!", 10);
				System.out.println();
				
				//locate battleships 
				Methods.textWriter("###########################################################", 5);
				System.out.println();
				UserBoard = Methods.resetBoard(UserBoard); //reset the UserSoard
				
				Methods.textWriter(names[1] +"! The sea is empty so far.", 10);
				UserBoard = Methods.locateBattleship(UserBoard); //method to locate ships into the board
				
				//print instructions
				Methods.Spacing(); //spacing method
				Methods.textWriter("Alright, " + names[1] + "!", 10);
				Methods.textWriter("Let's start round 2!", 10);
				
				//Attack
				System.out.println();
				Methods.textWriter(names[0] + ", choose a spot to attack!", 10);
				
				AttackBoard = Methods.resetBoard(AttackBoard); //reset AttackBoard
				
				do { //repeat until all the ships are sinked
					Methods.Guess(UserBoard, AttackBoard); //method to guess and find all of the ships
					attempt2++; //count # of attempts
					if (Methods.AllSink(AttackBoard) == true) { //check if all boats are sinked
						Methods.textWriter("Congratulations! You have found all battleships!", 10);
						break; //break the loop
					} else {
						continue; //continue looping
					} //end if & else
				}while(true); //end do while (guessing)
				
				Methods.textWriter("The total attempt taken by " + names[1] + " is " + attempt2, 10); //display # of attempts taken
				System.out.println(); //spacing
				
				//Winner
				Methods.textWriter("###########################################################", 5);
				System.out.println();
				Methods.textWriter("THE WINNER IS.......", 20);
				winner = Methods.findWinner(attempt1, attempt2); //collect winner from the method
				
				if (!(winner == -1)) { //if it is not tight
					System.out.println(names[winner] + "!"); //display the winner
					System.out.println("CONGRATULATIONS!!!");
				} //end if
				System.out.println(); //spacing
				break; //close case 1
			
			
			
			//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			
			
			
			case 2:
				
				//initialize variable
				attempt1 = 0; //user's attempt
				attempt2 = 0; //AI's attempt
				numPlayer = 1; //since it is player vs. AI.
				//the # of player is 1
				
				//collect names
				String [] name = Methods.collectNames(numPlayer);
				System.out.println(); //spacing
				
				//locate battleships - USER
				String[][] Board = Methods.createEmptyBoard(); //create a new board
				Board = Methods.resetBoard(Board); //reset the board -> ships are located
				
				Methods.textWriter(name[0] +"! The sea is empty so far.", 10);
				Board = Methods.locateBattleship(Board); //allow user to locate battleship with method
				
				//AI - board
				String [][] AI = Methods.createEmptyBoard(); //AI create a new board
				AI = Methods.resetBoard(AI); //AI reset the board
				AI = Methods.generate(AI);//AI place ships
					
				//Attack (Player vs. AI)
				//initialize boards
				String [][] Attack = Methods.createEmptyBoard(); //attack board for user
				Attack = Methods.resetBoard(Attack);
				String [][] AttackAI = Methods.createEmptyBoard(); //attack board for AI
				AttackAI = Methods.resetBoard(AttackAI);
				
				Methods.Spacing();
				do { //repeat until all the ships are sinked
					Methods.textWriter("###########################################################", 5);
					Methods.textWriter(name[0] +"! The following is AI's board.", 10);
					Methods.textWriter(name[0] + ", choose a spot to attack!", 10);
					System.out.println();
					
					Methods.Guess(AI, Attack); //method to guess and find all of the ships
					Methods.textWriter("###########################################################", 5);
					Methods.textWriter("The following is your board where AI attacks.", 10);
					Methods.AttemptsAI(AI, AttackAI, Board); //method where AI chooses to attack
					Methods.print(Board); //show the board attacked
					System.out.println();
					Methods.textWriter("AI finished attack.", 10);
					System.out.println();
					
					if (Methods.AllSinkAI(Attack, Board) == 0) { //check if all boats are sinked for user
						Methods.textWriter("###########################################################", 5);
						Methods.textWriter("Congratulations! You have found all battleships!", 10);
						break; //break the loop
					} else if (Methods.AllSinkAI(Attack, Board) == 1 ) { //for AI
						Methods.textWriter("###########################################################", 5);
						Methods.textWriter("The AI destroyed all of your ships!", 10);
						Methods.textWriter("AI won.", 10);
						break;
					} else if (Methods.AllSinkAI(Attack, Board) == 5) {
						Methods.textWriter("###########################################################", 5);
						Methods.textWriter("It is TIE!", 10);
						break;
					} else { //else -> there are ships remaining
						continue; //continue looping
					} //end if & else
				}while(true); //end do while (guessing)
				
				break; //close case 2
			} //end switch
				
			System.out.println(); //spacing
			do { //repeat until the right value is entered 
				System.out.println("Do you want to play again?:");
				playAgain = myInput.next(); //collect value
				if (playAgain.equalsIgnoreCase("YES") || playAgain.equalsIgnoreCase("NO")) { 
					break; //if right input, break loop
				} else { //if not right, 
					System.out.println("Please enter either \"YES\" or \"NO\".");
					continue; //continue loop
				} //end if & else
			} while(true); //end do while(true) for playAgain
			System.out.println(); //spacing
			
		} while(playAgain.equalsIgnoreCase("YES")); //repeat as long as user wants to keep it going
		
		//Outro
		System.out.println("Thank you for enjoying battleship! ");
		
	} //end main
		
} //end class