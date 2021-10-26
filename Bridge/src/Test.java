import javafx.application.Application;
import javafx.stage.Stage;


public class Test extends Application
    {
	public static void main(String[] args)
	{
	    Player x = new Player(true,"player");
	    Card.Shuffle();
	    for (int i = 0;i<13;i++)
		{
		    Card temp = Card.draw();
		    System.out.println(temp.toString());
		    x.add(temp);
		}
	    System.out.println("sdadad "+x.getHand()[x.getHighestCard(1)].toString());
	}

	@Override
	public void start(Stage arg0) throws Exception
	    {
		// TODO Auto-generated method stub
		
	    }
    }
