package Collection;

import IO.IOinterface;
import Parsers.Parser;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DeQueCollection implements CollectionInterface {
    private ArrayDeque<Movie> coll;
    private ArrayDeque<Movie> buf;
    public DeQueCollection(){
        coll = new ArrayDeque<>();
        buf=new ArrayDeque<>();
    }
    @Override
    public String info() {
        return "Тип коллекции "+coll.getClass()+ "Количество элементов "+coll.size();
    }

    @Override
    public String show() {
        StringBuilder sb = new StringBuilder();
        coll.forEach(x->sb.append(x.toString()).append("\n"));
        return sb.toString();
    }

    @Override
    public void add(Movie movie) {
        while (coll.size()!=0&&coll.getLast().getArgs().get(1).compareToIgnoreCase(movie.getArgs().get(1))>0) {
            buf.addFirst(coll.removeLast());
        }
        coll.addLast(movie);
        coll.addAll(buf);
        buf.clear();
    }

    @Override
    public void update(Integer id, Movie mov, IOinterface io) throws IOException {
        try {
            while (Long.parseLong(coll.getLast().getArgs().get(0)) != id) {
                buf.addFirst(coll.removeLast());
            }
            coll.removeLast();
            coll.addLast(mov);
        }catch(NoSuchElementException e){
            io.writeln("В коллекции нет Фильма с таким ID");
            coll.addAll(buf);
            buf.clear();
        }
    }

    @Override
    public void remove_by_id(Integer id, IOinterface iOinterface) throws IOException {
        try {
            while (Long.parseLong(coll.getLast().getArgs().get(0)) != id) {
                buf.addFirst(coll.removeLast());
            }
            coll.removeLast();
            coll.addAll(buf);
            buf.clear();
        }catch(NoSuchElementException |NullPointerException e){
            iOinterface.writeln("Элемента с таким ID нет в коллекции");
        }
    }

    @Override
    public void clear() {
        coll.clear();
    }

    @Override
    public void remove_first(IOinterface iOinterface) throws IOException {
        try {
            coll.removeFirst();
            iOinterface.writeln("Удален первый элемент");
        }catch(NoSuchElementException e){
            iOinterface.writeln("Коллекция пуста");
        }
    }

    @Override
    public void add_if_max(Movie movie, IOinterface io) throws IOException {
        try {
            if (coll.getLast().getArgs().get(1).compareToIgnoreCase(movie.getArgs().get(1)) < 0) {
                coll.addLast(movie);
                io.writeln("Элемент успешно добавлен");
            }
        }catch (NoSuchElementException | IOException e){
            io.writeln("Коллекция пуста, добавлю элемент");
            coll.addLast(movie);
        }
    }

    @Override
    public void add_if_min(Movie movie, IOinterface io) throws IOException {
        try {
            if (coll.getFirst().getArgs().get(1).compareToIgnoreCase(movie.getArgs().get(1)) > 0) {
                coll.addLast(movie);
                io.writeln("Элемент успешно добавлен");
            }
        }catch (NoSuchElementException e){
            io.writeln("Коллекция пуста, добавлю элемент");
            coll.addFirst(movie);
        }
    }

    @Override
    public void remove_all_by_screenwriter(Person pers, IOinterface io) {
        try {
            List<Integer> toDel = coll.stream().filter(x->x.getSc().equals(pers)).map(Movie::getId).collect(Collectors.toList());
            toDel.forEach(x-> {
                try {
                    remove_by_id(x,io);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            io.writeln("Команда выполнена");
        }catch (NullPointerException |IOException e){
            System.out.println("плохо");
        }
    }

    @Override
    public long count_by_mpaa_rating(MpaaRating rating) {
        return coll.stream().filter(x->x.getArgs().get(5).equals(rating.toString())).count();
    }

    @Override
    public String print_ascending() {
        StringBuilder sb = new StringBuilder();
        coll.forEach(x->sb.append(x.getArgs().get(1)).append("\n"));
        return sb.toString();
    }

    @Override
    public void save(Parser pars, IOinterface iOinterface) {
        coll.forEach(x-> {
            try {
                iOinterface.write(pars.ser(x));

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void load(Parser pars, IOinterface ioFile) throws IOException {
        try {
            String str = "";
            while (ioFile.hasNextLine()) {
                str = str + ioFile.readLine() + "\n";
            }
            coll.addAll(pars.deSer(str));
        }catch (NullPointerException e ){
            ioFile.writeln("В файле нет объектов");
        }
    }
}
