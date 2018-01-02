//16th June 2017
//Tic Tac Toe Game
// Anirudh Haribaskar
//Program allows users to play a game of Tic Tac toe against the computer and a second human player if requested

import java.awt.*;
import hsa.Console;
import java.io.*;
public class GameMain {
     static Console c;
     static Console j;
     
     public static void main(String[] args) { 
          j = new Console(16);                         //Setting console that displays game board size to 16
          c = new Console(15);                         //Console that interacts with player
          
          //Initializing fonts for both consoles
          Font font = new Font ("SansSerif", Font.BOLD, 150);
          Font font2 = new Font ("SansSerif", Font.BOLD, 50);
          
          //Initializing the variables that store information for the many loops and data validation methods
          String state = "", rageQuit = "", wrongChoice = "wrong", difficulty = "e", again = "y", rules = "";      
          
          //The signature variable, holds a certain value to be marked on the 2D array based on who is playing and where
          //columns and rows hold coordinate values, rest are pointer variables that lead methods in desired directions
          int pointer = 0, signature = 0, row = 0, col =0, validatePointer = 0, winner = 0, rounds = 0, coinToss = 0, x = 0, proceeder = 0;
          int computerWins=0, player1Wins=0, player2Wins=0, properState = 0; //Tracking the wins of each party in an integer
          int[][] k;         //intented to hold a copy of the 2D array
          
          String player1 ="", player2 = "", computer = ""; //records if each party is X or O
          
          GameBoard game1 = new GameBoard();                //Making objects out of supporting classes
          ArrayTicTac array1 = new ArrayTicTac();
         
         
          while (again.equalsIgnoreCase("Y")){ //replay request game loop
               array1.initialize();            //Initializes the 2D array at the start of the loop
               
               // Proper Game Introductions
               c.println ("\t\t\t\tWELCOME TO THE TIC TAC TOE GAME");
               j.println ("\t\tTIC TAC TOE BOARD");
               c.println ("\n\nThe game will be played in this console \nThe board wil be displayed in the other");
               c.print ("\nPlease enter (R) for rules or hit any key to continue");
               rules = c.readLine();
               if (rules.equalsIgnoreCase("r")){ //rules stated
                    c.println ("1) The Objective of the game is to get 3 X or O in a row");
                    c.println ("2) This can be lateral, vertical or horizintal");
                    c.println ("3) You can choose to play both multi or single player and choose if you are x or o in both");
                    c.println ("4) in multiplayer, you verse another human player to complete objective 1");
                    c.println ("5) in single player, you can choose to verse an intelligent or non intelligent computer and compete to fulfill objective 1");
               }
               while (properState == 0){  //validating the input for state
                    c.print ("\n\nDo you want to play Multi or Single Player (M-Multi, S - for Single):");
                    state = c.readLine();
                    if (state.equalsIgnoreCase("m")||state.equalsIgnoreCase("s")){  //only single or multiplayer accepted
                      properState = 100;
                    }
                 else{
                      c.println ("SORRY?\n");
                 }
               }
               pause(game1, font2 ,"");  //Method clears the screen, allows user to wait and readys the j console
          
          while (rounds <= 5){                 //After 5 rounds, game is tied
               if (state.equalsIgnoreCase("M")){
                    c.println ("YOU ARE NOW ENTERING MULTIPLAYER MODE");
               if (rounds == 0){
                    while (wrongChoice.equals("wrong")){                              //data validation for player choice
                               //sets x or O position of player 1 and player 2
                              c.print ("\n\nPlayer1, do you want to be X or O:  ");   
                              player1 = c.readLine();
                              if (player1.equalsIgnoreCase("o")||player1.equalsIgnoreCase("x")){
                                   wrongChoice = "right";
                              }
                    }
                              if (player1.equalsIgnoreCase("x")){
                                   player2 = "O";
                              }
                              else{
                                   player2 = "X";
                              }
               }
                wrongChoice = "wrong";   
                row = 0; col = 0;      
               pause(game1, font2,"");  //clears screen and displays the game board
                    
                     //player 1 plays now
                    signature = 1;      //player 1 marks of a value of 1 on a desired location of 2D array every time they play
                    col = validate(1, signature);       //validates integer for rows and col
                    row = validate(0, signature);
                    array1.lock(c,j,row,col,signature, font, pointer, player1, state);      //locks the array position so it cant be re accesed
                    array1.whoWins(c,state,array1.gameTracker);          //checks if a party has won
                    
                    winner = breaker(array1.gameTracker);                   //determines if the loop needs to be broken if some one did win
                     if (winner == 1){
                         rounds = 1000;
                         player1Wins++;
                         break;
                     }
                    if (winner == 2){
                         rounds = 1000;
                         player2Wins++;
                         break;
                    }
                    rounds++;              //increases rounds after play
                    if (rounds == 5){      //Determines if a tie needs to be called
                         c.println ("Its a Tie");
                         break;
                    }
               }
               
               //if the choice was single player 
               if (state.equalsIgnoreCase("S")){
                    pause(game1, font2,"");  //pauses game
                    if (rounds == 0){
                         //Givesinstructions and calls for user to choice if tehy are x or o only at the first round
                         c.println ("YOU ARE NOW IN THE SINGLE PLAYER MODE");
                         while (wrongChoice.equals("wrong")){   // data is validated again
                              c.print ("\n\nPlayer, do you want to be X or O:  ");
                              player2 = c.readLine();
                              if (player2.equalsIgnoreCase("o")||player2.equalsIgnoreCase("x")){
                                   wrongChoice = "right";}}
                              if (player2.equalsIgnoreCase("x")){
                                   computer = "O";
                              }
                              else{
                                   computer = "X";
                              }
                              //sets difficulty mode
                              c.println ("PLAYER, do you want it easy or hard going against the computer \n(H-Hard) or any key for easy");
                              difficulty = c.readLine();
                              coinToss = (int)(Math.random()*2)+1;        //performs a cointoss to determine which party goes first
                              pause(game1, font2,"");
                              c.println ("Since the advantage is with the party that starts\n a coinToss will be held to determine the starter\nHead is your start and Tails the computer starts");
                    }
                     
                     proceeder = 0; //reinitializing variable that tells the computer weather or not to defer to randomly placing its turn after every round
                     if (coinToss == 2){
                          if (rounds == 0){
                               c.println (".\n.\n.\n.HEADS, You start Player");}
                          if (rounds > 0){        //computer starts only after player
                                x = 0;
                                while (x == 0){   //continues to loop untill computer plays one position
                                     if (difficulty.equalsIgnoreCase("h")){ //intelligence method called if hard difficuty was chosen
                                          proceeder = array1.intelligence(j, font, computer);}
                                     if (proceeder != 1){
                                         
                                     signature = 1;       //Compueters signature is 1 on the array as well                                   
                                     col = (int)(Math.random()*3)+1;       //random # from 1-3
                         
                                     row = (int)(Math.random()*3)+1; 
                                      x = array1.lockComputer(j,row,col,1,font, pointer,computer);  //validates the possibility for computer
                                     }
                                     if (proceeder == 1){
                                          break;}}
                               
                          } }
                     if (coinToss == 1){     //computer starts  instead                    
                          if (rounds == 0){
                               c.println (".\n.\n.\n.\nTAILS, sorry, the computer plays");}
                          
                          proceeder = 0;
                          x = 0;
                          while (x == 0){
                              if (difficulty.equalsIgnoreCase("h")){ 
                                   proceeder = array1.intelligence(j, font, computer);}
                              if (proceeder !=1){
                                  
                              signature = 1;                                          
                               col = (int)(Math.random()*3)+1;
                         
                               row = (int)(Math.random()*3)+1; 
                               x = array1.lockComputer(j,row,col,1,font, pointer,computer);
                              }
                              if (proceeder  == 1){
                                   break;} proceeder = 0;}
                                
                           winner = breaker(array1.gameTracker);  //checks if the loop needs to break due to a winner
                    
                    
                     }
                     }
               if (state.equalsIgnoreCase("s")){       
                    if (winner == 1){
                         rounds = 1000;
                         computerWins++;           //increase the wins of the computer if it wins
                         break;}
                    if (winner == 2){
                         rounds = 1000;
                         player2Wins++;           //increases the value of the player if they win
                         break;
                    }
               }
              
                    //player 2 if multiplayer, user in sigle player
                    signature = 5; //always carries a signature of 5 on the 2D array
                    col = validate(1,signature); //validates input of player 2
                    row = validate(0, signature);
                    array1.lock(c,j,row,col,signature,font, pointer, player2, "m");  //locks position
                    
                     if (state.equalsIgnoreCase("S")){
                         rounds++;      //increases rounds here and checks if its a Tie only during single player (or checks in player 1 area)
                         if (rounds == 5){
                              c.println ("ITS A TIE");
                              break;
                         }
                     }       
                    
                    
                    array1.whoWins(c,state,array1.gameTracker);
                    winner = breaker(array1.gameTracker);  //winner is detarmined and loop is broken
                    
                    if (winner == 1){
                         rounds = 1000;
                         player1Wins++;
                         break;
                    }
                    if (winner == 2){
                         rounds = 1000;
                         player2Wins++;
                         break;}
                   c.println ("Please hit enter");    //provides user with time to intake next information
                   c.readChar();
                    
                    
                    //user can choose to quit at end of every round
                    c.print("Do any of you wish to quit\t (Any key- No, Y - yes): ");
                    rageQuit = c.readLine();
                    if (rageQuit.equalsIgnoreCase("y")){
                         rounds = 100;
                         break;}}
               
               c.println ("Please hit enter");
               c.readChar();
               
               //displays game stats
               if (state.equalsIgnoreCase ("M")){
                    c.println ("Player 1 has " + player1Wins + " wins" + "\nPlayer 2 has " + player2Wins + " wins");}
               if (state.equalsIgnoreCase("s")){
                     c.println ("computer has " + computerWins + " wins" + "\nPlayer 2 has " + player2Wins + " wins");}
              
              //determines if user wants to replay
              pause(game1, font2,"");
               c.println ("\n\nDO YOU WISH TO PLAY THE GAME AGAIN (Y-Yes) or any key to exit");  
               again = c.readLine(); 
               rounds = 0;  //reinitializes values
               j.clear();
          
          }
     c.println ("THANKYOU FOR PLAYING TIC TAC TOE");}
     
