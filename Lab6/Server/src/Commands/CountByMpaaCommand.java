package Commands;

import Collection.CollectionInterface;
import Collection.MpaaRating;
import IO.IOinterface;

import java.io.IOException;
import java.util.List;
import Connection.*;
public class CountByMpaaCommand implements Command {
    private CollectionInterface ci;
    private Validator val = new Validator("Rating");
    private String name;
    public CountByMpaaCommand(CollectionInterface ci, CommandFetch cf){
        cf.addCommand("count_by_mpaa_rating",this);
        this.ci=ci;
        name="count_by_mpaa_rating";
    }
    @Override
    public void execute(IOinterface io, List<String> args) throws IOException {
        if(val.validate(args)) io.writeln("Количество фильмов с таким рейтингом: "+ci.count_by_mpaa_rating(MpaaRating.valueOf(args.get(1))));
        else io.writeln("Неверные аргументы команды");
    }

    @Override
    public Validator getValidator() {
        return val;
    }

    @Override
    public String getName() {
        return name;
    }
}
