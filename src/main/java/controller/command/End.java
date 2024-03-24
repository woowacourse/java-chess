package controller.command;

import domain.board.Board;
import java.util.function.Supplier;

public final class End extends Command {
    @Override
    public Command execute(final Board board, final Supplier<String> input) {
        return this;
    }

    @Override
    protected Command next(final Supplier<String> input) {
        return this;
    }


    @Override
    public boolean isNotEnd() {
        return false;
    }
}
