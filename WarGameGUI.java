/*
Jonathon Cahoon
CS110
Assignment 10
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**WarGameGUI class creates a GUI for the WarGame class
*/

public class WarGameGUI
{
   private WarGame game; //game functionality
   private JFrame mainFrame; //mainframe everything is displayed on
   //labels to display cards and statuses (p2 = computer)
   private JLabel status, p1Card, p2Card, p1Deck, p2Deck;
   private JLabel p1Winnings, p2Winnings, p1War, p2War, p1, p2;
   private JLabel p1WinCount, p2WinCount, p1HandCount, p2HandCount;
   //button to let the user play or quit
   private JButton newGame, flipCard, quit;
   private boolean roundEnd; //shows if a player has won the round
   
   /**WarGameGUI creates the GUI to play the game of war
   */
   
   public WarGameGUI()
   {
      mainFrame = new JFrame("Jon's Game of War");//create mainFrame
      mainFrame.setSize(800,1000);//set default size of frame
      
      //create GridBagLayout to organize the game interface
      GridBagLayout  layout = new GridBagLayout();
      GridBagConstraints gbc = new GridBagConstraints();
      mainFrame.setLayout(layout);
      mainFrame.setBackground(Color.black);//set background color
      
      //if the window is closed the program terminates
      mainFrame.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent windowEvent)
         {
            System.exit(0);
         }
      });
      
      //create default game status
      status = new JLabel("Start a new game");
      status.setForeground(Color.red); //set color of text
      status.setFont(new Font("ARIAL", Font.PLAIN, 50)); //set font
      gbc.gridx = 0; //grid row
      gbc.gridy = 0; //grid column
      gbc.gridwidth = 5; //amount of columns the status occupies
      gbc.weighty = 1.0; //extra space created between buttons and status
      mainFrame.add(status, gbc);
      
      //add buttons
      newGame = new JButton("New Game");
      newGame.addActionListener(new ButtonListener());
      gbc.fill = GridBagConstraints.HORIZONTAL;//place text in the cell
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 1; //reset width and height to only 1 row and column
      gbc.weighty = 0; //resent extra space between elements in grid
      mainFrame.add(newGame, gbc);//add to mainFrame
      
      flipCard = new JButton("Play Card");
      flipCard.addActionListener(new ButtonListener());
      gbc.gridx = 1;
      gbc.gridy = 1;
      gbc.gridwidth = 2;
      mainFrame.add(flipCard, gbc);
      
      quit = new JButton("Quit");
      quit.addActionListener(new ButtonListener());
      gbc.gridx = 3;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      mainFrame.add(quit, gbc);
      
      //add labels for cards and board information
      p2WinCount = new JLabel("Cards Won: ");
      p2WinCount.setForeground(Color.red);
      gbc.fill = GridBagConstraints.CENTER; //place text in center of grid
      gbc.gridx = 1;
      gbc.gridy = 2;
      gbc.weighty = 0;
      mainFrame.add(p2WinCount, gbc);
      
      p2HandCount = new JLabel("Cards in Hand: ");
      p2HandCount.setForeground(Color.red);
      gbc.gridx = 2;
      gbc.gridy = 2;
      mainFrame.add(p2HandCount, gbc);
      
      p2 = new JLabel("Computer");
      p2.setFont(new Font("ARIAL", Font.PLAIN, 30));
      p2.setForeground(Color.red);
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridx = 0;
      gbc.gridy = 3;
      gbc.ipadx = 10;
      gbc.ipady = 20;
      mainFrame.add(p2, gbc);
         
      p2Deck = new JLabel(new ImageIcon("cardpics/empty.jpg"));
      gbc.fill = GridBagConstraints.BOTH;
      gbc.gridx = 2;
      gbc.gridy = 3;
      mainFrame.add(p2Deck, gbc);
         
      p2Winnings = new JLabel(new ImageIcon("cardpics/empty.jpg"));
      gbc.gridx = 1;
      gbc.gridy = 3;
      mainFrame.add(p2Winnings, gbc);
         
      p2Card = new JLabel(new ImageIcon("cardpics/empty.jpg"));
      gbc.gridx = 3;
      gbc.gridy = 3;
      mainFrame.add(p2Card, gbc);
                  
      p1Card = new JLabel(new ImageIcon("cardpics/empty.jpg"));
      gbc.gridx = 3;
      gbc.gridy = 4;
      mainFrame.add(p1Card, gbc);
      
      p1 = new JLabel("Player");
      p1.setForeground(Color.red);
      p1.setFont(new Font("ARIAL", Font.PLAIN, 30));
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridx = 0;
      gbc.gridy = 4;
      mainFrame.add(p1, gbc);
         
      p1Deck = new JLabel(new ImageIcon("cardpics/empty.jpg"));
      gbc.fill = GridBagConstraints.BOTH;
      gbc.gridx = 2;
      gbc.gridy = 4;
      mainFrame.add(p1Deck, gbc);
         
      p1Winnings = new JLabel(new ImageIcon("cardpics/empty.jpg"));
      gbc.gridx = 1;
      gbc.gridy = 4;
      mainFrame.add(p1Winnings, gbc);
      
      p1WinCount = new JLabel("Cards Won: ");
      p1WinCount.setForeground(Color.red);
      gbc.fill = GridBagConstraints.CENTER;
      gbc.gridx = 1;
      gbc.gridy = 5;
      mainFrame.add(p1WinCount, gbc);
      
      p1HandCount = new JLabel("Cards in Hand: ");
      p1HandCount.setForeground(Color.red);
      gbc.gridx = 2;
      gbc.gridy = 5;
      mainFrame.add(p1HandCount, gbc);
      
      
      //show the GUI when called   
      mainFrame.setVisible(true);;
   }
   
   /**showDecks updates the image for the player's hand
      based on the amount of cards in the pile
   */
   
   public void showDecks()
   {
      if(game.getP1Hand() > 15)
         p1Deck.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
      else if(game.getP1Hand() > 5)
         p1Deck.setIcon(new ImageIcon("cardpics/deckmed.jpg"));
      else if(game.getP1Hand() > 1)
         p1Deck.setIcon(new ImageIcon("cardpics/decksmall.jpg"));
      else if(game.getP1Hand() > 0)
         p1Deck.setIcon(new ImageIcon("cardpics/back.jpg"));
      else
         p1Deck.setIcon(new ImageIcon("cardpics/empty.jpg"));
                     
      if(game.getP2Hand() > 15)
         p2Deck.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
      else if(game.getP2Hand() > 5)
         p2Deck.setIcon(new ImageIcon("cardpics/deckmed.jpg"));
      else if(game.getP2Hand() > 1)
         p2Deck.setIcon(new ImageIcon("cardpics/decksmall.jpg"));
      else if(game.getP2Hand() > 0)
         p2Deck.setIcon(new ImageIcon("cardpics/back.jpg"));
      else
         p2Deck.setIcon(new ImageIcon("cardpics/empty.jpg"));
   }  
   
   /**showWinnings updates the images for the winnings pile of both
      players based on how many cards are in the winnings pile
   */
   
   public void showWinnings()
   {                
      if(game.getP1Winnings() > 15)
         p1Winnings.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
      else if(game.getP1Winnings() > 5)
         p1Winnings.setIcon(new ImageIcon("cardpics/deckmed.jpg"));
      else if(game.getP1Winnings() > 1)
         p1Winnings.setIcon(new ImageIcon("cardpics/decksmall.jpg"));
      else if(game.getP1Winnings() > 0)
         p1Winnings.setIcon(new ImageIcon("cardpics/back.jpg"));
      else
         p1Winnings.setIcon(new ImageIcon("cardpics/empty.jpg"));
                     
      if(game.getP2Winnings() > 15)
         p2Winnings.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
      else if(game.getP2Winnings() > 5)
         p2Winnings.setIcon(new ImageIcon("cardpics/deckmed.jpg"));
      else if(game.getP2Winnings() > 1)
         p2Winnings.setIcon(new ImageIcon("cardpics/decksmall.jpg"));
      else if(game.getP2Winnings() > 0)
         p2Winnings.setIcon(new ImageIcon("cardpics/back.jpg"));
      else
         p2Winnings.setIcon(new ImageIcon("cardpics/empty.jpg"));
   }
   
   /**cardsWon updates the text showing how many cards each player
      has in their winnings pile
   */
   
   public void cardsWon()
   {

      p1WinCount.setText("Cards Won: " + game.getP1Winnings());
      p2WinCount.setText("Cards Won: " + game.getP2Winnings());
   }
   
   /**cardsHand updates the text showing how many cards each player
      has in their hand
   */
   
   public void cardsHand()
   {
      p1HandCount.setText("Cards in Hand: " + game.getP1Hand());
      p2HandCount.setText("Cards in Hand: " + game.getP2Hand());
   }  
   
   /**gameOver disables the play card button when a player wins and 
      displays the winner
   */
      
   public void gameOver()
   {
      status.setIcon(null); //remove image in status
      flipCard.setEnabled(false); //disable flipCard button
      //remove cards from being in play
      p1Card.setIcon(new ImageIcon("cardpics/empty.jpg"));
      p2Card.setIcon(new ImageIcon("cardpics/empty.jpg"));
         
      //upate cards in players hands and winnings images and text
      cardsWon();
      cardsHand();      
      showDecks();
      showWinnings();
         
      //display winner of the game      
      if(game.winner() == 1)
         status.setText("Player Wins!");
      else
         status.setText("Computer Wins!");
   }
    
         
   /**ButtonListener class handles button events
   */
   
   private class ButtonListener implements ActionListener
   {
      /**Handles the event of a button being pressed in WarGameGUI
      */
      public void actionPerformed(ActionEvent e) 
      {
         //start new game
         if(e.getSource() == newGame)
         {
            //show that it is a new game
            game = new WarGame();
            status.setText("New Game");
            roundEnd = false;
            
            //reset all images and text
            status.setIcon(null);
            flipCard.setEnabled(true);

            p1Deck.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
            p2Deck.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
            p1Winnings.setIcon(new ImageIcon("cardpics/empty.jpg"));
            p2Winnings.setIcon(new ImageIcon("cardpics/empty.jpg"));
            p1Card.setIcon(new ImageIcon("cardpics/empty.jpg"));
            p2Card.setIcon(new ImageIcon("cardpics/empty.jpg"));
            flipCard.setText("Play Card");
            
            cardsWon();
            cardsHand();
            
            showDecks();
            showWinnings();
            
         }
         //flip card for each round
         else if(e.getSource() == flipCard)
         {
            //try catch block to make sure a game has started
            try
            {
               //update images and text for cards in winnings pile
               cardsWon();
               showWinnings();
               
               //if the game is not in war play a card face up
               if(!game.warStatus())
               {
                  status.setIcon(null);//reset status icon to null
                  
                  //if a player's hand is empty shuffle the cards
                  //and update the hand and winnings images and text
                  if(game.getP1Hand() == 0 || game.getP2Hand() == 0)
                  {
                     game.handShuffle();
                     cardsWon();
                     showWinnings();
                     showDecks();
                     cardsHand();
                  }
                  
                  //if it is not the end of a round play next round
                  if(!roundEnd)
                  {
                     //each player plays a card and update the image for the card
                     game.playRound();
                     p1Card.setIcon(new ImageIcon("cardpics/" + game.getP1Card().shortString() + ".jpg"));
                     p2Card.setIcon(new ImageIcon("cardpics/" + game.getP2Card().shortString() + ".jpg"));
                     cardsHand();
                     showDecks();
                  
                     //display who won the round        
                     if(game.getP1Card().getRank() > game.getP2Card().getRank())
                     {
                        status.setText("Player wins the round");
                        flipCard.setText("Take Cards");//set new instruction on button
                        roundEnd = true;
                     }
                  
                     else if(game.getP1Card().getRank() < game.getP2Card().getRank())
                     {
                        status.setText("Computer wins the round");
                        flipCard.setText("Take Cards");
                        roundEnd = true;
                     }
                     
                     //if cards are equal display the game goes to war
                     else
                     {
                        status.setText(null);
                        status.setIcon(new ImageIcon("cardpics/war.jpg"));
                        //change button to play a card face down
                        flipCard.setText("Play Card Face Down");//set new instructions on button
                        roundEnd = false;
                     }
                  }
                  
                  //if it is the end of a round clear the cards in play
                  else
                  {
                     roundEnd = false;
                     p1Card.setIcon(new ImageIcon("cardpics/empty.jpg"));
                     p2Card.setIcon(new ImageIcon("cardpics/empty.jpg"));
                     flipCard.setText("Play Card"); //reset button to play card
                  }
                  
               }
                  
               //if in war play a card face down
               else
               {
                  //if a player's hand is empty shuffle the cards
                  //and update the hand and winnings images and text
                  if(game.getP1Hand() == 0 || game.getP2Hand() == 0)
                  {
                     game.handShuffle();
                     cardsWon();
                     showWinnings();
                     showDecks();
                     cardsHand();
                  }
                  
                  game.war();//play card face down
                  flipCard.setText("Play Card");//reset button to play card
                  p1Card.setIcon(new ImageIcon("cardpics/back.jpg"));
                  p2Card.setIcon(new ImageIcon("cardpics/back.jpg"));
                  //update cards in hand image and text
                  cardsHand();
                  showDecks();
               }
               
               //if a player has won end the game
               if(game.winner() == 1 || game.winner() == 2)
                  gameOver();
            }
            
            catch(NullPointerException error)
            {
               status.setText("You must start a new game!");
            }
         }
         
         //quit if quit button is clicked
         else if(e.getSource() == quit)
            System.exit(0);
      }
   }


   /**main method calls the GUI and plays the game of war
   */
   
   public static void main(String[] args)
   {
      WarGameGUI gui = new WarGameGUI();
   }
}
     