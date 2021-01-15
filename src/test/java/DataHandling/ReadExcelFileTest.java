package DataHandling;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

//Note that the Mockito library couldn't be implemented, hence the ReadExcelFile method was tested
//first as it is required to test the rest of the methods both in this test class and the others.
public class ReadExcelFileTest {

    @Test
    public void shouldReadExcelFile(){
        boolean bThrown;
        try {
            ReadExcelFile file;
            file = new ReadExcelFile("src/data.xlsx");
            file.OpenExcelFile("src/data.xlsx");
            bThrown = false;
        }
        catch (IndexOutOfBoundsException e){
            bThrown = true;
        }
        assertEquals(bThrown, false);
    }
    @Test
    public void shouldNotReadNonExcelFile(){
        boolean bThrown;
        try {
            ReadExcelFile file;
            file = new ReadExcelFile("src/void.txt");
            file.OpenExcelFile("src/void.txt");
            bThrown = false;
        }
        catch (Exception e){
            bThrown = true;
        }
        assertEquals(bThrown, true);
    }

    @Test
    public void shouldGetDataset(){
        ReadExcelFile file;
        //Test Excel File generated to test if the dataset is set correctly
        file = new ReadExcelFile("src/mini.xlsx");
        Double[][] Dataset = new Double[2][2];
        Double[][] datatest = new Double[2][2];
        datatest [0][0] = -0.22419999539852142;
        datatest [0][1] = -0.09539999812841415;
        datatest [1][0] = -0.23479999601840973;
        datatest [1][1] = -0.10750000178813934;
        Dataset = file.getDataset();
        assertArrayEquals(Dataset, datatest);
    }
    @Test
    public void shouldGetElectrodeNames() {
        ReadExcelFile file;
        //Test Excel File generated to test if the dataset is set correctly
        file = new ReadExcelFile("src/mini.xlsx");
        String[] ElectrodeNames;
        String[] TestNames = new String[2];
        ElectrodeNames = file.getElectrodeNames();
        TestNames[0] = "mini-egmUNI1";
        TestNames[1] = "mini-egmUNI2";

        assertArrayEquals(ElectrodeNames, TestNames);
    }


}