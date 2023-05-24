import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class showFlights extends JFrame {
    private JTextField tfSource;
    private JTextField tfDestination;
    private JTextField tfSeats;
    private JTextField tfDepartureDate;
    private JButton btnSearch;
    private JPanel showFlightsPanel;
    private JLabel departureDate;
    private JButton backButton;
    private User user;
//    private Flight flight;

    public showFlights(User user) {
        this.user = user;
        setTitle("Showing a list of available flights");
        setContentPane(showFlightsPanel);
        setPreferredSize(new Dimension(600, 600));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchFlight();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerMenu menu = new customerMenu(user);
                menu.setVisible(true);
                dispose();
            }
        });
    }

    private void searchFlight() {
        String source = tfSource.getText();
        String destination = tfDestination.getText();
        String seats = tfSeats.getText();
        String departureDate = tfDepartureDate.getText();
        if (source.equals("") || destination.equals("") || seats.equals("") || departureDate.equals("")){
            JOptionPane.showMessageDialog(null, "Please fill all the fields!");
        } else {
            try {
                int seatsInt = Integer.parseInt(seats);
                if (seatsInt <= 0) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number of seats!");
                } else {
                    tableFlights table = new tableFlights(user,source, destination, seatsInt , departureDate);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number of seats!");
            }
        }
    }
}
