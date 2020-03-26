package Factory;

import Collection.Movie;
import Collection.Person;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;

public interface Factory {
    Movie createMovie(IOinterface io, List<String> args) throws IOException, ClassNotFoundException;
    Person createPerson(IOinterface io) throws IOException, ClassNotFoundException;
    Movie updateMovie(String id, IOinterface io) throws IOException, ClassNotFoundException;
}
