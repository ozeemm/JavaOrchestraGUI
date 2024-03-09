package Orchestra_package;

import java.util.ArrayList;
import java.util.Random;

public class Orchestra {
    private OrchestraSettings orchestraSettings;
    private ArrayList<Musician> musicians;
    private ArrayList<MusicInstrument> musicInstruments;

    public Orchestra(){
        orchestraSettings = new OrchestraSettings();
        musicInstruments = new ArrayList<MusicInstrument>();
        musicians = new ArrayList<Musician>();
    }

    public OrchestraSettings getSettings(){ return orchestraSettings; }

    public void setSettings(OrchestraSettings settings){ orchestraSettings = settings; }

    public ArrayList<MusicInstrument> getInstruments(){
        return musicInstruments;
    }
    public void setInstruments(ArrayList<MusicInstrument> instruments){
        musicInstruments = instruments;
    }
    public void addInstrument(MusicInstrument instrument){
        musicInstruments.add(instrument);
    }
    public void deleteInstrument(MusicInstrument instrument){
        musicInstruments.remove(instrument);
    }
    public void setMusicians(ArrayList<Musician> musicians){ this.musicians = musicians; }
    public ArrayList<Musician> getMusicians(){ return musicians; }
    public void addMusician(Musician musician){ musicians.add(musician); }
    public void deleteMusicican(Musician musician){ musicians.remove(musician); }

    public int getRandomSongSoundsNum(){
        Random rand = new Random();
        if(orchestraSettings.getMaxSongSounds() == orchestraSettings.getMinSongSounds())
            return orchestraSettings.getMaxSongSounds();
        else
            return rand.nextInt(orchestraSettings.getMaxSongSounds() - orchestraSettings.getMinSongSounds()) + orchestraSettings.getMinSongSounds() + 1;
    }
    // Сыграть звук песни
    public String playSound(){
        Random random = new Random();
        int musicianInd = random.nextInt(musicians.size());
        Musician musician = musicians.get(musicianInd);
        String sound = musician.getInstrument().orchestraPlay(orchestraSettings.getIsRuNotes());

        if(sound != null)
            return musician.getName() + " сыграл на " + musician.getInstrument().getName() + ": " + sound;
        else
            return musician.getName() + " не смог сыграть на " + musician.getInstrument().getName();
    }
}
