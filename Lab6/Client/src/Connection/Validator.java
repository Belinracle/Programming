package Connection;

import Collection.MpaaRating;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Validator {
    private String validate;
    private boolean needMovie;
    private boolean needPerson;
    public Validator(String validate){
        this.validate=validate;
    }
    boolean validate(List<String> string){
        if (validate.equals("Int")){try{ Integer.parseInt(string.get(1));return true;}catch (NumberFormatException|IndexOutOfBoundsException e){return false;}}
        if (validate.equals("Path")){try{ new FileReader(string.get(1)).close();return true;}catch (IndexOutOfBoundsException | IOException e){return false;}}
        if (validate.equals("Rating")){try{ MpaaRating.valueOf(string.get(1));return true;}catch (IndexOutOfBoundsException |IllegalArgumentException e){return false;}}
        if (validate.equals("Any")){return true;}
        if (validate.equals("String,Integer")){
            try {
                Integer.parseInt(string.get(2));
                return true;
            }catch (NumberFormatException e){
                return false;
            }catch (IndexOutOfBoundsException e){
                return true;
            }
        }
        return true;
    }
    void movie(){
        needMovie=true;
    };
    boolean needMovie(){
        return needMovie;
    }
    void person(){
        needPerson=true;
    }
    boolean needPerson(){
        return needPerson;
    }
}

