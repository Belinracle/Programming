package Collection;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private Double x; //Поле не может быть null
    private long y;
    private float z;
    private String name; //Поле может быть null
    private List<String> args;
    Location(List<String> args){
        this.args=args;
        x = Double.parseDouble(args.get(0));
        y = Long.parseLong(args.get(1));
        z = Float.parseFloat(args.get(2));
        name = args.get(3);
    }
    List<String> getArgs(){
        return args;
    }

    @Override
    public String toString() {
        return  "       X "+x+"\n"+
                "       Y "+y+"\n"+
                "       Z "+z+"\n"+
                "       Name "+name+"\n";
    }
}
