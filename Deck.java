import java.util.Random;
public class Deck 
{
   public static final int CARDS_IN_DECK = 52; //maximum amount of cards in a standard card deck

   Card [] deck; //list of Card objects
   int cardsTotal;  //total amount of cards in deck
   
   /**Default constructor creates a fresh deck of 52 cards
   */
   
   public Deck()
   {
      freshDeck();
   }
   
   /**freshDeck method creates a fresh deck of 52 cards
   */
   
   public void freshDeck()
   {
      deck = new Card[CARDS_IN_DECK];
      for (int r = 2; r<=Card.ACE;r++)
      {
         for (int s=Card.CLUBS;s<=Card.SPADES;s++)
         {
            deck[cardsTotal]=new Card(s,r);
            cardsTotal++;
         }
      }   
   }
   
   /**dealCard method removes a card from the deck
      @return A card object from the top spot in the deck
   */
   
   public Card dealCard()
   {
      cardsTotal--;
      return deck[cardsTotal];
   }
   
   /**cardsRemaining method returns the amount of cards left in the deck
      @return The amount of cards left in the deck
   */
   
   public int cardsRemaining()
   {  
      return cardsTotal;
   }
   
   /**shuffle method shuffles the cards in the deck and puts them in a new
      random order
   */
   
   public void shuffle()
   {
      int randNum;
      Card temp;
      Random r = new Random();
      for (int i = 0; i < cardsTotal; i++)
      {
         randNum = r.nextInt(cardsTotal);
         temp = deck[i];
         deck[i]=deck[randNum];
         deck[randNum]=temp;
      }
   }
   
   /**isEmpty method returns true if the deck of cards is empty
      @return True if the deck of cards has no more cards
   */
   
   public boolean isEmpty()
   {
      return (cardsRemaining() == 0);
   }
}