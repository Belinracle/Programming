package Collection;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int oscarsCount; //Значение поля должно быть больше 0
    private MovieGenre genre; //Поле может быть null
    private MpaaRating mpaaRating; //Поле не может быть null
    private Person screenwriter;
    private List<String> args;
    public Movie(List<String> args){
        this.args=args;
        id = Integer.parseInt(args.get(0));
        name = args.get(1);

        coordinates = new Coordinates(args.subList(3,5));
        creationDate = LocalDate.now();
        oscarsCount = Integer.parseInt(args.get(2));
        if (args.get(5).equals("null")){
            genre = null;}
        else {genre = MovieGenre.valueOf(args.get(5));}
        mpaaRating = MpaaRating.valueOf(args.get(6));
        screenwriter = new Person(args.subList(7,14));
    }
    public List<String> getArgs(){
        return args;
    }

    @Override
    public String toString() {
        return "ID "+id+"\n"+
                "Name "+name+"\n" +
                "Coordinates \n"+ coordinates+
                "Creation Date +" + creationDate+"\n"+
                "Oscars "+ oscarsCount+"\n"+
                "Movie Genre "+ genre+"\n"+
                "Mpaa Rating "+ mpaaRating+"\n"+
                "Screenwriter \n" + screenwriter+"\n";
    }
    Person getSc(){
        return screenwriter;
    }
    Integer getId(){return id;}
    public void setID(Integer id){
        this.id=id;
    }
}
