package Orchestra_package;

public class Musician {
    private String name;
    private int age;
    private int joiningOrchestraYear;
    private MusicInstrument instrument;

    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }
    public void setAge(int age){ this.age = age; }
    public int getAge(){ return age; }
    public void setJoiningOrchestraYear(int year){ joiningOrchestraYear = year; }
    public int getJoiningOrchestraYear(){ return joiningOrchestraYear; }
    public void setInstrument(MusicInstrument instrument){ this.instrument = instrument; }
    public MusicInstrument getInstrument(){ return instrument; }
}
