package fcfs;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ThirdPageController implements Initializable {
    
    private double x = 0;
    private double y = 0;
    
    @FXML
    private Button AddCustomer;

    @FXML
    private Button BackToWelcome;

    @FXML
    private Button ResetQueue;

    @FXML
    private Button ServeNextCustomer;

    @FXML
    private Button ViewQueue;

    @FXML
    private Button close;
    
    @FXML
    public void exit(){
        System.exit(0);
    }
    
    @FXML
    public void welcome() throws Exception{
        BackToWelcome.getScene().getWindow().hide();
        
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
    
    public void addCustomer()throws Exception{
        AddCustomer.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
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
    
    public void ViewQueue()throws Exception{
        ViewQueue.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("ViewQueue.fxml"));
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
    
    public void ServeCustomer()throws Exception{
        ServeNextCustomer.getScene().getWindow().hide();
        
        Parent root = FXMLLoader.load(getClass().getResource("ServeCustomer.fxml"));
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ResetQueue.setOnAction(event -> {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("Do you want to proceed?");

        // Change buttons to Yes and No
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            File origFile = new File("ticket.txt");
            origFile.delete();
        }
        });
    }
}
