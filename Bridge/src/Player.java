public class Player implements Suit
    {
	private Card[] hand = new Card[13];
	private int tricks;
	private boolean isUser;
	private String name;
	
	public Player(boolean isUser,String name)
	    {
		setName(name);
		setUser(isUser);
		tricks = 0;
	    }
	public Card[] getHand()
	    {
		return hand;
	    }
	public int getTricks()
	    {
		return tricks;
	    }
	public void addTrick()
	    {
		tricks++;
	    }
	public boolean isUser()
	    {
		return isUser;
	    }
	private void setUser(boolean isUser)
	    {
		this.isUser = isUser;
	    }
	public String getName()
	    {
		return name;
	    }
	public void setName(String name)
	    {
		this.name = name;
	    }
	public void add(Card card)
	    {
		int i = 0;
		boolean notFound = true;
		while (i < hand.length && notFound)
		    {
			if (hand[i] == null)
			    {
				hand[i] = card;
				hand[i].setImage(isUser());
				notFound = false;
			    }
			i++;
		    }
	    }
	public Card playCard(int index)
	    {
		Card card = hand[index];
		hand[index] = null;
		boolean swapped = false;
		for (int i = index + 1; i < getHand().length && !swapped; i++)
		    {
			if (i + 1 == hand.length || getHand()[i + 1] == null)
			    {
				Card temp = hand[i];
				hand[i] = hand[index];
				hand[index] = temp;
				swapped = true;
			    }
		    }
		System.out.println(getName()+" moved with a "+card.toString());
		return card;
	    }
	public int getHighestCard(int suit)
	    {
		if (containsSuit(suit))
		    {
			int max = 0;
			if (suit == ANY)
			    {
				for (int i = 1; i < getHand().length && getHand()[i] != null; i++)
				    {
					if (getHand()[max].getNumber() < getHand()[i].getNumber())
					    {
						max = i;
					    }
				    }
			    }
			else
			    {
				for (int i = 1; i < getHand().length && getHand()[i] != null; i++)
				    {
					if (getHand()[max].getSuit() != suit && getHand()[i].getSuit() == suit)
					    {
						max = i;
					    }
					if ((getHand()[max].getNumber() < getHand()[i].getNumber()) && getHand()[i].getSuit() == suit)
					    {
						max = i;
					    }
				    }
			    }
			return max;
		    }
		else
		    {
			int min = 0;
			for (int i = 1; i < getHand().length && getHand()[i] != null; i++)
			    {
				if (getHand()[min].getNumber() > getHand()[i].getNumber())
				    {
					min = i;
				    }
			    }
			return min;
		    }

	    }
	public boolean containsSuit(int suit)
	    {
		if (suit == ANY)
		    {
			return true;
		    }
		for (int i = 0; i < getHand().length && getHand()[i] != null; i++)
		    {
			if (getHand()[i].getSuit() == suit)
			    {
				return true;
			    }
		    }
		return false;
	    }
	public void reset()
	{
	    tricks=0;
	    hand = new Card[13];
	}
    }
