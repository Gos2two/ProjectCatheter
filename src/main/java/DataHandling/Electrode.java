package DataHandling;

public class Electrode {
    private String name;
    private Double[] Data;

    public Electrode(String name,Double[] Data){
        this.name = name;
        this.Data = Data;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {this.name = name;}

    public Double[] getData(){
        return Data;
    }

    public void setData(Double[] Data){this.Data = Data;}
}
