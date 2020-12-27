package DataHandeling;

public class ElectodeDB {
    private Unipolar[] ElectodeArray;

    public ElectodeDB(String path){
        //Open the ExcelFile
        ReadExcelFile Dataset = new ReadExcelFile(path);

        //Retrieve the dataset
        String[] names = Dataset.getElectrodeNames();
        Double[][] Data = Dataset.getDataset();
        Double [] tempData = new Double[Data.length];
        ElectodeArray = new Unipolar[Data[1].length];

        //Segment data into objects and construct DB
        for(int i=0;i<Data[1].length;i++){
            for(int j=0;j<Data.length;j++){
                tempData[j] = Data[j][i];
            }
            ElectodeArray[i] = new Unipolar(names[i],tempData);
        }
    }

    public Unipolar[] getElectodeArray(){
        return ElectodeArray;
    }

    public void printElectodes(){
        for(int i=0;i<ElectodeArray.length;i++){
            System.out.println(ElectodeArray[i].getName());
            System.out.println(ElectodeArray[i].getData().length);
        }
    }
}
