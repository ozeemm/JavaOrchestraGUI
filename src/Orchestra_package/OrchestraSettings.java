package Orchestra_package;

public class OrchestraSettings {
    private String name = "Солнышко";
    private int songSoundsDelay = 1250;
    private boolean isRuNotes = false;

    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }
    public void setSongSoundsDelay(int delay){ songSoundsDelay = delay; }
    public int getSongSoundsDelay(){ return songSoundsDelay; }
    public boolean getIsRuNotes(){ return isRuNotes; }
    public void setIsRuNotes(boolean value){ isRuNotes = value; }

}
