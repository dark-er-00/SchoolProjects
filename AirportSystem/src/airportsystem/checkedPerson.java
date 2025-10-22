
package airportsystem;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class checkedPerson {
    private final SimpleStringProperty name;
    private final SimpleStringProperty time;
    
    public checkedPerson(String name, String time) {
        this.name = new SimpleStringProperty(name);
        this.time = new SimpleStringProperty(time);
    }
    
    public String getTime() {
    return time.get();
}

    public void setTime(String newTime) {
        time.set(newTime);
    }

    public StringProperty timeProperty() {
        return time;
    }
    
    public String getName() {
    return name.get();
    }

    public void setName(String newName) {
        name.set(newName);
    }

    public StringProperty nameProperty() {
        return name;
    }
}
