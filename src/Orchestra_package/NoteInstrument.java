package Orchestra_package;

public abstract class NoteInstrument extends MusicInstrument{
    private Note minNote;
    private Note maxNote;

    public Note getMinNote(){
        return minNote;
    }

    public Note getMaxNote(){
        return maxNote;
    }

    public void setNoteRange(Note minNote, Note maxNote){
        this.minNote = minNote;
        this.maxNote = maxNote;
    }

    public abstract Note playSound();

    public String orchestraPlay(boolean isRuNotes) {
        Note note = playSound();
        if(note == null)
            return null;
        else if(isRuNotes)
            return note.toRuString();
        else
            return note.toString();
    }
}
