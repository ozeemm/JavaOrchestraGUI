package Orchestra_package;

public class WindInstrument extends NoteInstrument {

    private String material; // Материал инструмента
    private String type; // Тип инструмента
    private float notEnoughBreathChance; // Шанс того, что музыканту не хватит дыхания

    public void setMaterial(String material) { this.material = material; }
    public String getMaterial(){ return this.material; }
    public void setType(String type) { this.type = type; }
    public String getType(){ return this.type; }

    public void setNotEnoughBreathChance(float chance){ notEnoughBreathChance = chance; }
    public float getNotEnoughBreathChance(){ return notEnoughBreathChance; }

    @Override
    public Note playSound() {
        if(Math.random() < notEnoughBreathChance){
            return null;
            //return Note.getNullNote();
        }

        return Note.getRandomNote(getMinNote(), getMaxNote());
    }
}
