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
   public Card p1Card; //the card p1ayer 1 plays
   public Card p2Card; //the card player 2 plays
   public Card p1WarCard;
   public Card p2WarCard;


   
   /**Default constructor deals equal amount of cards to both players
   */
   
   public WarGame()
   {
      deck = new Deck(); //create new fresh deck
      p1Hand = new Hand(); //create empty hand for player 1
      p1Winnings = new Hand(); //create empty hand for player 1 winnings pile
      p2Hand = new Hand(); // create empty hand for player 2
      p2Winnings = new Hand(); //create empty hand for player 2 winnings

      
      deck.shuffle(); //shuffle the deck
      
      while(!deck.isEmpty()) //deal all cards in deck evenly between the 2 players
      {
         p1Hand.addCard(deck.dealCard());
         p2Hand.addCard(deck.dealCard());
      }
   }
   /**roundWinner method determines who wins each time the players play
      a card
   */
   
   public void roundWinner()
   {
      //if a player's hand is empty shuffle the winnings pile and replace
      //the winnings pile with the players empty hand
      if(p1Hand.isEmpty() || p2Hand.isEmpty())
      {
         handShuffle();
         if(winner() != 0)//if a player is out of cards return to main method
            return;
      }
         
      //players play one card each
      p1Card = p1Hand.dealCard();
      p2Card = p2Hand.dealCard();
      
      if(p1Card.getRank() > p2Card.getRank())
		{
		   p1Winnings.addCard(p1Card);
		   p1Winnings.addCard(p2Card);
		}
		else if(p2Card.getRank() >	p1Card.getRank())
		{
		   p2Winnings.addCard(p1Card);
		   p2Winnings.addCard(p2Card);
      }
   }
   /**War method plays one card face down from each player then plays 
      another card face up.  Repeats until one player wins the war.
   */
   public void war()
   {
      Card DownCards[] = new Card[Deck.CARDS_IN_DECK]; //holds played in war
      int cardsDown = 0; //counts amount of cards played in war
      
      do //loop until face up cards are not equal
      {
         //if a player's hand is empty shuffle the winnings pile and 
         //replace the winnings pile with the players empty hand
         if(p1Hand.isEmpty() || p2Hand.isEmpty())
         {
            handShuffle();
            if(winner() != 0)//if a player has no cards return to main method
               return;
         }
         //both players play one card face down
         DownCards[cardsDown] = p1Hand.dealCard();
         cardsDown ++;
         DownCards[cardsDown] = p2Hand.dealCard();
         cardsDown ++;
         
         //if a player's hand is empty shuffle the winnings pile and 
         //replace the winnings pile with the players empty hand
         if(p1Hand.isEmpty() || p2Hand.isEmpty())
         {
            handShuffle();
            if(winner() != 0);
               return;
         }
         //both players play one card face up
         p1WarCard = p1Hand.dealCard();
         p2WarCard = p2Hand.dealCard();

         
         //add face up cards to the list of cards in play in war   
         DownCards[cardsDown] = p1WarCard;
         cardsDown++;
         DownCards[cardsDown] = p2WarCard;
         cardsDown ++;
         
      }while(p1WarCard.equals(p2WarCard));      
      //the player who played the higher ranked card gets all the cards
      //played in war and the cards leading to war
      if(p1WarCard.getRank() > p2WarCard.getRank())
      {
         for(int i=0; i < cardsDown; i++)
            p1Winnings.addCard(DownCards[i]);
         p1Winnings.addCard(p1Card);
         p1Winnings.addCard(p2Card);
      }
      else
      {
         for(int i=0; i < cardsDown; i++)
            p2Winnings.addCard(DownCards[i]);
         p2Winnings.addCard(p2Card);
         p2Winnings.addCard(p1Card);
      }
      
   }
   
   /**handSwitch method shuffles the player's winnings hand and makes it
      the players hand
   */
   
   public void handShuffle()
   {
      int cardsLeft; //how many cards are in the winnings pile
      if(p1Hand.isEmpty())
      {
         if(p1Winnings.isEmpty()) //if player 1 has no cards left they lose
            winner();
         else
         {
            p1Winnings.shuffle(); //shuffle winnings pile
            cardsLeft = p1Winnings.cardsRemaining(); //winnings pile size before dealing
            //deal all cards in winnings pile into the player's hand
            for(int i = 0; i < cardsLeft; i++) 
               p1Hand.addCard(p1Winnings.dealCard());
         }
      }  
      if(p2Hand.isEmpty())
      {
         if(p2Winnings.isEmpty())//if player 2 has no cards left they lose
            winner();
         else
         {
            p2Winnings.shuffle(); //shuffle cards in winnings pile
            cardsLeft = p2Winnings.cardsRemaining();//amount of cards left before dealing
            //deal all cards in winnings pile into the player's hand
            for(int i = 0; i < cardsLeft; i++)
               p2Hand.addCard(p2Winnings.dealCard());
         }
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
         return 2;
      else if(p2Winnings.isEmpty() && p2Hand.isEmpty())
         return 1;
      else //if players have cards left the game continues
         return 0;
   }
   
  
   public static void main(String[] args)
   {
      WarGame war = new WarGame();
      while(war.winner() == 0)
         war.roundWinner();
      System.out.println(war.winner());
   }
}