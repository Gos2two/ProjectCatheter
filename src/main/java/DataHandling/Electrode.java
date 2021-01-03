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

    public Double[] getData(){
        return Data;
    }
}
