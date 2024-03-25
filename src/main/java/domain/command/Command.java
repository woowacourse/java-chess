package domain.command;


import domain.board.Board;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import view.OutputView;

public abstract class Command {
    public abstract Command next(Command command);

    public boolean isRunning() {
        return true;
    }

    public boolean isMove() {
        return false;
    }
}
