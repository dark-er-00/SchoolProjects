package fcfs;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Roland Carl
 */
public class ServeCustomerController implements Initializable {
    
    private double x = 0;
    private double y = 0;
    
    @FXML
    private TextField arrivalTime;
    
    @FXML
    private Button backToMenu;

    @FXML
    private Button exit;

    @FXML
    private TextField movieName;

    @FXML
    private TextField nowServing;

    @FXML
    private Button serveBtn;

    @FXML
    private TextField ticketCount;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File origFile = new File("ticket.txt");
        File temp = new File("temp.txt");
        
        if(origFile.exists() && origFile.length() != 0){
            serving();
        }
        
        serveBtn.setOnAction(event -> {
            
            if(origFile.exists() && origFile.length() != 0){
                try(BufferedReader reader = new BufferedReader(new FileReader(origFile));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(temp))){
                    String line;
                    boolean isFirstLine = true;

                      while ((line = reader.readLine()) != null) {
                          if (isFirstLine) {
                              isFirstLine = false; // skip first line
                              continue;
                          }
                          writer.write(line);
                          writer.newLine();
                      }

                      writer.flush();
                      writer.close();
                      reader.close();

                      origFile.delete();
                      temp.renameTo(origFile);
                  } catch(IOException e){
                      e.getMessage();
                  }

                  nowServing.clear();
                  movieName.clear();
                  ticketCount.clear();
                  arrivalTime.clear();

                  serving();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention");
            alert.setHeaderText("The Queue is Empty");
            
            alert.showAndWait();
            }
            
         
        });
    }    
   
    
    public void serving(){
        try(BufferedReader reader = new BufferedReader(new FileReader("ticket.txt"))){
          String line = reader.readLine();          
          String[] info = line.split(",");
          
          nowServing.setText(info[0]);
          movieName.setText(info[1]);
          ticketCount.setText(info[2]);
          arrivalTime.setText(info[4]); 
          
        } catch(IOException e){
            e.getMessage();
        }
    }
    
    public void exit(){
        System.exit(0);
    }
    
    public void menu() throws Exception{
        backToMenu.getScene().getWindow().hide();
        
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
}
