package Collection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coordinates implements Serializable {
    private Float x; //Значение поля должно быть больше -928, Поле не может быть null
    private double y; //Максимальное значение поля: 982
    private List<String> args;
    Coordinates(List<String> args ){
        this.args=args;
        x = Float.valueOf(args.get(0));
        y = Double.parseDouble(args.get(1));
    }
    List<String> getArgs(){
        return args;
    }

    @Override
    public String toString() {
        return  "   X "+ x+"\n"+
                "   Y "+ y+"\n";
    }
}
