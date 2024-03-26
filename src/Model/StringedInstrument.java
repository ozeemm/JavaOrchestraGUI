package Model;

public class StringedInstrument extends NoteInstrument{

    private int strings; // Кол-во струн
    private int frets; // Кол-во ладов. Если 0, то безладовый инструмент
    private boolean isBow; // Есть ли смычок
    private float stringBreakChance; // Шанс того, что струна лопнет

    public void setStrings(int strings){
        this.strings = strings;
    }
    public int getStrings(){
        return strings;
    }
    public void setFrets(int frets) {
        this.frets = frets;
    }
    public int getFrets(){
        return frets;
    }
    public void setIsBow(boolean isBow){
        this.isBow = isBow;
    }
    public boolean getIsBow() {
        return isBow;
    }
    public void setStringBreakChance(float chance){
        stringBreakChance = chance;
    }
    public float getStringBreakChance(){
        return stringBreakChance;
    }

    @Override
    public Note playSound() {
        if(Math.random() < stringBreakChance){
            return null;
            //return Note.getNullNote();
        }

        return Note.getRandomNote(getMinNote(), getMaxNote());
    }
}
