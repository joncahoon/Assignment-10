/*
Jonathon Cahoon
CS110
Assignment 10
*/

import java.util.Random;

/**War class simulates a game of war between two players
*/

public class WarGame
{
   private Deck deck; //deck of cards to be distributed to players at start
   private Hand p1Hand; //player 1's hand
   private Hand p2Hand; //player 2's hand
   private Hand p1Winnings; //winning pile of cards for player 1
   private Hand p2Winnings; //winning pile of cards for player 2
   private Card p1Card; //the card p1ayer 1 plays
   private Card p2Card; //the card player 2 plays
   private Hand downCards; //holds cards currently in play
   private boolean inWar; //stores whether or not the game is in war
   
   /**Default constructor deals equal amount of cards to both players
   */
   
   public WarGame()
   {
      deck = new Deck(); //create new fresh deck
      p1Hand = new Hand(); //create empty hand for player 1
      p1Winnings = new Hand(); //create empty hand for player 1 winnings pile
      p2Hand = new Hand(); // create empty hand for player 2
      p2Winnings = new Hand(); //create empty hand for player 2 winnings
      downCards = new Hand(); //create empty hand for cards in play
      inWar = false;

      
      deck.shuffle(); //shuffle the deck
      
      while(!deck.isEmpty()) //deal all cards in deck evenly between the 2 players
      {
         p1Hand.addCard(deck.dealCard());
         p2Hand.addCard(deck.dealCard());
      }
   }
   
   /**playRound method each player plays one round of cards.
   */
   
   public void playRound()
   {           
      //players play one card each
      p1Card = p1Hand.dealCard();
      p2Card = p2Hand.dealCard();
      
      //add played cards to downCards
      downCards.addCard(p1Card);
      downCards.addCard(p2Card);
      
      //award all cards in play to the player who wins the round 
      if(p1Card.getRank() > p2Card.getRank())
		{
         inWar = false;
         while(!downCards.isEmpty())
            p1Winnings.addCard(downCards.dealCard());
		}
		else if(p2Card.getRank() >	p1Card.getRank())
		{
         inWar = false;
		   while(!downCards.isEmpty())
            p2Winnings.addCard(downCards.dealCard());
      }
      else //if cards are equal go to war
         inWar = true;

   }
   
   /**War method plays one card face down from each player and adds them
      to the cards in play
   */
   
   public void war()
   {  
      //both players play one card face down
      downCards.addCard(p1Hand.dealCard());
      downCards.addCard(p2Hand.dealCard());
      
      inWar = false;
   }
   
   /**handSwitch method shuffles the player's winnings hand and makes it
      the players hand
   */
   
   public void handShuffle()
   {
      if(p1Hand.isEmpty() && !p1Winnings.isEmpty())
      {
         p1Winnings.shuffle(); //shuffle winnings pile
         //deal all cards in winnings pile into the player's hand
         while(!p1Winnings.isEmpty()) 
            p1Hand.addCard(p1Winnings.dealCard());
      }  
      
      if(p2Hand.isEmpty() && !p2Winnings.isEmpty())
      {
         p2Winnings.shuffle(); //shuffle cards in winnings pile
         //deal all cards in winnings pile into the player's hand
         while(!p2Winnings.isEmpty())
            p2Hand.addCard(p2Winnings.dealCard());
      }
   }
   
   /**winner method returns the integer associated with the player who won
      @return The winner of the game
   */
   
   public int winner()
   {
      //if the player has no cards left in their winnings pile or 
      //their hand then they lose
      if(p1Winnings.isEmpty() && p1Hand.isEmpty())
      {
         //winning player takes all cards in play
         while(!downCards.isEmpty())
         {
            p2Winnings.addCard(downCards.dealCard());
         }
         return 2;
      }
            
      else if(p2Winnings.isEmpty() && p2Hand.isEmpty())
      {
         //winning player takes all cards in play
         while(!downCards.isEmpty())
         {
            p1Winnings.addCard(downCards.dealCard());
         }
         return 1;
      }
      
      else //if players have cards left the game continues
         return 0;
   }
   
   /**getP1Card returns the Card object associated with the card that
      player 1 played
      @return A card object
   */
   
   public Card getP1Card()
   {
      return p1Card;
   }
   
   /**getP2Card returns the Card object associated with the card that
      player 2 player
      @return A card object
   */
   
   public Card getP2Card()
   {
      return p2Card;
   }
   
   /**getP1Winnings method returns the amount of cards in player 1's
      winnings pile
      @return The amount of cards in p1Winnings
   */
   
   public int getP1Winnings()
   {
      return p1Winnings.cardsRemaining();
   }
   
   /**getP1Hand method returns the amount of cards in player 1's hand
      @return The amount of cards in p1Hand
   */
   
   public int getP1Hand()
   {
      return p1Hand.cardsRemaining();
   }
   
   /**getP2Winnings method returns the amount of cards in player 2's
      winnings pile
      @return The amount of cards in p2Winnings
   */
   
   public int getP2Winnings()
   {
      return p2Winnings.cardsRemaining();
   }
   
   /**getP2Hand method returns the amount of cards in player 2's hand
      @return The amount of cards in p2Hand
   */
   
   public int getP2Hand()
   {
      return p2Hand.cardsRemaining();
   }
   
   /**warStatus method returns whether or not the game is currently in war
      @return True if the game is in war
   */
   
   public boolean warStatus()
   {
      return inWar;
   }
}