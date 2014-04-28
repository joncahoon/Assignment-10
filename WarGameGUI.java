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
   //labels to display cards and statuses
   private JLabel status, p1Card, p2Card, p1Deck, p2Deck;
   private JLabel p1Winnings, p2Winnings, p1War, p2War, p1, p2;
   private JLabel p1WinCount, p2WinCount, p1HandCount, p2HandCount;
   //button to let the user play or quit
   private JButton newGame, flipCard, quit;
   
   public WarGameGUI()
   {
      mainFrame = new JFrame("Jon's Game of War");//create mainFrame
      mainFrame.setSize(800,800);//set default size of frame
      
      //create GridBagLayout to organize the game interface
      GridBagLayout  layout = new GridBagLayout();
      GridBagConstraints gbc = new GridBagConstraints();
      mainFrame.setLayout(layout);
      
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
      status.setFont(new Font("ARIAL", Font.PLAIN, 50));
      gbc.gridx = 0; //grid row
      gbc.gridy = 0; //grid column
      gbc.gridwidth = 5; //amount of columns the status occupies
      gbc.weighty = 20; //extra space created between buttons and status
      mainFrame.add(status, gbc);
      
      //add buttons
      newGame = new JButton("New Game");
      newGame.addActionListener(new ButtonListener());
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 1; //reset width and height to only 1 row and column
      gbc.gridheight = 1;
      gbc.weighty = 10;
      mainFrame.add(newGame, gbc);
      
      flipCard = new JButton("Flip Card");
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
      gbc.gridx = 1;
      gbc.gridy = 2;
      gbc.weighty = 0;
      gbc.fill = GridBagConstraints.CENTER;
      mainFrame.add(p2WinCount, gbc);
      
      p2HandCount = new JLabel("Cards in Hand: ");
      gbc.gridx = 2;
      gbc.gridy = 2;
      mainFrame.add(p2HandCount, gbc);
      
      p2 = new JLabel("Player 2");
      p2.setFont(new Font("ARIAL", Font.PLAIN, 30));
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
      
      p1 = new JLabel("Player 1");
      p1.setFont(new Font("ARIAL", Font.PLAIN, 30));
      gbc.fill = GridBagConstraints.HORIZONTAL;
      gbc.gridx = 0;
      gbc.gridy = 4;
      gbc.gridwidth = 1;
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
      gbc.fill = GridBagConstraints.CENTER;
      gbc.gridx = 1;
      gbc.gridy = 5;
      mainFrame.add(p1WinCount, gbc);
      
      p1HandCount = new JLabel("Cards in Hand: ");
      gbc.gridx = 2;
      gbc.gridy = 5;
      mainFrame.add(p1HandCount, gbc);
      
      
      //show the GUI when called   
      mainFrame.setVisible(true);;
   }
         
         
   
   /**ButtonListener class handles button events
   */
   private class ButtonListener implements ActionListener
   {
      public void actionPerformed(ActionEvent e) 
      {
         //start new game
         if(e.getSource() == newGame)
         {
            game = new WarGame();
            status.setText("New Game");

            p1Deck.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
            p2Deck.setIcon(new ImageIcon("cardpics/decklarge.jpg"));
            p1HandCount.setText("Cards in Hand: " + game.getP1Hand());
            p2HandCount.setText("Cards in Hand: " + game.getP2Hand());
            p1WinCount.setText("Cards Won: " + game.getP1Winnings());
            p2WinCount.setText("Cards Won: " + game.getP2Winnings());
         }
         //flip card for each round
         else if(e.getSource() == flipCard)
         {
            try
            {
               p1HandCount.setText("Cards in Hand: " + game.getP1Hand());
               p2HandCount.setText("Cards in Hand: " + game.getP2Hand());
               p1WinCount.setText("Cards Won: " + game.getP1Winnings());
               p2WinCount.setText("Cards Won: " + game.getP2Winnings());

               game.playRound();
               p1Card.setIcon(new ImageIcon("cardpics/" + game.getP1Card().shortString() + ".jpg"));
               p2Card.setIcon(new ImageIcon("cardpics/" + game.getP2Card().shortString() + ".jpg"));
                              
               if(game.getP1Card().getRank() > game.getP2Card().getRank())
                  status.setText("Player 1 wins the round");
                  
               else if(game.getP1Card().getRank() < game.getP2Card().getRank())
                  status.setText("Player 2 wins the round");
                  
               else
               {
                  status.setText("WAR! Play one card face down.");
                  game.war();
                  p1Card.setIcon(new ImageIcon("cardpics/back.jpg"));
                  p2Card.setIcon(new ImageIcon("cardpics/back.jpg"));
               }

               
            }
            catch(NullPointerException error)
            {
               status.setText("You must start a new game!");
            }
            
            
         }
         else if(e.getSource() == quit)
            System.exit(0);
      }
   }


   public static void main(String[] args)
   {
      WarGameGUI gui = new WarGameGUI();
   }
}
            
         
         
      