package fcfs;

import javafx.beans.property.SimpleStringProperty;

public class Customer {
    private final SimpleStringProperty name;
    private final SimpleStringProperty movie;
    private final SimpleStringProperty ticket;
    private final SimpleStringProperty time;
    private final SimpleStringProperty arrivalOrder;

    public Customer(String name, String movie, String ticket, String time, String arrivalOrder) {
        this.name = new SimpleStringProperty(name);
        this.movie = new SimpleStringProperty(movie);
        this.ticket = new SimpleStringProperty(ticket);
        this.time = new SimpleStringProperty(time);
        this.arrivalOrder = new SimpleStringProperty(arrivalOrder);
    }

    public String getName() { return name.get(); }
    public String getMovie() { return movie.get(); }
    public String getTicket() { return ticket.get(); }
    public String getTime() { return time.get(); }
    public String getArrivalOrder() { return arrivalOrder.get(); }
}