package Data;

import Model.*;

import java.util.ArrayList;

public class Repository {
    private Orchestra orchestra;
    private DBWorker dbWorker;

    public Orchestra getOrchestra(){
        return orchestra;
    }
    public void initDataFromDb(){
        if(dbWorker == null)
            initDbWorker();
        orchestra = new Orchestra();
        orchestra.setInstruments(dbWorker.getAllInstruments());
        orchestra.setMusicians(dbWorker.getMusicians(orchestra.getInstruments()));
    }
    public void initDbWorker(){
        dbWorker = new DBWorker();
        dbWorker.connect();
    }
    public void closeDbWorker(){
        dbWorker.closeConnection();
    }

    public OrchestraSettings getSettings(){ return orchestra.getSettings(); }
    public void setSettings(OrchestraSettings settings) { orchestra.setSettings(settings); }
    public ArrayList<MusicInstrument> getInstruments(){ return orchestra.getInstruments(); }
    public ArrayList<Musician> getMusicians(){ return orchestra.getMusicians(); }
    public void addInstrument(MusicInstrument instrument) {
        dbWorker.saveInstrument(instrument);
        orchestra.addInstrument(instrument);
    }
    public void deleteInstrument(MusicInstrument instrument){
        dbWorker.deleteInstrument(instrument);
        orchestra.deleteInstrument(instrument);
    }
    public void addMusician(Musician musician){
        dbWorker.saveMusician(musician);
        orchestra.addMusician(musician);
    }
    public void deleteMusician(Musician musician){
        dbWorker.deleteMusician(musician);
        orchestra.deleteMusicican(musician);
    }
}
