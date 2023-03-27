package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;
import java.util.List;

public final class Result implements GameState {

    private static final boolean CAN_PRINT = true;
    private static final boolean CANNOT_PRINT = false;

    private boolean printable;

    Result() {
        this.printable = false;
    }

    @Override
    public GameState execute(final GameCommand gameCommand, final List<Position> ignored) {
        validateGameCommand(gameCommand);

        return handleGameCommand(gameCommand);
    }

    private GameState handleGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isEnd()) {
            return new End();
        }
        printable = CAN_PRINT;
        return this;
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart() || gameCommand.isLoad() || gameCommand.isMove()) {
            printable = CANNOT_PRINT;
            throw new IllegalArgumentException("게임이 종료된 상태입니다.");
        }
    }

    @Override
    public boolean isContinue() {
        return true;
    }

    @Override
    public boolean isPlay() {
        return false;
    }

    @Override
    public boolean isPrintable() {
        return printable;
    }
}
