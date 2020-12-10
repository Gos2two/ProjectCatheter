package PlotUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcel {

    public ReadExcel(){
        //REMEMBER TO CHANGE PATHNAME!!
        FileInputStream fis;
        fis=null;
        {
            try {
                fis = new FileInputStream(new File("/Macintosh HD/Users/sergigosalvez/Desktop/data.xlsx"));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }


}
