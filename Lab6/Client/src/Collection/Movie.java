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
        List<String> coorList = new ArrayList<>();
        coorList.add(args.get(3));
        coorList.add(args.get(4));
        coordinates = new Coordinates(coorList);
        creationDate = LocalDate.now();
        oscarsCount = Integer.parseInt(args.get(2));
        if (args.get(5).equals("null")){
            genre = null;}
        else {genre = MovieGenre.valueOf(args.get(5));}
        mpaaRating = MpaaRating.valueOf(args.get(6));
        List<String> PersList = new ArrayList<>();
        PersList.add(args.get(7));
        PersList.add(args.get(8));
        PersList.add(args.get(9));
        PersList.add(args.get(10));
        PersList.add(args.get(11));
        PersList.add(args.get(12));
        PersList.add(args.get(13));
        screenwriter = new Person(PersList);
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
        args.remove(0);
        args.add(0,String.valueOf(id));
    }
}