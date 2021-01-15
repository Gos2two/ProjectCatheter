package DataHandling;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ElectrodeTest {
    @Test
    public void shouldGetName() {
        Electrode electrode;
        Double[] data = new Double[2];
        //Initialize an electrode w/ arbitrary values
        electrode = new Electrode("egmTest1", data);
        //Test the .getName() function
        Assert.assertEquals("egmTest1", electrode.getName());
    }

    @Test
    public void shouldGetData() {
        Electrode electrode;
        Double[] data = new Double[2];
        Double[] expectedData = new Double[2];
        //Initializing arbitrary values for data
        data[0] = 1.0;
        data[1] = 2.0;

        expectedData[0] = 1.0;
        expectedData[1] = 2.0;

        //Initialize an electrode w/ arbitrary parameters
        electrode = new Electrode("egmTest1", data);

        //Tests the .getData() function
        Assert.assertEquals(expectedData, electrode.getData());
    }
@Test
    public void shouldSetData() {
        Electrode electrode;
        Double[] data = new Double[2];
        Double[] expectedData = new Double[2];
        //Initializing arbitrary values for data
        data[0] = 1.0;
        data[1] = 2.0;

        expectedData[0] = 1.0;
        expectedData[1] = 2.0;

        electrode = new Electrode("test", data);
        //Initialize an electrode w/ arbitrary parameters
        electrode.setData(data);

        //Tests the setData() function
        Assert.assertEquals(expectedData, electrode.getData());
    }

    @Test
    public void shouldSetName(){
        Electrode electrode;
        Double[] data = new Double[0];
        //Initializing an object of class electrode w/arbitrary data
        electrode = new Electrode("test", data);
        electrode.setName("egmTest1");
        //Testing the setName() function
        Assert.assertEquals("egmTest1", electrode.getName());

    }
}