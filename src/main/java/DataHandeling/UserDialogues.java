package DataHandeling;

import javax.swing.*;
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
}
