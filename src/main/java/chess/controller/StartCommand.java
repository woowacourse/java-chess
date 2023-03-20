package chess.controller;

public class StartCommand extends Command {

    public StartCommand() {
        super(args -> false);
//        Action action = () ->
//        super();
    }

    @Override
    public boolean execute() {
        return true;
    }
}
