package DataHandling;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

//All classes have been tested using smaller datasets for easier comprehension
/*All tests have been carried out using the IntelliJ methods instead of Gradle, and that is why
the assertEquals function is not set as Assert.assertEquals(); */
public class ElectrodeDBTest {
    @Test
    public void ElectrodeDBTest() {
        ElectrodeDB eDB;
        Unipolar[] unipolar = new Unipolar[10];
        Unipolar[] datatest = new Unipolar[10];
        Double[] data;
        //Testing w/ 10x10 matrix to check correct allocation of information
        eDB = new ElectrodeDB("src/10x10.xlsx");

        //Initializing test data with the expected values (arbitrarily set to match the values on the .xlsx file)
        for (int i = 0; i < 10; i++) {
            data = new Double[10];
            for (int j = 0; j < 10; j++) {
                data[j] = 0.01 * (i + 1) + 0.1 * j;
            }
            unipolar[i] = new Unipolar("egmUTest" + (i + 1), data);
        }

        datatest = eDB.getUnipolarArray();

        boolean bOk = true;
        for (int i = 0; i < 10; i++) {
            //Compares the expected and actual names of the Unipolar electrodes
            assertEquals(datatest[i].getName(), unipolar[i].getName());
            for (int j = 0; j < 10; j++) {
                //Compares every value of every Unipolar inside their data matrices
                bOk &= (Math.abs(datatest[i].getData()[j]) - (unipolar[i].getData()[j]) < 0.00000000001);
            }
        }
        assertEquals(bOk, true);
    }
}