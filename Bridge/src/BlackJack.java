import java.util.Arrays;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author Matthew Espinoza
 */
public class BlackJack extends Application
    {
	Button deal = new Button("Deal");
	Button reset = new Button("Reset");
	ImageView[] view = {new ImageView(),new ImageView(),new ImageView(),new ImageView()};
        VBox box = new VBox();
        HBox cards = new HBox();
        Label label = new Label("Press \"deal\" to begin");
        int value = 0;
        int i = 0;
        Card[] hand = new Card[4];
	public void start(Stage primaryStage)
	    {
		Card.Shuffle();
		reset.setOnAction(e->{
		    Arrays.fill(hand, null);
		    for(int k = 0;k<view.length;k++)
			{
			    view[k].setImage(null);
			}
		    i = 0;
		    value = 0;
		    label.setText("Press \"deal\" to begin");
		});
		deal.setOnAction(e->
		{
		    if(i<4&&value<22)
			{
			    hand[i] = Card.draw();
			    view[i].setImage(hand[i].getImage());
			    value +=hand[i].getNumber();
			    if(value>21)
				{
				    label.setText(hand[i].toString()+" was drawn, Total is now "+value+", You Busted!");
				}
			    else
				{
				    label.setText(hand[i].toString()+" was drawn, Total is now "+value);
				}
			    i++;
			}
		});
		cards.getChildren().addAll(view[0],view[1],view[2],view[3]);
		box.getChildren().addAll(label,cards,deal,reset);
		box.setAlignment(Pos.CENTER);
		Scene scene = new Scene(box,800,400);
		primaryStage.setTitle("BlackJack - Matthew Espinoza");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
    }