package DataHandling;

public class Bipolar extends Electrode {
    public Bipolar(Unipolar Active,Unipolar Reference){
        //Parent Constructor
        super("",null);

        //Calculate Difference
        Double[] Data = new Double[Active.getData().length];
        for(int i=0;i<Active.getData().length;i++) {
            Data[i] = Active.getData()[i] - Reference.getData()[i];
        }
        super.setData(Data);

        String name = Active.getName() + " / " +Reference.getName();
        super.setName(name);
        }
    }


