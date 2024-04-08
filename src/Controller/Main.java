package Controller;

import Model.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //Controller controller = new Controller();
        //controller.Start();

        DBWorker dbWorker = new DBWorker();
        dbWorker.connect();

        System.out.println("========= INSTRUMENTS =========");
        ArrayList<MusicInstrument> instruments = dbWorker.getAllInstruments();

        for(MusicInstrument instrument : instruments){
            System.out.println(instrument.getId());
            System.out.println(instrument.getName());
            if(instrument instanceof NonNoteInstrument)
                ((NonNoteInstrument)instrument).getSounds().forEach(item -> System.out.println("\t" + item.toString()) );
            else
                System.out.println("\t" + ((NoteInstrument)instrument).getMinNote() + " - " + ((NoteInstrument)instrument).getMaxNote());
        }
        System.out.println();

        System.out.println("========= MUSICIANS =========");
        ArrayList<Musician> musicians = dbWorker.getMusicians(instruments);
        for(Musician musician : dbWorker.getMusicians(instruments)) {
            System.out.println(musician.getId());
            System.out.println(musician.getName());
            System.out.println("\t" + musician.getAge());
            System.out.println("\t" + musician.getJoiningOrchestraYear());
            System.out.println("\t" + musician.getInstrument().getName());
        }

        /*
        todo:
            - Добавлять/удалять инструменты/музыкантов
            - в прошлой UML в infoPanel методы и поля поменять местами
         */

        dbWorker.closeConnection();
    }
}