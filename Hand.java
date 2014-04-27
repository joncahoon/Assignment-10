/*
Jonathon Cahoon
CS110
Assignment 10
*/

/**Hand class represents a hand of cards in a card game
*/

public class Hand extends Deck
{
   /**Constructor creates an empty deck of cards
   */
   
   public Hand()
   {
      deck = new Card[CARDS_IN_DECK];
      cardsTotal = 0;
   }
   
   /**addCard method adds a card to the hand if cards
      @param card The Card object to be added to the hand
   */
   
   public void addCard(Card card)
   {
      deck[cardsTotal] = new Card(card);
      cardsTotal++;
   }
   
   public static void main(String[] args)
   {
      Deck deck = new Deck();
      Hand hand = new Hand();
      deck.shuffle();
      hand.addCard(deck.dealCard());
      if(deck.isEmpty() || hand.isEmpty())
         System.out.println("This shouldn't print");
      System.out.println(hand.dealCard().toString());
      System.out.println("deck is empty? " + deck.isEmpty());
      System.out.println("hand is empty? " + hand.isEmpty());
      if(deck.isEmpty() || hand.isEmpty())
         System.out.println("This should print");
      
      
   }
}