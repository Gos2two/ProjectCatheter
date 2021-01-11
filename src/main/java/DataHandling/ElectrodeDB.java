package DataHandling;

public class ElectrodeDB {
    private Unipolar[] ElectrodeArray;

    public ElectrodeDB(String path){
        //Open the ExcelFile
        ReadExcelFile Dataset = new ReadExcelFile(path);

        //Retrieve the dataset
        String[] names = Dataset.getElectrodeNames();
        Double[][] Data = Dataset.getDataset();
        Double[] tempData;
        ElectrodeArray = new Unipolar[Data[0].length];

        //Segment data into objects and construct DB


        for(int i=0;i<Data[0].length;i++){
            tempData = new Double[Data.length];
            for(int j=0;j<Data.length;j++){
                tempData[j] = Data[j][i];
            }
            ElectrodeArray[i] = new Unipolar(names[i],tempData);
        }
    }

    public Unipolar[] getElectrodeArray(){
        return ElectrodeArray;
    }

    public void printElectrodes(){
        for(int i=0;i<ElectrodeArray.length;i++){
            System.out.println(ElectrodeArray[i].getName());
            System.out.println(ElectrodeArray[i].getData().length);
        }
    }
}