     //Method clears screen, gives user time to process information adn readys teh j console
     public static void pause(GameBoard game1, Font font2, String playerChoice){
          c.clear();
          c.print("Please hit enter");
          c.readChar();
          game1.drawBoard(j,font2);
     }
     
     //method validates the numbers inputted as row and column before sending them to be locked on the array, returns the string number as an integer
     public static int validate (int validatePointer, int signature){
          int numb;
          String valid;
          while (true){
               try{
                    if (signature == 1){  //if it is player 1
                         if (validatePointer == 1){
                              c.print("Player 1, please enter x coordinates: ");}
                         else {
                              c.print("Player 1, please enter y coordinates:");}}
                    if (signature == 5){ //if it is player 2
                         if (validatePointer == 1){
                              c.print("   \n\nPlayer 2, please enter x coordinates: ");}
                         else {
                              c.print ("Player 2, please enter y coordinates:");}}
                    valid = c.readLine();
                    
                    numb = Integer.parseInt(valid);
                    if (numb >=1 && numb <=3){ //checks the range of number inputted
                         break;
                    }
                    else{
                         c.println ("\nRemember that Tic Tac Toe has 3 rows and 3 columns\n If u have Difficulty, refer to the game board labels");
                         c.println ("X is Horizontal\t Y is vertical");
                    }}
               catch (NumberFormatException e){  //checks if it even is a number
                    c.println ("\nEnsure you enter a number\n");
               }}
          return (numb); //returns string number as integer
     }
     
