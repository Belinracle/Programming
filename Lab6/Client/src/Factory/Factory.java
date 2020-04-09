package Factory;

import Collection.Movie;
import Collection.Person;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;

/**
 * Интерфейс для создания необходимых объектов
 */
public interface Factory {
    Movie createMovie(IOinterface io, List<String> args) throws IOException;
    Person createPerson(IOinterface io) throws IOException;
    Movie updateMovie(String id, IOinterface io) throws IOException;
}
