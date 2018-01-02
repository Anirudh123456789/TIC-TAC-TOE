//Tic tac toe gameBoard
//Supports Main program
import java.awt.*;
import hsa.Console;
public class GameBoard
{
  
    
  
  
  public GameBoard()
  { 
    
    
  }
  public void drawBoard(Console j, Font font2)
  {
   j.setFont(font2);
   j.drawRect (50,50,150,150);
   j.drawString ("Y",0,50); j.drawString ("--",0,75);
   j.drawString ("1",0,165);
   
   j.drawRect (50,200,150,150);
   j.drawString ("2",0,320);
   j.drawRect (50,350,150,150);
   j.drawString ("1",135,550);
   
    j.drawRect (200,50,150,150);
    j.drawString ("3",0,445);
    j.drawString ("X|", 50,550);
    
    j.drawRect (200,200,150,150);
    
    
    j.drawRect (200,350,150,150);
    j.drawString ("2",275,550);
    
    j.drawRect (350,50,150,150);
    
    j.drawRect (350,200,150,150);
    
    j.drawRect (350,350,150,150);
    j.drawString ("3",450,550);
    
  
  
  }
  
  public void draw(Console j, Font font,  int pointer,String playerChoice)
  {
        j.setFont(font);
        if (pointer == 1)
        {
             j.drawString (playerChoice,85,165);}
        if (pointer == 2)
        {
             j.drawString (playerChoice,85,340);}
        if (pointer == 3)
        {
             j.drawString (playerChoice,85,465);}
        if (pointer == 4)
        {
             j.drawString (playerChoice,235,165);}
        if (pointer == 5)
        {
             j.drawString (playerChoice,235,340);}
        if (pointer == 6)
        {
             j.drawString (playerChoice,235,465);}
        if (pointer == 7)
        {
             j.drawString (playerChoice,385,165);}
        if (pointer == 8)
        {
             j.drawString (playerChoice,385,340);}
        if (pointer == 9)
        {
             j.drawString (playerChoice,385,465);}
  }
   
    
   

  
  
  } 


       
 
       
