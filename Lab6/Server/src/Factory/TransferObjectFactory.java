package Factory;

import Collection.Movie;
import Collection.Person;
import Connection.TransferObject;
import IO.IOinterface;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Фабрика нужных объектов настроенная на извлечение готовых объектов из IO
 */
public class TransferObjectFactory implements Factory {
    @Override
    public Movie createMovie(IOinterface io, List<String> args) throws IOException, ClassNotFoundException {
        TransferObject trans = (TransferObject) io.readObj();
        Movie mov =(Movie)trans.getObject();
        mov.setID(IDFactory.createID());
        mov.setCreatDate(LocalDate.now());
        return mov;
    }

    @Override
    public Person createPerson(IOinterface io) throws IOException, ClassNotFoundException {
        TransferObject trans = (TransferObject) io.readObj();
        Person person =(Person)trans.getObject();
        return person;
    }

    @Override
    public Movie updateMovie(String id, IOinterface io) throws IOException, ClassNotFoundException {
        TransferObject trans = (TransferObject) io.readObj();
        Movie mov =(Movie)trans.getObject();
        return mov;
    }
}
