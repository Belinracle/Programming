package Collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Поле не может быть null
    private Location location; //Поле не может быть null
    private List<String> args;
    public Person(List<String> args){
        this.args=args;
        name = args.get(0);
        weight = Long.parseLong(args.get(1));
        passportID = args.get(2);
        location = new Location(args.subList(3,7));
    }
    public List<String> getArgs(){
        return args;
    }

    @Override
    public String toString() {
        return  "   Name "+name+"\n"+
                "   Weight" + weight+"\n"+
                "   PassportId " + passportID+"\n"+
                "   Location \n"+location.toString();
    }

    boolean equals(Person  anPers) {
        return args.containsAll(anPers.getArgs());
    }
}
