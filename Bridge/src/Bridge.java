import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Bridge extends Application implements Suit
    {
	private Player user = new Player(true, "Player 1"), cpu1 = new Player(false, "Player 2"), cpu2 = new Player(false, "Player 3"), cpu3 = new Player(
		false, "Player 4");
	private BorderPane pane = new BorderPane();
	private BorderPane gameCenter = new BorderPane();
	private HBox p0 = new HBox(-50);
	private VBox p1 = new VBox(-100);
	private HBox p2 = new HBox(-50);
	private VBox p3 = new VBox(-100);
	private StackPane root = new StackPane();
	private CardSlot bot = new CardSlot();
	private CardSlot left = new CardSlot();
	private CardSlot top = new CardSlot();
	private CardSlot right = new CardSlot();
	private ImageView background = new ImageView(new Image("bg-background-theme-2.jpg"));
	private int position = 1;
	private int suitOfFirst;
	private Label label = new Label("Game start!");
	private Label l1 = new Label("Player 1 Tricks: 0");
	private Label l2 = new Label("Player 2 Tricks: 0");
	private Label l3 = new Label("Player 3 Tricks: 0");
	private Label l4 = new Label("Player 4 Tricks: 0");
	private Image spade = new Image("spade.png");
	private Image hearts = new Image("hearts.png");
	private Image club = new Image("club.png");
	private Image diamond = new Image("diamond.png");
	private ImageView suitCenter = new ImageView(hearts);
	private Button play = new Button("Play bridge!");
	AudioClip sound = new AudioClip(getClass().getResource("drawnew.wav").toExternalForm());
	@Override
	public void start(Stage primaryStage)
	    {
		label.setVisible(false);
		l1.setVisible(false);
		l2.setVisible(false);
		l3.setVisible(false);
		l4.setVisible(false);
		suitCenter.setVisible(false);
		gameCenter.setBottom(bot);
		gameCenter.setTop(top);
		gameCenter.setLeft(left);
		gameCenter.setRight(right);
		gameCenter.setCenter(suitCenter);
		gameCenter.setMaxHeight(400);
		gameCenter.setMaxWidth(400);
		bot.setMinHeight(125);
		p0.setMinHeight(220);
		p2.setMinHeight(220);
		// p0.setStyle("-fx-border-width:1; -fx-border-color:red;");
		p0.setPadding(new Insets(0, 0, 30, 0));
		p1.setPadding(new Insets(0, 0, 0, 30));
		p2.setPadding(new Insets(30, 0, 0, 0));
		p3.setPadding(new Insets(0, 30, 0, 0));
		// p1.setStyle("-fx-border-width:1; -fx-border-color:red;");
		p0.setAlignment(Pos.BOTTOM_CENTER);
		p1.setAlignment(Pos.CENTER_LEFT);
		p2.setAlignment(Pos.TOP_CENTER);
		p3.setAlignment(Pos.CENTER_RIGHT);
		pane.setBottom(p0);
		pane.setLeft(p1);
		pane.setTop(p2);
		pane.setRight(p3);
		pane.setCenter(gameCenter);
		root.getChildren().add(background);
		label.setTextFill(Color.WHITE);
		label.setStyle("-fx-color: red");
		l1.setTextFill(Color.WHITE);
		l1.setStyle("-fx-color: red");
		l2.setTextFill(Color.WHITE);
		l2.setStyle("-fx-color: red");
		l3.setTextFill(Color.WHITE);
		l3.setStyle("-fx-color: red");
		l4.setTextFill(Color.WHITE);
		l4.setStyle("-fx-color: red");
		root.getChildren().add(label);
		root.getChildren().add(l1);
		root.getChildren().add(l2);
		root.getChildren().add(l3);
		root.getChildren().add(l4);
		label.setTranslateY(205);
		l1.setTranslateY(420);
		l2.setTranslateX(-660);
		l2.setTranslateY(205);
		l3.setTranslateY(-420);
		l4.setTranslateX(660);
		l4.setTranslateY(205);
		root.getChildren().add(pane);
		root.getChildren().add(play);
		Scene scene = new Scene(root, 1473, 850);
		primaryStage.setTitle("Bridge - Matthew Espinoza");
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
		play.setOnAction(e -> {
		    Card.reset();
		    Card.Shuffle();
		    play.setVisible(false);
		    play.setDisable(true);
		    beginDeal();
		});
	    }
	public void turnLoop() // bulk of game logic gates
	    {
		Platform.runLater(() -> {
		    if (getTotalTricks() != 13)
			{
			    if (position == 0) // player was first
				{
				    if (bot.getCard() == null)
					{
					    generateValidCards(user, ANY);
					    return;
					}
				    setSuitOfFirst(bot.getCard().getSuit());
				    left.setCard(cpu1.playCard(cpu1.getHighestCard(suitOfFirst)));
				    top.setCard(cpu2.playCard(cpu2.getHighestCard(suitOfFirst)));
				    right.setCard(cpu3.playCard(cpu3.getHighestCard(suitOfFirst)));
				    getRoundWinner();
				    if (getTotalTricks() == 13)
					{
					    endGame();
					    return;
					}
				    if (position == 2)
					{
					    position = 4;
					}
				    else if (position == 3)
					{
					    position = 5;
					}
				    turnLoop();
				}
			    else if (position == 1) // cpu1 was
						    // first
				{
				    left.setCard(cpu1.playCard(cpu1.getHighestCard(ANY)));
				    setSuitOfFirst(left.getCard().getSuit());
				    top.setCard(cpu2.playCard(cpu2.getHighestCard(suitOfFirst)));
				    right.setCard(cpu3.playCard(cpu3.getHighestCard(suitOfFirst)));
				    generateValidCards(user, suitOfFirst);
				}
			    else if (position == 2) // cpu2 was
						    // first
				{
				    left.setCard(cpu1.playCard(cpu1.getHighestCard(suitOfFirst)));
				    getRoundWinner();
				    if (getTotalTricks() == 13)
					{
					    endGame();
					    return;
					}
				    if (position == 0)
					{
					    generateValidCards(user, ANY);
					    return;
					}
				    else if (position == 1)
					{
					    turnLoop();
					    return;
					}
				    else if (position == 2)
					{
					    top.setCard(cpu2.playCard(cpu2.getHighestCard(ANY)));
					    setSuitOfFirst(top.getCard().getSuit());
					    right.setCard(cpu3.playCard(cpu3.getHighestCard(suitOfFirst)));
					    generateValidCards(user, suitOfFirst);
					}
				    else if (position == 3)
					{
					    position = 5;
					    turnLoop();
					}
				}
			    else if (position == 3) // cpu 3 was
						    // first
				{
				    left.setCard(cpu1.playCard(cpu1.getHighestCard(suitOfFirst)));
				    top.setCard(cpu2.playCard(cpu2.getHighestCard(suitOfFirst)));
				    getRoundWinner();
				    if (getTotalTricks() == 13)
					{
					    endGame();
					    return;
					}
				    if (position == 0)
					{
					    generateValidCards(user, ANY);
					    return;
					}
				    else if (position == 1)
					{
					    turnLoop();
					    return;
					}
				    else if (position == 2)
					{
					    position = 4;
					    turnLoop();
					    return;
					}
				    else if (position == 3)
					{
					    position = 5;
					    turnLoop();
					    return;
					}
				    right.setCard(cpu3.playCard(cpu3.getHighestCard(ANY)));
				    generateValidCards(user, right.getCard().getNumber());
				}
			    else if (position == 4) // cpu2 won and
						    // is now
						    // going first.
				{
				    top.setCard(cpu2.playCard(cpu2.getHighestCard(ANY)));
				    setSuitOfFirst(top.getCard().getSuit());
				    right.setCard(cpu3.playCard(cpu3.getHighestCard(suitOfFirst)));
				    position = 2;
				    generateValidCards(user, suitOfFirst);
				}
			    else if (position == 5)
				{
				    right.setCard(cpu3.playCard(cpu3.getHighestCard(ANY)));
				    setSuitOfFirst(right.getCard().getSuit());
				    position = 3;
				    generateValidCards(user, suitOfFirst);
				}
			}
		    else
			{
			    endGame();
			}
		});

	    }
	public void generateValidCards(Player x, int suit)
	    {
		if (suit == ANY || !(x.containsSuit(suit)))
		    {
			for (int i = 0; i < x.getHand().length && user.getHand()[i] != null; i++)
			    {
				final int loc = i;
				x.getHand()[i].getCardView().setOnMouseClicked(e -> {
				    sound.play();
				    bot.setCard(user.playCard(loc));
				    if (position == 1)
					{
					    getRoundWinner();
					    if (getTotalTricks() == 13)
						{
						    endGame();
						}
					    else
						{
						    if (position == 2)
							{
							    position = 4;
							}
						    else if (position == 3)
							{
							    position = 5;
							}
						}
					}
				    degenerateCards();
				    turnLoop();
				});
			    }
		    }
		else
		    {
			for (int i = 0; i < x.getHand().length && user.getHand()[i] != null; i++)
			    {
				if (x.getHand()[i].getSuit() == suit)
				    {
					final int loc = i;
					x.getHand()[i].getCardView().setOnMouseClicked(e -> {
					    sound.play();
					    bot.setCard(user.playCard(loc));
					    if (position == 1)
						{
						    getRoundWinner();
						    if (getTotalTricks() == 13)
							{
							    endGame();
							}
						    else
							{
							    if (position == 2)
								{
								    position = 4;
								}
							    else if (position == 3)
								{
								    position = 5;
								}
							}
						}
					    degenerateCards();
					    turnLoop();
					});
				    }
			    }
		    }
	    }
	private void degenerateCards()
	    {
		for (int i = 0; i < user.getHand().length && user.getHand()[i] != null; i++)
		    {
			user.getHand()[i].getCardView().setOnMouseClicked(l -> {
			});
		    }
	    }
	private int getTotalTricks()
	    {
		return user.getTricks() + cpu1.getTricks() + cpu2.getTricks() + cpu3.getTricks();
	    }
	private void getRoundWinner()
	    {
		ArrayList<Card> center = new ArrayList<Card>(Arrays.asList(new Card[]
		    { bot.getCard(), top.getCard(), left.getCard(), right.getCard() }));
		for (int i = 0; i < center.size(); i++)
		    {
			if (center.get(i) != null && center.get(i).getSuit() != suitOfFirst)
			    {
				center.remove(i);
			    }
		    }
		Card max = Collections.max(center);
		if (max.equals(bot.getCard()))
		    {
			position = 0;
			setSuitOfFirst(ANY);
			user.addTrick();
			l1.setText("Player 1 Tricks: " + user.getTricks());
			label.setText(user.getName() + " Won the round with " + max.toString());
			System.out.println(user.getName() + " Won the round with " + max.toString());
		    }
		else if (max.equals(left.getCard()))
		    {
			position = 1;
			cpu1.addTrick();
			l2.setText("Player 2 Tricks: " + cpu1.getTricks());
			label.setText(cpu1.getName() + " Won the round with " + max.toString());
			System.out.println(cpu1.getName() + " Won the round with " + max.toString());
		    }
		else if (max.equals(top.getCard()))
		    {
			position = 2;
			cpu2.addTrick();
			l3.setText("Player 3 Tricks: " + cpu2.getTricks());
			label.setText(cpu2.getName() + " Won the round with " + max.toString());
			System.out.println(cpu2.getName() + " Won the round with " + max.toString());
		    }
		else if (max.equals(right.getCard()))
		    {
			position = 3;
			cpu3.addTrick();
			l4.setText("Player 4 Tricks: " + cpu3.getTricks());
			label.setText(cpu3.getName() + " Won the round with " + max.toString());
			System.out.println(cpu3.getName() + " Won the round with " + max.toString());
		    }
		bot.setCard(null);
		left.setCard(null);
		top.setCard(null);
		right.setCard(null);
	    }
	private void deal(int i)
	    {
		final Integer loc = i;
		try
		    {
			Platform.runLater(new Runnable()
			    {

				public void run()
				    {
					user.add(Card.draw());
					p0.getChildren().add(user.getHand()[loc].getCardView());
					sound.play();
				    }
			    });
			Thread.sleep(60);

			Platform.runLater(new Runnable()
			    {

				public void run()
				    {
					cpu1.add(Card.draw());
					cpu1.getHand()[loc].getCardView().setRotate(90);
					p1.getChildren().add(cpu1.getHand()[loc].getCardView());
					// sound.play();
				    }
			    });
			Thread.sleep(60);
			Platform.runLater(new Runnable()
			    {

				public void run()
				    {
					cpu2.add(Card.draw());
					p2.getChildren().add(cpu2.getHand()[loc].getCardView());
					// sound.play();
				    }
			    });

			Thread.sleep(60);

			Platform.runLater(new Runnable()
			    {

				public void run()
				    {

					cpu3.add(Card.draw());
					cpu3.getHand()[loc].getCardView().setRotate(-90);
					p3.getChildren().add(cpu3.getHand()[loc].getCardView());
					// sound.play();
				    }
			    });
			Thread.sleep(60);
		    }
		catch (InterruptedException e)
		    {
		    }
	    }
	public void setSuitOfFirst(int suit)
	    {
		suitOfFirst = suit;
		if (suit == HEART)
		    {
			suitCenter.setImage(hearts);
		    }
		else if (suit == DIAMOND)
		    {
			suitCenter.setImage(diamond);
		    }
		else if (suit == SPADE)
		    {
			suitCenter.setImage(spade);
		    }
		else if (suit == CLUB)
		    {
			suitCenter.setImage(club);
		    }
	    }
	public void endGame()
	    {
		System.out.println("Game ended\nNorth South : " + (user.getTricks() + cpu2.getTricks()) + "\nWest East: "
			+ (cpu1.getTricks() + cpu3.getTricks()));
		if (user.getTricks() + cpu2.getTricks() > cpu1.getTricks() + cpu3.getTricks())
		    {
			label.setText(label.getText() + ". North South : " + (user.getTricks() + cpu2.getTricks()) + " West East: "
				+ (cpu1.getTricks() + cpu3.getTricks()) + " North South wins!");
			System.out.println("North South wins!");
		    }
		else
		    {
			label.setText(label.getText() + ". North South : " + (user.getTricks() + cpu2.getTricks()) + " West East: "
				+ (cpu1.getTricks() + cpu3.getTricks()) + " West East wins!");
			System.out.println("West East wins!");
		    }
		bot.setCard(null);
		left.setCard(null);
		top.setCard(null);
		right.setCard(null);
		// user.reset();
		// cpu1.reset();
		// cpu2.reset();
		// cpu3.reset();
		// play.setVisible(true);
		// play.setDisable(false);
		suitCenter.setVisible(false);
	    }
	public void beginDeal()
	    {
		new Thread(new Runnable()
		    {
			public void run()
			    {
				for (int i = 0; i < 13; i++)
				    {
					deal(i);
				    }
				beginGame();
			    }
		    }).start();
	    }
	public void beginGame()
	    {
		new Thread(new Runnable()
		    {
			public void run()
			    {
				Platform.runLater(() -> {
				    label.setVisible(true);
				    l1.setVisible(true);
				    l2.setVisible(true);
				    l3.setVisible(true);
				    l4.setVisible(true);
				    suitCenter.setVisible(true);
				});

				turnLoop();
			    }
		    }).start();
	    }
    }
