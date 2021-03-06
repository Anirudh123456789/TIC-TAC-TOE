//aNIRUDH hARIbASKAR
import java.awt.*;
import hsa.Console;

public class ArrayTicTac 
{
     public int[][] gameTracker = new int [3][3];  //The array that truly tracks the progress of the game and it is made public to be 
     //acessed by the main method and other classes
     private int col = 0;  //attributes of the array include the row, column and signature values
     private int row = 0;
     private int signature = 0;
     
 
       
     
     public ArrayTicTac(){
     
     }
     //Method initializes every array position and is called at the begining of every game 
     public void initialize()
     {
          for (int i = 0; i < gameTracker.length ; i++)
          {
                for (int j = 0; j < gameTracker.length ; j++)
                {
                     gameTracker[i][j] = 0;
                }
          }
     }
   
     //method, validates and locks array positions requested by users so it can not be reacessed during a game
     public void lock(Console c,Console j, int row, int col, int signature, Font font, int pointer, String choice, String status){
          String lockState = "fail"; //variable that assists in the looping code that reprompts for correct user inputs
          int point = 0;  // variable guides the supporting static method to draw X or O's at the right position
          row = row;
          col = col;
          signature = signature;
          
          GameBoard game1 = new GameBoard(); //creates an object of teh gameboard class (the display board)
          
          while (lockState.equals("fail"))
          {
               if (point == 1)
               {
                   
                    col = validate(c,1); //always revalidates user input
                  
                    row = validate(c,2);
               }
               
               
               if (gameTracker[row-1][col-1] == 0)
               {     //only imprints the signature of teh party if the array position is vaccant
                    gameTracker[row-1][col-1] = signature;
                    lockState = "sucess";
                    pointer = setPointer(row,col,pointer);  //guides the draw method on where to draw based on coordinates
                    game1.draw(j,font,pointer,choice);      //draws at the requested coordinates
                    
               }
               else
               {
                    
                    c.println ("\nThis location has already been Taken, TRY AGAIN");
                    point = 1;
                    
                     
          }
              }
     }
     //method validates the random cordinates generated by teh computer and only imprints signature if the coordinates are vaccant
     public int lockComputer (Console j, int row, int col, int signature, Font font, int pointer, String choice)
     {
          String lockState = "fail"; //loop variable
          int point = 0, x = 0;
          row = row;
          col = col;
          signature = signature;
           GameBoard game1 = new GameBoard();
           
            while (lockState.equals("fail"))
            {
               if (point == 0){
                   
                    col = (int)(Math.random()*3)+1; //generating a random number from 1 to 3 instead of asking for user input
                  
                    row = (int)(Math.random()*3)+1;}
               
               
               if (gameTracker[row-1][col-1] == 0)
               {       //only works if array is vaccant
                    gameTracker[row-1][col-1] = signature;
                    lockState = "sucess";
                    pointer = setPointer(row,col,pointer);
                    game1.draw(j,font,pointer,choice);
                    point = 1; //commands lop to break
                    x = 1;   
                    //
               }
               else
               {
                    
                    
                    point = 0;
                    x = 0;
                     
          }
            }
          return (x);   //returns a guidance value to the artificial intelligence to the main method to instruct the loop containing the method to break
     }
     
     
     
     //method takes in the coordintes inputted by teh user and generated by teh computer the guide the draw method to draw in the appropriate 
     //array location
     public static int setPointer (int row,int col, int pointer){
          if (col == 1 && row == 1)
          {
               pointer = 1;}
          if (col== 1 && row == 2)
          {
               pointer = 2;}
          if (col== 1 && row == 3)
          {
               pointer = 3;}
          if (col==2 && row==1)
          {
               pointer = 4;}
          if (col==2 && row==2)
          {    //sets pointer based on coordinates
               pointer = 5;}
          if (col==2 && row==3)
          {
               pointer = 6;}
          if (col==3 && row==1)
          {
               pointer = 7;}
          if (col== 3 && row==2)
          {
               pointer = 8;}
          if (col==3 && row==3)
          {
               pointer = 9;}
          return (pointer);
          }
     
     // Supporting static Method validates user data inputted after they have been deferred to the lock array method
     //Seeing how they can make an error here as well and crash the program
     public static int validate (Console c, int a)
     {
          int numb;
          String valid;
          while (true)
          {
               try
               {
                    if (a == 1)
                    {
                         c.print("please enter x coordinates again: ");}
                         else
                         {
                              c.print("please enter y coordinates again: ");}
                    valid = c.readLine();
                    numb = Integer.parseInt(valid);  //converts string to number
                    if (numb >=1 && numb <=3){
                         break;
                    }
                    else
                    {
                         c.println ("\nRemember that Tic Tac Toe has 3 rows and 3 columns\n");
                    }
               }
               catch (NumberFormatException e)
               {
                    c.println ("\nEnsure you enter a number\n");
               }
          }
          return (numb);  //returns the validated number before being called as an array ocation 
          }
     
     
//______________________________________________________________________________________________________________________  
     
