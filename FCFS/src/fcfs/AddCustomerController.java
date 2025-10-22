package fcfs;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 * FXML Controller class
 *
 * @author Roland Carl
 */
public class AddCustomerController implements Initializable {
    
    static File file = new File("ticket.txt");
    private double x = 0;
    private double y = 0;
    private int arrivalOrder = 1;
    
    @FXML
    private ComboBox<String> SelectMovie;

    @FXML
    private Button addBtn;

    @FXML
    private Button backToMenu;

    @FXML
    private Button close;

    @FXML
    private TextField nameField;

    @FXML
    private TextFlow textFlow;

    @FXML
    private Spinner<Integer> ticketCount;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(file.exists() && file.length() != 0){
            arrivalOrder = updateArrivalOrder() + 1;
        }
        
        LocalTime time = LocalTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
        String formattedTime = time.format(formatter);
        
        close.setOnAction(event -> {
           System.exit(0);
        });
        
        SpinnerValueFactory<Integer> valueFactory = 
    new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        
        ticketCount.setEditable(false);
        ticketCount.setValueFactory(valueFactory);
        
        SelectMovie.getItems().addAll("Titanic", "End", "The Fall");
        
        addBtn.setOnAction(event -> {
            String name = nameField.getText();
            String movie = SelectMovie.getValue();
            int count = ticketCount.getValue();
        
            if(name != null && movie != null && count != 0){
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
                writer.write(name + "," + movie + "," + count + "," + formattedTime + "," + arrivalOrder);
                writer.newLine();
                writer.flush();
                
                arrivalOrder += 1;
                
                textFlow.getChildren().clear();
                
                textFlow.getChildren().add(new Text("Added Successfully"));
            } catch (IOException e){
                e.getMessage();
            }
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Attention");
            alert.setHeaderText("Hello");
            alert.setContentText("Complete all the Details");
            
            alert.showAndWait();
        }
        });
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
    
    static int updateArrivalOrder(){
        int highest = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader("ticket.txt"))){
            String line;

            while((line = reader.readLine()) != null){
                String[] book = line.split(",");

                int toInt = Integer.parseInt(book[4]);

                if(highest < toInt){
                    highest = toInt;
                }
            }

            reader.close();
        } catch (IOException e) {
            e.getMessage();
        }

        return highest;
    }
}
