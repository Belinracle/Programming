package Collection;

import IO.IOinterface;
import Parsers.Parser;

import java.io.IOException;

/**
 * Интерфейс для работы с разными коллекциями
 */
public interface CollectionInterface {
    String info();
    String show();
    void add(Movie movie);
    void update(Integer id, Movie mov, IOinterface iOinterface) throws IOException;
    void remove_by_id(Integer id, IOinterface iOinterface) throws IOException;
    void clear();
    void remove_first(IOinterface iOinterface) throws IOException;
    void add_if_max(Movie movie, IOinterface io) throws IOException;
    void add_if_min(Movie movie, IOinterface io) throws IOException;
    void remove_all_by_screenwriter(Person pers, IOinterface io);
    long count_by_mpaa_rating(MpaaRating rating);
    String print_ascending();
    void save(Parser pars, IOinterface iOFile);
    void load(Parser pars, IOinterface ioFile) throws IOException;
}