     //intelligent Method essentially runs through every possibility that may lead to the computer being beaten or winning and 
     //places its position appropriately based on these predetermined conditions
     
     public int intelligence (Console j,Font font, String choice)
     {                               
          int pointer = 0, proceed = 0; //variables essentially tell teh program weather or not to proceed to check other conditions
          GameBoard game1 = new GameBoard();
          
          //Program examines possibilities in the array row wise
          if (gameTracker[0][1] + gameTracker[2][1] == 10 || gameTracker[0][1] + gameTracker[2][1] == 2)
          {
              row = 2;   //coordinates are pre determined
              col = 2;
              if (gameTracker[row][col] == 0)
              {         //position only played if array is vacant
              pointer = setPointer(row,col,pointer);    // pointer is set
              game1.draw(j,font,pointer,choice);        //position is drawn on display
              proceed = 1;                              //all other conditions unfulfilled
              gameTracker [row-1][col-1] = 1;}}         // signature is imprinted in array
          if (proceed != 1)
          {                            
               if (gameTracker[0][1] + gameTracker[1][1] == 10 || gameTracker[0][1] + gameTracker[1][1] == 2)
               {
              row = 3;
              col = 2;
              if (gameTracker[row][col] == 0)
              {
              pointer = setPointer(row,col,pointer);
              game1.draw(j,font,pointer,choice);
              proceed = 1;
              gameTracker [row-1][col-1] = 1;}
               }
          }
          if (proceed != 1)
          {                             //program only checks conditions if the variable is not a certain vallue
               if (gameTracker[1][1] + gameTracker[2][1] == 10 || gameTracker[1][1] + gameTracker[2][1] == 2){
              row = 1;
              col = 2;
              if (gameTracker[row][col] == 0)
              {
              pointer = setPointer(row,col,pointer);
              game1.draw(j,font,pointer,choice);
              proceed = 1;
              gameTracker [row-1][col-1] = 1;}
               }
          }
          
          
          if (proceed !=1)
          {
               if (gameTracker[0][0] + gameTracker[1][0] == 10 || gameTracker[0][0] + gameTracker[1][0] == 2)
               {
                    row = 3;
                    col = 1;
                    if (gameTracker[row][col] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[2][0] + gameTracker[1][0] == 10 || gameTracker[2][0] + gameTracker[1][0] == 2)
               {
              row = 1;
              col = 1;
              if (gameTracker[row][col] == 0)
              {
              pointer = setPointer(row,col,pointer);
              game1.draw(j,font,pointer,choice);
              proceed = 1;
              gameTracker [row-1][col-1] = 1;}
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[0][0] + gameTracker[2][0] == 10 || gameTracker[0][0] + gameTracker[2][0] == 2)
               {
                    row = 2;
                    col = 1;
                    if (gameTracker[row][col] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;}
               }
          }
          
          
           if (proceed !=1)
           {
               if (gameTracker[0][2] + gameTracker[1][2] == 10 || gameTracker[0][2] + gameTracker[1][2] == 2)
               {
                    row = 3;
                    col = 3;
                    if (gameTracker[row][col] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;}
               }
           }
          if (proceed != 1)
          {
               if (gameTracker[2][2] + gameTracker[1][2] == 10 || gameTracker[2][2] + gameTracker[1][2] == 2)
               {
                    row = 1;
                    col = 3;
                    if (gameTracker[row][col] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;}
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[0][2] + gameTracker[2][2] == 10 || gameTracker[0][2] + gameTracker[2][2] == 2)
               {
                    row = 2;
                    col = 3;
                    if (gameTracker[row][col] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;}
               }
          }
     
            
          
          //program checks all posibilities column wise
           if (proceed !=1)
           {
               if (gameTracker[0][0] + gameTracker[0][2] == 10 || gameTracker[0][0] + gameTracker[0][2] == 2)
               {
                    row = 1;
                    col = 2;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;}
               }
           }
          if (proceed != 1)
          {
               if (gameTracker[0][0] + gameTracker[0][1] == 10 || gameTracker[0][0] + gameTracker[0][1] == 2)
               {
                    row = 1;
                    col = 3;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[0][2] + gameTracker[0][1] == 10 || gameTracker[0][2] + gameTracker[0][1] == 2)
               {
                    row = 1;
                    col = 1;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;}
               }
          }
          
          
          if (proceed !=1)
          {
               if (gameTracker[1][0] + gameTracker[1][2] == 10 || gameTracker[1][0] + gameTracker[1][2] == 2){
                    row = 2;
              
                    col = 2;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;}
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[1][0] + gameTracker[1][1] == 10 || gameTracker[1][0] + gameTracker[1][1] == 2)
               {
                    row = 2;
                    col = 3;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[1][1] + gameTracker[1][2] == 10 || gameTracker[1][1] + gameTracker[1][2] == 2)
               {
                    row = 2;
                    col = 1;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          
          
          if (proceed !=1)
          {
               if (gameTracker[2][0] + gameTracker[2][2] == 10 || gameTracker[2][0] + gameTracker[2][2] == 2)
               {
                    row = 3;
                    col = 2;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[2][1] + gameTracker[2][2] == 10 || gameTracker[2][1] + gameTracker[2][2] == 2)
               {
                    row = 3;
                    col = 1;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[2][0] + gameTracker[2][1] == 10 || gameTracker[2][0] + gameTracker[2][1] == 2)
               {
                    row = 3;
                    col = 3;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          
          //Program checks all posibilities diagnolly
           if (proceed !=1)
           {
               if (gameTracker[2][2] + gameTracker[1][1] == 10 || gameTracker[2][2] + gameTracker[1][1] == 2)
               {
                    row = 1;
                    col = 1;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
           }
          if (proceed != 1)
          {
               if (gameTracker[0][0] + gameTracker[1][1] == 10 || gameTracker[0][0] + gameTracker[1][1] == 2)
               {
                    row = 3;
                    col = 3;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[2][2] + gameTracker[0][0] == 10 || gameTracker[2][2] + gameTracker[0][0] == 2)
               {
                    row = 2;
                    col = 2;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          
         //positions are checked diagnolly
          
            if (proceed !=1)
            {
               if (gameTracker[2][0] + gameTracker[1][1] == 10 || gameTracker[2][0] + gameTracker[1][1] == 2)
               {
                    row = 1;
                    col = 3;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
            }
          if (proceed != 1)
          {
               if (gameTracker[1][1] + gameTracker[0][2] == 10 || gameTracker[1][1] + gameTracker[0][2] == 2)
               {
                    row = 3;
                    col = 1;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          if (proceed != 1)
          {
               if (gameTracker[2][0] + gameTracker[0][2] == 10 || gameTracker[2][0] + gameTracker[0][2] == 2)
               {
                    row = 2;
                    col = 2;
                    if (gameTracker[row-1][col-1] == 0)
                    {
                         pointer = setPointer(row,col,pointer);
                         game1.draw(j,font,pointer,choice);
                         proceed = 1;         
                         gameTracker [row-1][col-1] = 1;
                    }
               }
          }
          return (proceed);
     }     
     //the guidance variable is returned to the variable set to this method in oreder to tell the program weather or not to defer
     //to generating random coordinates. If a position is played, the computer will not do so but it wil if this is not teh case. 
                                    
         
     
    //__________________________________________________________________________________________________________________  
     //Method determines the winner by receiving a copy of the array and outputs the appropriate messages based on teh game mode
     public void whoWins(Console c, String state, int[][] a)
     { //a is received from main which uses public array
          int accumulator = 0; // variable that accumulates signature sums
         
          int gameStatus = 0; //variable determines if the program should bother checking other conditions if a win has been declared
          //program runs through array columns 
          for (int i = 0;i < a.length; i++)
          {
               for (int j = 0; j < a.length; j++)
               {
                    accumulator+= a[i][j];  //accuulates signatures in array positions
               }
          gameStatus = setGameStatus(c, accumulator, state); //set to method that outputs teh appropriate message based on value of accumulator
           
          accumulator = 0;  //reinitializes accumulator for next condition          
          }
          if (gameStatus != 1 ||gameStatus != 2 ||gameStatus != 3 )
          { //if no winnrs are detected
               //program runs through array rows
               for (int i = 0;i < a.length; i++)
               {
               for (int j = 0; j < a.length; j++)
               {
                    accumulator+= a[j][i];
               }
          gameStatus = setGameStatus(c, accumulator, state);
           
          accumulator = 0;
         
               }
          }
           //program runs through array laterally on one side
           if (gameStatus != 1 ||gameStatus != 2 ||gameStatus != 3)
           {
                    accumulator+= a[0][0]+ a[1][1]+ a[2][2];
                    gameStatus = setGameStatus(c, accumulator, state);
                    
                    accumulator = 0;
           }
           
          //program runs through array laterally on other side
           if (gameStatus != 1 ||gameStatus != 2 ||gameStatus != 3)
           {
                    accumulator+= a[2][0]+ a[1][1]+ a[0][2];
                    gameStatus = setGameStatus(c, accumulator, state);
                     
                    accumulator = 0;
           }
          
            
     }
     //static method supports previous non static method by displaying the appropriate output based on accumulator values
     public static int setGameStatus(Console c, int accumulator, String state){
          int gameStatus; // returns who won in integer values
          
          if (accumulator == 3)
          {  //player 1 victory if multiplayer mode
               if (state.equalsIgnoreCase("m"))
               {
                    c.println ("PLAYER 1 WINS");
                    gameStatus = 1;
               } //player 1 as an integer
                    
               if (state.equalsIgnoreCase("s"))
               { //computer victory in single player mode 
                    c.println ("COMPUTER WINS");
                    gameStatus = 3;
               }  //computer as an integer
                    
                    
          }
          if (accumulator == 15)
          {              //player 2 victory in either modes
               c.println ("PLAYER 2 WINS");
               gameStatus = 2; //player 2 as an integer
              
          }
          else
          {
               gameStatus = 0;
          } //no winner detected
          
          return (gameStatus);    //returns the party that won as an integer value
      
}
                      
}           