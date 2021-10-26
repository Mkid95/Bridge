import javafx.scene.layout.StackPane;

public class CardSlot extends StackPane
    {
	private Card card;
	public void setCard(Card x)
	    {
		card = x;
		if (x != null)
		    {
			card.setImage(true);
			card.getCardView().setOnMouseEntered(e -> {

			});
			card.getCardView().setOnMouseClicked(e -> {

			});
			this.getChildren().add(card.getCardView());
		    }
	    }
	public Card getCard()
	    {
		return card;
	    }
    }
