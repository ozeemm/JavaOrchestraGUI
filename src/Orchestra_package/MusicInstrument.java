package Orchestra_package;

public abstract class MusicInstrument {
    private String name;
    private boolean isAcoustic;

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
