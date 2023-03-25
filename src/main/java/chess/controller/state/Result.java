package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.position.Position;
import java.util.List;

public class Result implements GameState {

    private static final boolean PRINTABLE = true;

    private final boolean printable;

    Result(final boolean printable) {
        this.printable = printable;
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
        return new Result(PRINTABLE);
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            throw new IllegalArgumentException("게임을 시작할 수 없습니다.");
        }
        if (gameCommand.isMove()) {
            throw new IllegalArgumentException("게임을 플레이할 수 없습니다.");
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
