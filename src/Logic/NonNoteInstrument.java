package Logic;

import java.util.ArrayList;
import java.util.Random;

public class NonNoteInstrument extends MusicInstrument {

    private ArrayList<NonNoteSound> mySounds = new ArrayList<NonNoteSound>();

    public void setSounds(ArrayList<NonNoteSound> sounds){ mySounds.addAll(sounds); }

    public ArrayList<NonNoteSound> getSounds(){ return mySounds; }

    public void addSound(NonNoteSound sound){
        mySounds.add(sound);
    }

    public void deleteSound(NonNoteSound sound){
        mySounds.remove(sound);
    }

    public NonNoteSound playSound(){
        Random rand = new Random();
        return mySounds.get(rand.nextInt(mySounds.size()));
    }

    @Override
    public String orchestraPlay(boolean isRuNotes) {
        return playSound().toString();
    }
}
