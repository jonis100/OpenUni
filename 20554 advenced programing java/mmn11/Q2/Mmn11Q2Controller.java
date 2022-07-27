import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

public class Mmn11Q2Controller {

    @FXML
    private Button btnF;

    @FXML
    private Canvas canv;
    
    @FXML
    private GraphicsContext gc;
    
    final int SPACE = 10;
    final int PERCENT_OF_COVER = 10;
    Random r = new Random();
    		
    public void initialize() {
    	 gc = canv.getGraphicsContext2D();
    	 CreateGrid(canv);
    } 
    
    
    private void CreateGrid(Canvas canv) {
    	for (int i=0;i<=(canv.getHeight()/10);i++) {
    		gc.strokeLine(SPACE*i, 0, SPACE*i, canv.getHeight());
    		gc.strokeLine(0, SPACE*i, canv.getWidth(), SPACE*i );
    	}
    		
    }
    
    @FXML
    void btnFpressed(ActionEvent event) {
//    	System.out.println("Pressed!");
    	int num_fill_sqe = ((int)(canv.getHeight()/SPACE)*(int)(canv.getWidth()/SPACE))/PERCENT_OF_COVER;
    	gc.clearRect(0, 0, canv.getWidth(), canv.getHeight());
    	CreateGrid(canv);
    	for (int i = 0; i < num_fill_sqe; i++) {
    		int x = r.nextInt((int)canv.getWidth());
    		int y = r.nextInt((int)canv.getHeight());
//    		gc.setFill(Color.color(Math.random(), Math.random(),Math.random()));
    		gc.fillRect((x/SPACE)*SPACE, (y/SPACE)*SPACE, SPACE, SPACE);

    	}

    }
    
    
    
}
