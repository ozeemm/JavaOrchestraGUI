package Controller;

import Model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBWorker {
    private final String url = "jdbc:postgresql://localhost:5432/orchestra";
    private final String user = "postgres";
    private final String password = "postgres";
    private Connection connection;
    public void connect(){
        Properties auth = new Properties();
        auth.setProperty("user", user);
        auth.setProperty("password", password);

        try{
            connection = DriverManager.getConnection(url, auth);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void closeConnection(){
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public ArrayList<StringedInstrument> getStringedInstruments(){
        try {
            ArrayList<StringedInstrument> instruments = new ArrayList<StringedInstrument>();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM stringed_instruments_view";
            ResultSet table = statement.executeQuery(query);

            while(table.next()){
                StringedInstrument instrument = new StringedInstrument();
                instrument.setId(table.getInt("id"));
                instrument.setName(table.getString("name"));
                instrument.setIsAcoustic(table.getBoolean("acoustic"));
                String minNoteString = table.getString("min_note");
                Note minNote = new Note(minNoteString.split(" ")[0], Integer.parseInt(minNoteString.split(" ")[1]));
                String maxNoteString = table.getString("max_note");
                Note maxNote = new Note(maxNoteString.split(" ")[0], Integer.parseInt(maxNoteString.split(" ")[1]));
                instrument.setNoteRange(minNote, maxNote);
                instrument.setStrings(table.getInt("strings"));
                instrument.setFrets(table.getInt("frets"));
                instrument.setIsBow(table.getBoolean("bow"));
                instrument.setStringBreakChance(table.getFloat("string_break_chance"));
                instruments.add(instrument);
            }
            table.close();
            statement.close();

            return instruments;
        } catch(SQLException e){ e.printStackTrace(); return null; }
    }
    public ArrayList<KeyboardInstrument> getKeyboardInstruments(){
        try {
            ArrayList<KeyboardInstrument> instruments = new ArrayList<KeyboardInstrument>();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM keyboard_instruments_view";
            ResultSet table = statement.executeQuery(query);

            while(table.next()){
                KeyboardInstrument instrument = new KeyboardInstrument();
                instrument.setId(table.getInt("id"));
                instrument.setName(table.getString("name"));
                instrument.setIsAcoustic(table.getBoolean("acoustic"));
                String minNoteString = table.getString("min_note");
                Note minNote = new Note(minNoteString.split(" ")[0], Integer.parseInt(minNoteString.split(" ")[1]));
                String maxNoteString = table.getString("max_note");
                Note maxNote = new Note(maxNoteString.split(" ")[0], Integer.parseInt(maxNoteString.split(" ")[1]));
                instrument.setNoteRange(minNote, maxNote);
                instrument.setKeys(table.getInt("keys"));
                instruments.add(instrument);
            }
            table.close();
            statement.close();

            return instruments;
        } catch(SQLException e){ e.printStackTrace(); return null; }
    }
    public ArrayList<WindInstrument> getWindInstruments(){
        try {
            ArrayList<WindInstrument> instruments = new ArrayList<WindInstrument>();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM wind_instruments_view";
            ResultSet table = statement.executeQuery(query);

            while(table.next()){
                WindInstrument instrument = new WindInstrument();
                instrument.setId(table.getInt("id"));
                instrument.setName(table.getString("name"));
                instrument.setIsAcoustic(table.getBoolean("acoustic"));
                String minNoteString = table.getString("min_note");
                Note minNote = new Note(minNoteString.split(" ")[0], Integer.parseInt(minNoteString.split(" ")[1]));
                String maxNoteString = table.getString("max_note");
                Note maxNote = new Note(maxNoteString.split(" ")[0], Integer.parseInt(maxNoteString.split(" ")[1]));
                instrument.setNoteRange(minNote, maxNote);
                instrument.setMaterial(table.getString("material"));
                instrument.setType(table.getString("type"));
                instrument.setNotEnoughBreathChance(table.getFloat("not_enough_breath_chance"));
                instruments.add(instrument);
            }
            table.close();
            statement.close();

            return instruments;
        } catch(SQLException e){ e.printStackTrace(); return null; }
    }
    public ArrayList<NonNoteInstrument> getNonNoteInstruments(){
        try {
            ArrayList<NonNoteInstrument> instruments = new ArrayList<NonNoteInstrument>();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM non_note_instruments_view";
            ResultSet table = statement.executeQuery(query);

            while(table.next()){
                NonNoteInstrument instrument = new NonNoteInstrument();
                instrument.setId(table.getInt("id"));
                instrument.setName(table.getString("name"));
                instrument.setIsAcoustic(table.getBoolean("acoustic"));

                String[] sounds = table.getString("sounds").split("\\|");
                for(String soundStr : sounds) {
                    instrument.addSound(new NonNoteSound(soundStr));
                }
                instruments.add(instrument);
            }
            table.close();
            statement.close();

            return instruments;
        } catch(SQLException e){ e.printStackTrace(); return null; }
    }
    public ArrayList<MusicInstrument> getAllInstruments(){
        ArrayList<MusicInstrument> instruments = new ArrayList<MusicInstrument>();
        instruments.addAll(getStringedInstruments());
        instruments.addAll(getKeyboardInstruments());
        instruments.addAll(getWindInstruments());
        instruments.addAll(getNonNoteInstruments());
        return instruments;
    }
    public ArrayList<Musician> getMusicians(ArrayList<MusicInstrument> instruments){
        try {
            ArrayList<Musician> musicians = new ArrayList<Musician>();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM musicians";
            ResultSet table = statement.executeQuery(query);

            while (table.next()){
                Musician musician = new Musician();
                musician.setId(table.getInt("id"));
                musician.setName(table.getString("name"));
                musician.setAge(table.getInt("age"));
                musician.setJoiningOrchestraYear(table.getInt("joining_orchestra_year"));
                int instrumentId = table.getInt("instrument");
                for(MusicInstrument instrument : instruments){
                    if(instrument.getId() == instrumentId){
                        musician.setInstrument(instrument);
                        break;
                    }
                }
                musicians.add(musician);
            }

            statement.close();
            return musicians;
        } catch(SQLException e){ e.printStackTrace(); return null; }
    }

    public void saveInstrument(MusicInstrument instrument){
        try{
            String query = "INSERT INTO music_instruments(name, acoustic) VALUES(?, ?) RETURNING id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, instrument.getName());
            preparedStatement.setBoolean(2, instrument.getIsAcoustic());
            ResultSet table = preparedStatement.executeQuery();
            table.next();
            instrument.setId(table.getInt("id"));
            table.close();
            //String instrumentType = "";
            if(instrument instanceof NoteInstrument){
                NoteInstrument noteInstrument = (NoteInstrument)instrument;
                query = "INSERT INTO note_instruments(music_instrument_id, min_note, max_note) VALUES(?, ?, ?) RETURNING id";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, instrument.getId());
                preparedStatement.setString(2, noteInstrument.getMinNote().toString());
                preparedStatement.setString(3, noteInstrument.getMaxNote().toString());
                table = preparedStatement.executeQuery();
                table.next();
                int noteInstrumentId = table.getInt("id");
                table.close();

                if(instrument instanceof StringedInstrument){
                    StringedInstrument stringedInstrument = (StringedInstrument)instrument;
                    query = "INSERT INTO stringed_instruments(note_instrument_id, strings, frets, bow, string_break_chance) VALUES(?, ?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, noteInstrumentId);
                    preparedStatement.setInt(2, stringedInstrument.getStrings());
                    preparedStatement.setInt(3, stringedInstrument.getFrets());
                    preparedStatement.setBoolean(4, stringedInstrument.getIsBow());
                    preparedStatement.setFloat(5, stringedInstrument.getStringBreakChance());
                    preparedStatement.execute();
                }
                else if(instrument instanceof KeyboardInstrument){
                    KeyboardInstrument keyboardInstrument = (KeyboardInstrument)instrument;
                    query = "INSERT INTO keyboard_instruments(note_instrument_id, keys) VALUES(?, ?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, noteInstrumentId);
                    preparedStatement.setInt(2, keyboardInstrument.getKeys());
                    preparedStatement.execute();

                } else if(instrument instanceof WindInstrument){
                    WindInstrument windInstrument = (WindInstrument)instrument;
                    query = "INSERT INTO wind_instruments(note_instrument_id, material, type, not_enough_breath_chance) VALUES(?, ?, ?, ?)";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setInt(1, noteInstrumentId);
                    preparedStatement.setString(2, windInstrument.getMaterial());
                    preparedStatement.setString(3, windInstrument.getType());
                    preparedStatement.setFloat(4, windInstrument.getNotEnoughBreathChance());
                    preparedStatement.execute();
                }
            }
            else{
                NonNoteInstrument nonNoteInstrument = (NonNoteInstrument)instrument;
                StringBuilder sounds = new StringBuilder();
                for(int i = 0; i < nonNoteInstrument.getSounds().size(); i++){
                    sounds.append(nonNoteInstrument.getSounds().get(i).toString());
                    if(i != nonNoteInstrument.getSounds().size() - 1)
                        sounds.append("|");
                }
                query = "INSERT INTO non_note_instruments(music_instrument_id, sounds) VALUES(?, ?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, instrument.getId());
                preparedStatement.setString(2, sounds.toString());
                preparedStatement.execute();
                table.close();

            }
            preparedStatement.close();

        } catch(SQLException e){ e.printStackTrace(); }
    }
    public void deleteInstrument(MusicInstrument instrument){
        try {
            String query = "DELETE FROM music_instruments WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, instrument.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e){ e.printStackTrace(); }
    }

    public void saveMusician(Musician musician) {
        try {
            String query = "INSERT INTO musicians(name, age, joining_orchestra_year, instrument) VALUES(?, ?, ?, ?) RETURNING id";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, musician.getName());
            preparedStatement.setInt(2, musician.getAge());
            preparedStatement.setInt(3, musician.getJoiningOrchestraYear());
            preparedStatement.setInt(4, musician.getInstrument().getId());

            ResultSet table = preparedStatement.executeQuery();
            table.next();
            int musicianId = table.getInt("id");
            table.close();
            musician.setId(musicianId);
            preparedStatement.close();
        } catch(SQLException e) { e.printStackTrace(); }
    }
    public void deleteMusician(Musician musician){
        try {
            String query = "DELETE FROM musicians WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, musician.getId());
            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e){ e.printStackTrace(); }
    }
}