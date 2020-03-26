package Factory;

import Collection.Movie;
import Collection.MovieGenre;
import Collection.MpaaRating;
import Collection.Person;
import IO.IOinterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class IOfactory implements Factory {
    @Override
    public Movie createMovie(IOinterface io, List<String> args) throws IOException {
        List<String> mov = new ArrayList<>();
//        mov.add(String.valueOf(IDFactory.createID()));
        if(args.size()>1){mov.add(args.get(1));}
        else mov.add(readString(io,"Введите название фильма",false));
        if(args.size()>2){
            Integer.parseInt(args.get(2));
            mov.add(args.get(2));
        }
        else mov.add(String.valueOf(readLong(io,"Введите количество оскаров в фильме(Integer)",0,Integer.MAX_VALUE)));
        mov.add(String.valueOf(readDouble(io,"Введите координату х(Float)",-928, Float.MAX_VALUE,7)));
        mov.add(String.valueOf(readDouble(io,"Введите координату y(Double)",-Double.MAX_VALUE, 982,50)));
        mov.add(String.valueOf(readGenre(io)));
        mov.add(String.valueOf(readRating(io)));
        mov.addAll(createPerson(io).getArgs());
        return new Movie(mov);
    }
    @Override
    public Movie updateMovie(String id, IOinterface io) throws IOException {
        List<String> mov = new ArrayList<>();
        mov.add(id);
        mov.add(readString(io,"Введите название фильма",false));
        mov.add(String.valueOf(readLong(io,"Введите количество оскаров в фильме(Integer)",0,Integer.MAX_VALUE)));
        mov.add(String.valueOf(readDouble(io,"Введите координату х(Float)",-928, Float.MAX_VALUE,7)));
        mov.add(String.valueOf(readDouble(io,"Введите координату y(Double)",-Double.MAX_VALUE, 982,50)));
        mov.add(String.valueOf(readGenre(io)));
        mov.add(String.valueOf(readRating(io)));
        mov.addAll(createPerson(io).getArgs());
        return new Movie(mov);
    }

    @Override
    public Person createPerson(IOinterface io) throws IOException {
        List<String> pers = new ArrayList<>();
        pers.add(readString(io,"Введите имя сценариста",false));
        pers.add(String.valueOf(readLong(io,"Введите его вес",0,Long.MAX_VALUE)));
        pers.add(readString(io,"Введите его Паспортный идентификатор",false));
        pers.add(String.valueOf(readDouble(io,"Введите координату Х локации сценариста",-Double.MAX_VALUE,Double.MAX_VALUE,50)));
        pers.add(String.valueOf(readLong(io,"Введите координату У",Long.MIN_VALUE,Long.MAX_VALUE)));
        pers.add(String.valueOf(readDouble(io,"Введите координату Z",Float.MIN_VALUE,Float.MAX_VALUE,7)));
        pers.add(readString(io,"Введите название этого места",true));
        return new Person(pers);
    }


    private  Long readLong(IOinterface io,String msg, Number min, Number max) throws IOException {
        if(io.isInteractive()) {
            io.writeln(msg);
            Long result = null;
            while (result == null) {
                try {
                    long l = Long.parseLong(io.readLine().trim());
                    if (checkLong(l, min, max)) {
                        result = l;
                    } else {
                        io.writeln("Неверный ввод. Повторите");
                    }
                } catch (NullPointerException | NumberFormatException e) {
                    io.writeln("Неверный ввод. Повторите");
                }
            }
            return result;
        }
        else return Long.parseLong(io.readLine());
    }
    public String readString(IOinterface io,String msg, boolean mayNull) throws IOException {
        if (io.isInteractive()) {
            String result = "";
            io.writeln(msg);
            while (!mayNull&&result.isEmpty()) {
                result = io.readLine().trim();
                if (result.isEmpty()) {
                    io.writeln("Введите непустую строку");
                }
            }
            if(mayNull) result = io.readLine().trim();
            return result;
        }
        else return io.readLine();
    }
    private  Double readDouble(IOinterface io,String msg, Number min, Number max, int digits) throws IOException {
        if (io.isInteractive()) {
            io.writeln(msg);
            Double result = null;
            while (result == null) {
                try {
                    String input = io.readLine().trim();
                    char[] digs = input.toCharArray();
                    int counter = 0;
                    for (char s: digs){
                        if (Character.isDigit(s)) counter++;
                    }
                    double d = Double.parseDouble(input);
                    if (checkDouble(d, min, max)&&counter<=digits) {
                        result = d;
                    } else {
                        io.writeln("Неверный ввод. Повторите");
                    }
                } catch (NullPointerException | NumberFormatException e) {
                    io.writeln("Неверный ввод. Повторите");
                }
            }
            return result;
        }
        else return Double.parseDouble(io.readLine());
    }
    private  MovieGenre readGenre(IOinterface io) throws IOException {
        if(io.isInteractive()) {
            io.writeln("Введите жанр из предложенных: Western, Comedy, Musical, Science_Fiction - или же пустую строку");
            while (true) {
                String genre = io.readLine().trim();
                if (genre.isEmpty()) {
                    return null;
                }
                switch (genre.toUpperCase()) {
                    case "WESTERN":
                        return MovieGenre.WESTERN;
                    case "COMEDY":
                        return MovieGenre.COMEDY;
                    case "MUSICAL":
                        return MovieGenre.MUSICAL;
                    case "SCIENCE_FICTION":
                        return MovieGenre.SCIENCE_FICTION;
                }
                io.writeln("Нет такого жанра");
            }
        }
        else return MovieGenre.valueOf(io.readLine().toUpperCase());
    }
    private  MpaaRating readRating(IOinterface io) throws IOException {
        if(io.isInteractive()) {
            io.writeln("Введите рейтинг из предложенных G, PG_13, R, NC_17");
            while (true) {
                String rating = io.readLine().trim();
                if (rating.isEmpty()) {
                    io.writeln("Введите жанр");
                    continue;
                }
                switch (rating.toUpperCase()) {
                    case "G":
                        return MpaaRating.G;
                    case "PG_13":
                        return MpaaRating.PG_13;
                    case "R":
                        return MpaaRating.R;
                    case "NC_17":
                        return MpaaRating.NC_17;
                }
                io.writeln("Нет такого рейтинга");
            }
        }
        else return MpaaRating.valueOf(io.readLine().toUpperCase());
    }


    private boolean checkLong(long s, Number min, Number max) {
        return s>min.longValue()&&s<max.longValue();
    }

    private boolean checkDouble(double s, Number min, Number max) {
        return s>min.doubleValue()&&s<max.doubleValue();
    }
}
