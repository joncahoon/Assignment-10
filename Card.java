/*
Jonathon Cahoon
Assignment 5
CS110
*/

/**Simulates a single card from a standard 52 card deck
*/

public class Card
{
   //set integer value of suit based on alphabetical order
   public static final int SPADES = 4;
   public static final int CLUBS = 1;
   public static final int HEARTS = 3;
   public static final int DIAMONDS = 2;
   
   //set integer value of rank based on card order
   public static final int ACE = 14;
   public static final int JACK = 11;
   public static final int QUEEN = 12;
   public static final int KING = 13;
   
   private int rank; //rank of the card as an integer value
   private int suit; //suit of the card as an integer value
   
   /**Constructor
      @param s suit of the card
      @param r rank of the card
   */
   
   public Card(int s, int r)
   {
      //set suit and rank based on arguments passed to constructor
      suit = s;
      rank = r;
   }
   
   /**Copy constructor
      @param card A card object
   */
   
   public Card(Card card)
   {
      this(card.getSuit(), card.getRank());
   }
   
   /**getSuit method
      @return The suit of the card
   */
   
   public int getSuit()
   {
      return suit; //return suit integer value
   }
   
   /**getRank method
      @return The rank of the card
   */
   
   public int getRank()
   {
      return rank; //return rank integer value
   }
   
   /**toString method
      @return A string indicating the rank and suit of the card
   */
   
   public String toString()
   {
      String rankName; //hold rank name as a string
      String suitName; //hold suit name as a string
      
      //switch statement to set rankName based on rank
      switch (rank)
      {
         case 1:
            rankName = "1";
            break;
         case 2:
            rankName = "2";
            break;
         case 3:
            rankName = "3";
            break;
         case 4:
            rankName = "4";
            break;
         case 5:
            rankName = "5";
            break;
         case 6:
            rankName = "6";
            break;
         case 7:
            rankName = "7";
            break;
         case 8:
            rankName = "8";
            break;
         case 9:
            rankName = "9";
            break;
         case 10:
            rankName = "10";
            break;
         case 11:
            rankName = "Jack";
            break;
         case 12:
            rankName = "Queen";
            break;
         case 13:
            rankName = "King";
            break;
         case 14:
            rankName = "Ace";
            break;
         //if rank is out of range of what would be found in a standard
         //52 card deck then rankName states the rank does not exist
         default:
            rankName = "Rank does not exist";
      }
      
      //switch statement to set suitName based on suit
      switch(suit)
      {
         case 1:
            suitName = "Clubs";
            break;
         case 2:
            suitName = "Diamonds";
            break;
         case 3:
            suitName = "Hearts";
            break;
         case 4:
            suitName = "Spades";
            break;
         //if suit is not contained in a standard 52 card deck then
         //suitName states that the Suit does not exist
         default:
            suitName = "Suit does not exist";
      }
      
      //create one string to return rankName and suitName
      String str = rankName + " of " + suitName;
      return str; //return string with info on suit and rank
   }
   
   /**equals method accepts a Card object as an argument.  It then tests
      the equality of the calling object and the argument object.
      @return True if the two cards have equal rank. 
   */
   
   public boolean equals(Card otherCard)
   {
      //return equality of the rank of two cards tested
      return (rank == otherCard.rank);
   }
   
   /**shortString method returns a shortened version of the toString method
      ex: 2 of Clubs will be returns as 2c
      @return Shortened string indicating rank and suit
   */
   
   public String shortString()
   {
      String str; //holds card info as a string
      String suitChar; //holds character associated with the suit
      String rankName; //holds name of rank of the card
      switch (rank)
      {
         case 1:
            rankName = "1";
            break;
         case 2:
            rankName = "2";
            break;
         case 3:
            rankName = "3";
            break;
         case 4:
            rankName = "4";
            break;
         case 5:
            rankName = "5";
            break;
         case 6:
            rankName = "6";
            break;
         case 7:
            rankName = "7";
            break;
         case 8:
            rankName = "8";
            break;
         case 9:
            rankName = "9";
            break;
         case 10:
            rankName = "10";
            break;
         case 11:
            rankName = "jack";
            break;
         case 12:
            rankName = "queen";
            break;
         case 13:
            rankName = "king";
            break;
         case 14:
            rankName = "ace";
            break;
         //if rank is out of range of what would be found in a standard
         //52 card deck then rankName states the rank does not exist
         default:
            rankName = "Rank does not exist";
      }

      switch(suit)
      {
         case 1:
            suitChar = "c";
            break;
         case 2:
            suitChar = "d";
            break;
         case 3:
            suitChar = "h";
            break;
         case 4:
            suitChar = "s";
            break;
         default:
            suitChar = "Does not exist";
      }
      
      str = rankName + suitChar;
      return str;
   }
   public static void main(String[] args)
   {
      Card card = new Card(2,11);
      System.out.println(card.shortString());
   }
}