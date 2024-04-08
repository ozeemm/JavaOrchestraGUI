package Model;

public abstract class MusicInstrument {
    private int id;
    private String name;
    private boolean isAcoustic;

    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public boolean getIsAcoustic(){
        return isAcoustic;
    }

    public void setIsAcoustic(boolean isAcoustic){
        this.isAcoustic = isAcoustic;
    }

    public abstract String orchestraPlay(boolean isRuNotes);
}
