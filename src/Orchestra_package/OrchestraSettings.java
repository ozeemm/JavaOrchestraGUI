package Orchestra_package;

public class OrchestraSettings {
    private String name = "Солнышко";
    private int minSongSounds = 5;
    private int maxSongSounds = 10;
    private int songSoundsDelay = 750;
    private boolean isRuNotes = false;

    public void setName(String name){ this.name = name; }
    public String getName(){ return name; }
    public void setSongSoundsDelay(int delay){ songSoundsDelay = delay; }
    public int getSongSoundsDelay(){ return songSoundsDelay; }
    public void setMinSongSounds(int sounds){ minSongSounds = sounds; }
    public int getMinSongSounds(){ return minSongSounds; }
    public void setMaxSongSounds(int sounds){ maxSongSounds = sounds; }
    public int getMaxSongSounds(){ return maxSongSounds; }
    public boolean getIsRuNotes(){ return isRuNotes; }
    public void setIsRuNotes(boolean value){ isRuNotes = value; }

}
