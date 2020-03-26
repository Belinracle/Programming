package Connection;

import Collection.MpaaRating;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class Validator implements Serializable {
    private String validate;
    private boolean needMovie;
    private boolean needPerson;
    public Validator(String validate){
        this.validate=validate;
    }
    public boolean validate(List<String> string){
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
    public void movie(){
        needMovie=true;
    };
    public boolean needMovie(){
        return needMovie;
    }
    public void person(){
        needPerson=true;
    }
    public boolean needPerson(){
        return needPerson;
    }
}
