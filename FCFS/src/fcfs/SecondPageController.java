
package fcfs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SecondPageController implements Initializable {
    
    private double x = 0;
    private double y = 0;
    
    @FXML
    private Button exitBtn;

    @FXML
    private Button startBtn;
    
    @FXML
    public void start() throws Exception{
        startBtn.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("ThirdPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        root.setOnMousePressed((MouseEvent event) ->{
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged((MouseEvent event) -> {
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
        
        stage.setOpacity(.8);
    });
        
        root.setOnMouseReleased((MouseEvent event) ->{
            stage.setOpacity(1);
        });
        
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void exit(ActionEvent event) throws Exception {
        exitBtn.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void closed(){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
