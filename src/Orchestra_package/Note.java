package Orchestra_package;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Note{
    private static ArrayList<String> possibleNotes; // Доступные ноты
    private static HashMap<Character, String> ruNotes; // Ноты на русском языке

    static { // On creating first class
        // init possibleNotes
        possibleNotes = new ArrayList<String>();
        for(String a : new String[] {"C", "D", "E", "F", "G", "A", "B"}){
            if(a != "F" && a != "C")
                possibleNotes.add(a+"b");
            possibleNotes.add(a);
            if(a != "E" && a != "B")
                possibleNotes.add(a+"#");
        }

        // init ruNotes
        ruNotes = new HashMap<Character, String>();
        ruNotes.put('C', "До");
        ruNotes.put('D', "Ре");
        ruNotes.put('E', "Ми");
        ruNotes.put('F', "Фа");
        ruNotes.put('G', "Соль");
        ruNotes.put('A', "Ля");
        ruNotes.put('B', "Си");
    }
    private String note; // Название ноты
    private int octave; // Октава

    public Note() {
        setNote(possibleNotes.get(0));
        setOctave(0);
    }
    public Note(String note, int octave) {
        setNote(note);
        setOctave(octave);
    }

    public String getNote(){
        return note;
    }

    public String getRuNote(){
        String ruNote = ruNotes.get(note.charAt(0));
        if(note.length() > 1){
            if(note.charAt(1) == '#')
                ruNote += "-диез";
            else if(note.charAt(1) == 'b')
                ruNote += "-бемоль";
        }
        return ruNote;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public int getOctave(){
        return octave;
    }
    public void setOctave(int octave){
        this.octave = octave;
    }

    public static Note getRandomNote(Note minNote, Note maxNote){
        Random rand = new Random();
        int rand_octave = rand.nextInt(maxNote.getOctave()-minNote.getOctave()+1) + minNote.getOctave();
        int rand_note_ind;
        if(rand_octave == minNote.octave){
            rand_note_ind = rand.nextInt(possibleNotes.size()-possibleNotes.indexOf(minNote.getNote())) + possibleNotes.indexOf(minNote.getNote());
        }
        else if(rand_octave == maxNote.octave){
            rand_note_ind = rand.nextInt(possibleNotes.size()-possibleNotes.indexOf(minNote.getNote()));
        }
        else{
            rand_note_ind = rand.nextInt(possibleNotes.size());
        }
        Note rand_sound = new Note();
        rand_sound.setOctave(rand_octave);
        rand_sound.setNote(possibleNotes.get(rand_note_ind));
        return rand_sound;
    }

    @Override
    public String toString() {
        return (this.note + ' ' + this.octave);
    }

    public String toRuString(){
        return (this.getRuNote() + ' ' + this.octave);
    }
}
