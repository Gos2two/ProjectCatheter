package DataHandling;

public class ElectrodeDB implements UserDialogues {
    private Unipolar[] UnipolarArray;
    private Bipolar[] BipolarArray;

    public ElectrodeDB(String path){
        initUnipolarArray(path);
        initBipolarArray();
    }

    public Unipolar[] getUnipolarArray(){
        return UnipolarArray;
    }

    public void printElectrodes(){
        for(int i = 0; i< UnipolarArray.length; i++){
            System.out.println(UnipolarArray[i].getName());
            System.out.println(UnipolarArray[i].getData().length);
        }
    }

    private void initUnipolarArray(String path){
        //Open the ExcelFile
        ReadExcelFile Dataset = new ReadExcelFile(path);

        //Retrieve the dataset
        String[] names = Dataset.getElectrodeNames();
        Double[][] Data = Dataset.getDataset();
        Double[] tempData;
        UnipolarArray = new Unipolar[Data[0].length];

        //Segment data into objects and construct DB


        for(int i=0;i<Data[0].length;i++){
            tempData = new Double[Data.length];
            for(int j=0;j<Data.length;j++){
                tempData[j] = Data[j][i];
            }
            UnipolarArray[i] = new Unipolar(names[i],tempData);
        }
    }

    private void initBipolarArray(){
        int[] dim = UserDialogues.getGridDimensions();
        int rows = dim[0];
        int cols = dim[1];
        int combs_rows = dim[0]*(dim[1]-1);
        int combs_cols = dim[1]*(dim[0]-1);

        BipolarArray =  new Bipolar[combs_rows+combs_cols];
        for(int i=0; i<rows;i++){
            for(int j=0; j<cols-1;j++){
                BipolarArray[(cols-1)*i + j] = new Bipolar(UnipolarArray[(cols-1)*i + j],UnipolarArray[(cols-1)*i +j+1]);
            }
        }
        for(int j=0; j<cols;j++){
            for(int i=0; i<rows-1;i++){
                BipolarArray[combs_rows + (rows-1)*j + i] = new Bipolar(UnipolarArray[i*(cols) +j],UnipolarArray[(i+1)*(cols) +j]);
            }
        }
        for(int k=0;k<combs_rows+combs_cols;k++){
           System.out.println(BipolarArray[k].getName());
        }
    }
}