package PlotUI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

public class ReadExcel {
    private Double [][] Dataset;
    private String path;

    public ReadExcel(){
        Dialog();
        OpenExcelFile();
        printDataset();
    }

    private void OpenExcelFile(){

        try {
            FileInputStream file = new FileInputStream(new File(path));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Array Dimensions + Initialize Dataset
            int num_rows = sheet.getLastRowNum();
            int num_cols = sheet.iterator().next().getLastCellNum();
            //System.out.println(sheet.iterator().next().getLastCellNum());
            //System.out.println(sheet.getLastRowNum());
            initDataset(num_rows,num_cols);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int row_num=-1;

            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                int col_num=0;
                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            //System.out.print(cell.getNumericCellValue());
                            Dataset[row_num][col_num]=cell.getNumericCellValue();
                            break;
                        case STRING:
                            //System.out.print(cell.getStringCellValue());
                            break;
                    }
                    col_num++;
                }
                row_num++;
            }
            file.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initDataset(int num_rows,int num_cols){
        Dataset = new Double[num_rows][num_cols];
    }

    public void printDataset(){
        System.out.println(Arrays.deepToString(Dataset));
    }

    public Double[][] getDataset() {
        return Dataset;
    }

    private void Dialog(){
        //Frame and Panel for the Dialog Window.
        JFrame  frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);


        //Create a file chooser
       JFileChooser fileChooser = new JFileChooser();
       fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
       int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            setPath(selectedFile.getAbsolutePath());
        }

    }

    private void setPath(String path){
        this.path = path;
    }
}
