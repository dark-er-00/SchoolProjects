package airportsystem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class MainController implements Initializable {
    
    File waiting = new File("waiting.txt");
    File checked = new File("checked.txt");
    
    @FXML
    private Button addBtn;

    @FXML
    private Button checkInBtn;

    @FXML
    private TableView<checkedPerson> checkedInTable;

    @FXML
    private Button close;

    @FXML
    private TextField fieldName;

    @FXML
    private TableColumn<checkedPerson, String> nameChecked;

    @FXML
    private TableColumn<checkedPerson, String> nameWaiting;

    @FXML
    private TextFlow nowChecking;

    @FXML
    private TableColumn<checkedPerson, String> queueChecked;

    @FXML
    private TableColumn<checkedPerson, String> queueWaiting;

    @FXML
    private Button resetBtn;

    @FXML
    private TableView<checkedPerson> waitingQueue;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        close.setOnAction(event -> {
            System.exit(0);
        });
        

        
        LocalTime time = LocalTime.now();
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a");
        String formattedTime = time.format(formatter);
        
        addBtn.setOnAction(event -> {
            String name = fieldName.getText();
            
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(waiting, true))){
                writer.write(name + "," + formattedTime);
                writer.newLine();
                writer.flush();
            } catch (IOException e){
                e.getMessage();
            }
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Added Successfully!");
            alert.showAndWait();
            
            fieldName.clear();
            
            updateWaiting();
            updateChecking();
        });
        
        checkInBtn.setOnAction(event -> {
           if(!waiting.exists() || waiting.length() == 0){
               nowChecking.getChildren().clear();
               nowChecking.getChildren().add(new Text("No one is currently checking in."));
           } else {
               checkedBtn();
           }
           updateWaiting();
        });
        
        resetBtn.setOnAction(event -> {
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure?");
            
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if(waiting.exists() || checked.exists()){
                waiting.delete();
                checked.delete();
                
                updateWaiting();
                updateChecked();
                nowChecking.getChildren().clear();
           }
            }
        });
    }

    public void updateWaiting(){
            nameWaiting.setCellValueFactory(new PropertyValueFactory<>("name"));
            queueWaiting.setCellValueFactory(new PropertyValueFactory<>("time"));

            ObservableList<checkedPerson> list = FXCollections.observableArrayList();
            
            try(BufferedReader reader = new BufferedReader(new FileReader(waiting))){
                String line;
                
                while((line = reader.readLine()) != null){
                    String[] info = line.split(",");
                    
                    list.add(new checkedPerson(info[0], info[1]));
                }
            } catch (IOException e){
                e.getMessage();
            }
            
            waitingQueue.setItems(list);
    }
    
    public void updateChecking(){
        try (BufferedReader reader = new BufferedReader(new FileReader(waiting))) {
        String line = reader.readLine();
        
        nowChecking.getChildren().clear();
        
        if (line != null && !line.trim().isEmpty()) {
            String[] info = line.split(",");
            nowChecking.getChildren().add(new Text("\n"));
            nowChecking.getChildren().add(new Text(info[0]));
        } else {
            nowChecking.getChildren().add(new Text("\n"));
            nowChecking.getChildren().add(new Text("No one is currently checking in."));
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    public void checkedBtn(){
        File temp = new File("temp.txt");
        
        try(BufferedReader reader = new BufferedReader(new FileReader(waiting));
                BufferedWriter writer = new BufferedWriter(new FileWriter(checked, true));
                BufferedWriter tempWriter = new BufferedWriter(new FileWriter(temp))){
            
            String skip = reader.readLine();
            writer.write(skip);
            writer.newLine();
            
            String line;
            while((line = reader.readLine()) != null){
                tempWriter.write(line);
                tempWriter.newLine();
            }
            
            tempWriter.flush();
            tempWriter.close();
            
            writer.flush();
            writer.close();
            
            reader.close();
            
            waiting.delete();
            temp.renameTo(waiting);
            
        } catch (IOException e){
            e.getMessage();
        }
        
        updateChecked();
        
        updateChecking();
    }
    
    public void updateChecked(){
            nameChecked.setCellValueFactory(new PropertyValueFactory<>("name"));
            queueChecked.setCellValueFactory(new PropertyValueFactory<>("time"));

            ObservableList<checkedPerson> list = FXCollections.observableArrayList();
            
            try(BufferedReader reader = new BufferedReader(new FileReader(checked))){
                String line;
                
                while((line = reader.readLine()) != null){
                    String[] info = line.split(",");
                    
                    list.add(new checkedPerson(info[0], info[1]));
                }
            } catch (IOException e){
                e.getMessage();
            }
            
            checkedInTable.setItems(list);
    }
}
