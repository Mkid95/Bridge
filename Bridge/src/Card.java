import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 *
 * @author Matthew Espinoza
 */
public class Card implements Suit, Comparable<Card>
    {
	private int number, suit, color;
	private String suitName;
	private Image image;
	private static final Image BACK = new Image("Card_back.png");
	private ImageView cardView = new ImageView();

	public static Card[] arrayDeck =
	    { new Card(14, 1), new Card(2, 1), new Card(3, 1), new Card(4, 1), new Card(5, 1), new Card(6, 1), new Card(7, 1), new Card(8, 1), new Card(9, 1),
		    new Card(10, 1), new Card(11, 1), new Card(12, 1), new Card(13, 1), new Card(14, 2), new Card(2, 2), new Card(3, 2), new Card(4, 2),
		    new Card(5, 2), new Card(6, 2), new Card(7, 2), new Card(8, 2), new Card(9, 2), new Card(10, 2), new Card(11, 2), new Card(12, 2),
		    new Card(13, 2), new Card(14, 3), new Card(2, 3), new Card(3, 3), new Card(4, 3), new Card(5, 3), new Card(6, 3), new Card(7, 3),
		    new Card(8, 3), new Card(9, 3), new Card(10, 3), new Card(11, 3), new Card(12, 3), new Card(13, 3), new Card(14, 4), new Card(2, 4),
		    new Card(3, 4), new Card(4, 4), new Card(5, 4), new Card(6, 4), new Card(7, 4), new Card(8, 4), new Card(9, 4), new Card(10, 4),
		    new Card(11, 4), new Card(12, 4), new Card(13, 4), };
	public static ArrayList<Card> deck = new ArrayList<Card>(Arrays.asList(arrayDeck));

	public Card(int number, int suit)
	    {
		try
		    {
			setNumber(number);
			setSuit(suit);
		    }
		catch (InvalidCardException e)
		    {
			e.printStackTrace();
		    }
	    }
	public int getNumber()
	    {
		return number;
	    }
	public int getSuit()
	    {
		return suit;
	    }
	private void setNumber(int number) throws InvalidCardException
	    {
		if (number <= 14 && number > 1)
		    {
			this.number = number;
		    }
		else
		    {
			throw new InvalidCardException();
		    }
	    }
	private void setSuit(int suit) throws InvalidCardException
	    {
		if (suit <= 4 && suit > 0)
		    {
			this.suit = suit;
			if (suit == HEART)
			    {
				setSuiteName("Heart");
				setColor(RED);
			    }
			else if (suit == DIAMOND)
			    {
				setSuiteName("Diamond");
				setColor(RED);
			    }
			else if (suit == SPADE)
			    {
				setSuiteName("Spade");
				setColor(BLACK);
			    }
			else if (suit == CLUB)
			    {
				setSuiteName("Club");
				setColor(BLACK);
			    }
		    }
		else
		    {
			throw new InvalidCardException();
		    }
	    }
	private void setSuiteName(String suitName)
	    {
		this.suitName = suitName;

	    }
	public String getSuitName()
	    {
		return suitName;
	    }
	public String getName()
	    {
		if (number == 14)
		    {
			return "Ace";
		    }
		else if (number >= 2 && number < 11)
		    {
			return Integer.toString(number);
		    }
		else if (number == 11)
		    {
			return "Jack";
		    }
		else if (number == 12)
		    {
			return "Queen";
		    }
		else if (number == 13)
		    {
			return "King";
		    }
		else
		    {
			return null;
		    }
	    }
	public int getColor()
	    {
		return color;
	    }
	private void setColor(int color)
	    {
		this.color = color;
	    }
	public Image getImage()
	    {
		return image;
	    }
	public void setImage(boolean x)
	    {
		if (x)
		    {
			StringBuilder imageName = new StringBuilder("Playing_card_");
			imageName.append(getSuitName().toLowerCase() + "_" + Integer.toString(getNumber()) + ".jpg");
			// System.out.println(imageName.toString());
			image = new Image(imageName.toString());
			cardView.setOnMouseEntered(e -> {
			    cardView.setFitWidth(150);
			    // cardView.setY(cardView.getY() -10);
			});
			cardView.setOnMouseExited(e -> {
			    cardView.setFitWidth(100);
			});

		    }
		else
		    {
			image = BACK;
		    }
		cardView.setImage(image);
		cardView.setPreserveRatio(true);
		cardView.setFitWidth(100);
	    }
	public ImageView getCardView()
	    {
		return cardView;
	    }
	public String toString()
	    {
		return getName() + " Of " + getSuitName() + "s";
	    }
	public static void Shuffle()
	    {
		Card temp;
		Random ran = new Random();
		int ranIndex;
		for (int i = 0; i < deck.size(); i++)
		    {
			temp = deck.get(i);
			ranIndex = ran.nextInt(52);
			deck.set(i, deck.get(ranIndex));
			deck.set(ranIndex, temp);
		    }
	    }
	public static void reset()
	    {
		deck = new ArrayList<Card>(Arrays.asList(arrayDeck));
	    }
	public static void printCards()
	    {
		for (int i = 0; i < deck.size(); i++)
		    {
			System.out.println(deck.get(i).toString());
		    }
	    }
	public static Card draw()
	    {
		Card drawnCard = deck.get(deck.size() - 1);
		deck.remove(deck.size() - 1);
		deck.trimToSize();
		return drawnCard;
	    }
	@Override
	public int compareTo(Card o)
	    {
		if (getNumber() > o.getNumber())
		    {
			return 1;
		    }
		else if (getNumber() < o.getNumber())
		    {
			return -1;
		    }
		return 0;
	    }
    }