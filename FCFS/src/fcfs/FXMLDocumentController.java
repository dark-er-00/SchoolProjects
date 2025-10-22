package fcfs;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Roland Carl
 */
public class FXMLDocumentController implements Initializable {
    
    private double x = 0;
    private double y = 0;
    
    @FXML
    private Button Loginbtn;

    @FXML
    private AnchorPane main;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;
    
    @FXML
    private Button exit;
    
    @FXML
    public void Login() throws Exception{
        Loginbtn.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("SecondPage.fxml"));
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
    public void close(ActionEvent event){
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
