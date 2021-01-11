package DataHandling;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class UserDialogues {
    protected String excelFilePath;

    public UserDialogues(){
    }
    public String getExcelFilePath(){
        return excelFilePath;
    }
    protected void setExcelFilePath(String excelFilePath){
        this.excelFilePath=excelFilePath;
    }
    public void getFileSource(){
        //Frame and Panel for the Dialog Window.
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        //Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            setExcelFilePath(selectedFile.getAbsolutePath());
        }
    }
    public int[] getGridDimensions(){
        //Define temporal int[] to store grid dimensions
        int[] gridDimensions = new int[2];
        JTextField rowField = new JTextField(5);
        JTextField colField = new JTextField(5);

        //Setting up panel with text fields for the user to input values
        JPanel myPanel= new JPanel();
        myPanel.add(new JLabel("Number of rows:"));
        myPanel.add(rowField);
        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("Number of columns:"));
        myPanel.add(colField);

        //Define and scale new ImageIcon
        ImageIcon icon= new ImageIcon(new ImageIcon("src/dimension.png").getImage().getScaledInstance(35,
                35, Image.SCALE_SMOOTH));

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Please Enter Catheter Dimensions", JOptionPane.OK_CANCEL_OPTION,JOptionPane.PLAIN_MESSAGE,icon);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println("Number of rows: " + rowField.getText());
            try
            {
                // the String to int conversion happens here
                 gridDimensions[0] = Integer.parseInt((rowField.getText()).trim());
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
            System.out.println("Number of columns: " + colField.getText());
            try
            {
                // the String to int conversion happens here
                gridDimensions[1] = Integer.parseInt((colField.getText()).trim());
            }
            catch (NumberFormatException nfe)
            {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }

        }
        return gridDimensions;
    }
}
