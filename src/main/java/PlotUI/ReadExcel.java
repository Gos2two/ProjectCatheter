package PlotUI;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ReadExcel {
    private Double [][] Dataset;

    public ReadExcel(){
        OpenExcelFile();
        System.out.println(Arrays.deepToString(Dataset));
    }

    public void OpenExcelFile(){

        try {
            FileInputStream file = new FileInputStream(new File("/Users/dimitrisnicolaides/Desktop/egmUNI.xlsx"));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Array Dimensions
            int num_rows = sheet.getLastRowNum();
            int num_cols = sheet.iterator().next().getLastCellNum();
            //System.out.println(sheet.iterator().next().getLastCellNum());
            //System.out.println(sheet.getLastRowNum());
            Dataset = new Double[num_rows+1][num_cols];

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int row_num=0;

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
                            Dataset[row_num][col_num]=0.0;
                            break;
                    }
                    col_num++;
                }
                System.out.println("");
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
}
