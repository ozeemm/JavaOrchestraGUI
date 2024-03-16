package Logic;

import java.util.ArrayList;

public class NonNoteSound {
    private static ArrayList<String> possibleSounds = new ArrayList<String>(); // Доступные звуки
    private String sound;

    public NonNoteSound() {
        if(!possibleSounds.isEmpty())
            setSound(possibleSounds.get(0));
    }
    public NonNoteSound(String sound) {
        setSound(sound);

        if(!possibleSounds.contains(sound))
            addPossibleSound(sound);
    }

    public void setSound(String sound){
        this.sound = sound;
    }

    public String getSound(){
        return sound;
    }

    @Override
    public String toString() {
        return getSound();
    }

    public static void addPossibleSound(String sound){
        possibleSounds.add(sound);
    }

    public static void deletePossibleSound(NonNoteSound sound){
        possibleSounds.remove(sound.getSound());
    }

    public static ArrayList<String> getPossibleSounds(){
        return possibleSounds;
    }
}
