import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMenu extends JFrame{
    private JButton btnUpdateProfile;
    private JButton btnAddAircraft;
    private JButton btnAddFlight;
    private JButton btnUpdateAircraft;
    private JButton btnUpdateFlight;
    private JButton btnVeiwReport;
    private JButton backButton;
    private JPanel adminMenuPanel;
    User user;

    public AdminMenu(User user) {
        this.user = user;
    setTitle("Admin Menu");
    setContentPane(adminMenuPanel);
    setPreferredSize(new Dimension(600, 600));
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    btnUpdateProfile.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            adminUpdate update = new adminUpdate(user);
            update.setVisible(true);
            dispose();
        }
    });
        btnAddAircraft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAircraft aircraft = new AddAircraft(user);
                aircraft.setVisible(true);
                dispose();
            }
        });
        btnAddFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 AddFlight flight = new AddFlight(user);
                 flight.setVisible(true);
                 dispose();
            }
        });
        btnUpdateAircraft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String aircraftID = JOptionPane.showInputDialog(adminMenuPanel, "Enter Aircraft ID:", "Update Aircraft", JOptionPane.PLAIN_MESSAGE);
                updateAircraft aircraft = new updateAircraft(aircraftID,user);
                aircraft.setVisible(true);
                dispose();
            }
        });
        btnUpdateFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String flightID = JOptionPane.showInputDialog(adminMenuPanel, "Enter Flight ID:", "Update Flight", JOptionPane.PLAIN_MESSAGE);
                UpdateFlight flight = new UpdateFlight(flightID,user);
                flight.setVisible(true);
                dispose();
            }
        });

        btnVeiwReport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                meaningReport report = new meaningReport();
                report.setVisible(true);
                dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminForm adminForm = new adminForm();
                adminForm.setLocationRelativeTo(null);
                adminForm.setVisible(true);
                adminForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
    }
}
