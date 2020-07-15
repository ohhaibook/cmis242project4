import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;
import java.util.TreeMap;

public class Project4
{
  public static void main(String[] args) {
    TreeMap<Integer, Property> propertyRecords = new TreeMap<>();

    // CREATE PANEL AND FRAME
    JFrame frame = new JFrame("Real Estate Database");
    JPanel mainPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    // DEFINE BUTTONS AND FIELDS
    mainPanel.setLayout(new GridLayout(1,2));
    leftPanel.setLayout(new GridLayout(0,1));
    rightPanel.setLayout(new GridLayout(0,1));

    // LEFT PANEL
    JLabel transNumLabel = new JLabel("Transaction No.:");
    JLabel addressLabel = new JLabel("Address:");
    JLabel bedroomsLabel = new JLabel("Bedrooms:");
    JLabel sqFtLabel = new JLabel("Square Footage:");
    JLabel priceLabel = new JLabel("Price:");
    transNumLabel.setHorizontalAlignment(JTextField.CENTER);
    addressLabel.setHorizontalAlignment(JTextField.CENTER);
    bedroomsLabel.setHorizontalAlignment(JTextField.CENTER);
    sqFtLabel.setHorizontalAlignment(JTextField.CENTER);
    priceLabel.setHorizontalAlignment(JTextField.CENTER);
    JButton processButton = new JButton("Process");
    JButton changeStatusButton = new JButton("Change Status");

    // RIGHT PANEL
    JTextField transNumInput = new JTextField();
    JTextField addressInput = new JTextField();
    JTextField bedroomsInput = new JTextField();
    JTextField sqFtInput = new JTextField();
    JTextField priceInput = new JTextField();
    String[] processArray = {"Insert", "Delete", "Find"};
    JComboBox processInput = new JComboBox(processArray);
    JComboBox statusInput = new JComboBox(State.values());

    // ADD ALL LABELS AND FIELDS TO PANEL
    leftPanel.add(transNumLabel);
    leftPanel.add(addressLabel);
    leftPanel.add(bedroomsLabel);
    leftPanel.add(sqFtLabel);
    leftPanel.add(priceLabel);
    leftPanel.add(processButton);
    leftPanel.add(changeStatusButton);

    rightPanel.add(transNumInput);
    rightPanel.add(addressInput);
    rightPanel.add(bedroomsInput);
    rightPanel.add(sqFtInput);
    rightPanel.add(priceInput);
    rightPanel.add(processInput);
    rightPanel.add(statusInput);

    processButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int transNum, bedrooms, sqFt, price;
        String address;
        Property property;
        Object action = processInput.getSelectedItem();
        try {
          transNum = Integer.parseInt(transNumInput.getText());
        } catch(Exception ex) {
          JOptionPane.showMessageDialog(frame, "Non-int values detected");
          return;
        }
        if(action == "Insert") {
          try {
            transNum = Integer.parseInt(transNumInput.getText());
            address = addressInput.getText();
            bedrooms = Integer.parseInt(bedroomsInput.getText());
            sqFt = Integer.parseInt(sqFtInput.getText());
            price = Integer.parseInt(priceInput.getText());
            property = new Property(address, bedrooms, sqFt, price);
            propertyRecords.put(transNum, property);
            JOptionPane.showMessageDialog(frame, "Successfully added following property under transaction number " + transNum + "\n" + property.toString());
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Non-int values detected");
          }
        } else if (action == "Delete") {
          try {
            propertyRecords.get(transNum).toString();
            propertyRecords.remove(transNum);
            JOptionPane.showMessageDialog(frame, "Successfully deleted transaction number " + transNum);
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "No record with transaction number " + transNum + " to remove");
          }
        } else if (action == "Find") {
          try {
            String propertyInfo = propertyRecords.get(transNum).toString();
            JOptionPane.showMessageDialog(frame, "Transaction number " + transNum + "\n" + propertyInfo);
          } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "No record with transaction number " + transNum + " to display");
          }
        }
      }
    });

    changeStatusButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        State state = State.valueOf(statusInput.getSelectedItem().toString());
        int transNum;
        try {
          transNum = Integer.parseInt(transNumInput.getText());
        } catch(Exception ex) {
          JOptionPane.showMessageDialog(frame, "Non-int values detected");
          return;
        }
        try {
          String propertyInfo = propertyRecords.get(transNum).toString();
          propertyRecords.get(transNum).changeState(state);
          JOptionPane.showMessageDialog(frame, "Changed status of transaction number " + transNum + " to " + state);
        } catch (Exception ex) {
          JOptionPane.showMessageDialog(frame, "No record with transaction number " + transNum + " to change");
        }
      }
    });

    // FINALIZE
    mainPanel.add(leftPanel);
    mainPanel.add(rightPanel);
    frame.add(mainPanel);
    frame.setSize(500,300);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
	}
}
