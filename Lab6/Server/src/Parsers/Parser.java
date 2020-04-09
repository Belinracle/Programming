package Parsers;

import Collection.Movie;

import java.util.Collection;

/**
 * интерфейс для Парсинга
 */
public interface Parser {
    String ser(Movie movie);
    Collection<Movie> deSer(String str);
}
