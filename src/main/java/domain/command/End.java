package domain.command;

import domain.board.Board;
import java.util.function.Supplier;

public final class End extends Command {
    private static final Command instance = new End();

    private End() {
    }

    @Override
    public Command next(final Command command) {
        throw new UnsupportedOperationException("끝난 상태에서 다음 상태로 넘어갈 수 없습니다");
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    public static Command getInstance() {
        return instance;
    }
}
