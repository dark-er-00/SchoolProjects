package fcfs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Roland Carl
 */
public class ViewQueueController implements Initializable {
    
    private static File file = new File("ticket.txt");
    private double x = 0;
    private double y = 0;
    
    @FXML
    private TableColumn<Customer, String> arrivalOrder;
    
    @FXML
    private Button backToMenu;

    @FXML
    private Button close;

    @FXML
    private TableColumn<Customer, String> movie;

    @FXML
    private TableColumn<Customer, String> name;

    @FXML
    private TableView<Customer> tableView;

    @FXML
    private TableColumn<Customer, String> ticket;

    @FXML
    private TableColumn<Customer, String> time;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    name.setCellValueFactory(new PropertyValueFactory<>("name"));
    movie.setCellValueFactory(new PropertyValueFactory<>("movie"));
    ticket.setCellValueFactory(new PropertyValueFactory<>("ticket"));
    time.setCellValueFactory(new PropertyValueFactory<>("time"));
    arrivalOrder.setCellValueFactory(new PropertyValueFactory<>("arrivalOrder"));

    ObservableList<Customer> list = FXCollections.observableArrayList();
    
    if(file.length() != 0){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            
            while((line = reader.readLine()) != null){
                String[] info = line.split(",");
                
                list.add(new Customer(info[0], info[1], info[2], info[3], info[4]));
            }
        } catch (IOException e){
            e.getMessage();
        }
    }
    
    tableView.setItems(list);
    }

    public void close(){
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
