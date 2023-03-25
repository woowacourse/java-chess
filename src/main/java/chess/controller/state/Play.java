package chess.controller.state;

import chess.controller.GameCommand;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import java.util.List;

public class Play implements GameState {

    private static final boolean UN_PRINTABLE = false;
    private static final int SOURCE_INDEX = 0;
    private static final int TARGET_INDEX = 1;
    private final ChessGame chessGame;

    public Play(final ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public GameState execute(final GameCommand gameCommand, final List<Position> movePositions) {
        validateGameCommand(gameCommand);

        try {
            return handleGameCommand(gameCommand, movePositions);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("존재하지 않는 체스 칸을 지정했습니다.", e);
        }
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
        }
        if (gameCommand.isStatus()) {
            throw new IllegalArgumentException("게임이 진행중입니다.");
        }
    }

    private GameState handleGameCommand(final GameCommand gameCommand, final List<Position> movePositions) {
        if (gameCommand.isMove()) {
            return handleMove(movePositions);
        }
        return new End();
    }

    private GameState handleMove(final List<Position> movePositions) {
        chessGame.move(movePositions.get(SOURCE_INDEX), movePositions.get(TARGET_INDEX));

        if (chessGame.isGameOnGoing()) {
            return this;
        }
        return new Result(UN_PRINTABLE);
    }

    @Override
    public boolean isContinue() {
        return true;
    }

    @Override
    public boolean isPlay() {
        return true;
    }

    @Override
    public boolean isPrintable() {
        return true;
    }
}
