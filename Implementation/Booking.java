public class Booking {
    private int customerID;
    private int flightID;
    private String bookingDate;

public Booking(int customerID, int flightID) {
        this.customerID = customerID;
        this.flightID = flightID;
        this.bookingDate = bookingDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getFlightID() {
        return flightID;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

}