     //method acesses the public array in the arrayTi tac object and checks if the signatures add up to a certain number
     //if the number is triple a signature, the party that holds teh signatur ewins
      public static int breaker(int[][] a){
           //checks array column wise
          int accumulate = 0, winPointer = 0;
          for (int i = 0; i< 3; i++){
                         for (int j = 0; j<3; j++){                      
                              accumulate += a[i][j];
                              if (accumulate == 3){
                                   winPointer = 1;}     //checks signature sums
                              if (accumulate == 15){
                                   winPointer = 2;}
                              }
                         accumulate = 0;
          }
          //checks array colun wise and accumulates signature sums
           for (int i = 0; i< 3; i++){
                         for (int j = 0; j<3; j++){                      
                              accumulate += a[j][i];
                              if (accumulate == 3){
                                   winPointer = 1;}
                              if (accumulate == 15){
                                   winPointer = 2;}
                              }
                         accumulate  = 0;
          }
           //checks laterally and accumulates sums of signatures 
           if ((accumulate += a[2][2]+ a[1][1]+ a[0][0]) == 15){
                winPointer = 2;
                 }
           accumulate = 0;
           if ((accumulate += a[2][2]+ a[1][1]+ a[0][0]) == 3){
                winPointer = 1;}
           accumulate = 0;
           if ((accumulate += a[2][0]+ a[1][1]+ a[0][2]) == 15){
                winPointer = 2;}
           accumulate = 0;
            if ((accumulate += a[2][0]+ a[1][1]+ a[0][2]) == 3){
                winPointer = 1;} 
           accumulate = 0;
           
      return (winPointer);  //returns if a winner exists or not
      }
      
      }
         
     
     
     



