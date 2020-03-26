package Parsers;

import Collection.Movie;

import java.util.Collection;

public interface Parser {
    String ser(Movie movie);
    Collection<Movie> deSer(String str);
}
