package Orchestra_package;

public class KeyboardInstrument extends NoteInstrument {

    private int keys; // Количество клавиш

    public void setKeys(int keys) { this.keys = keys; }
    public int getKeys(){ return keys; }

    @Override
    public Note playSound() {
        return Note.getRandomNote(getMinNote(), getMaxNote());
    }
}
