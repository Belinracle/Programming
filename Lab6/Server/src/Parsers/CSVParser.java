package Parsers;

import Collection.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * реализация Парсера на CSV формат
 */
public class CSVParser implements Parser {

    @Override
    public String ser(Movie movie) {
        List<String> args = movie.getArgs();
        StringBuilder sb = new StringBuilder();
        for(String str:args){
            sb.append(str).append(",");
        }
        sb.deleteCharAt(sb.length()-1).append("\n");
        return sb.toString();
    }

    @Override
    public Collection<Movie> deSer(String str) {
        try {
            List<Movie> movies = new ArrayList<>();
            String[] linees = str.split("\n");
            for (String line : linees) {
                movies.add(new Movie(Arrays.asList(line.split(","))));
            }
            return movies;
        }catch(NumberFormatException e){
            System.out.println(e.getCause());
        }
        return null;
    }
}
