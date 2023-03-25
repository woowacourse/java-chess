package chess.controller.state;

import chess.controller.GameCommand;
import chess.dao.ChessMovementDao;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import java.util.Collections;
import java.util.List;

public final class Ready implements GameState {

    private final ChessGame chessGame;
    private final ChessMovementDao chessMovementDao;

    public Ready(final ChessGame chessGame, final ChessMovementDao chessMovementDao) {
        this.chessGame = chessGame;
        this.chessMovementDao = chessMovementDao;
    }

    @Override
    public GameState execute(final GameCommand gameCommand, final List<Position> ignored) {
        validateGameCommand(gameCommand);

        return handleGameCommand(gameCommand);
    }

    private void validateGameCommand(final GameCommand gameCommand) {
        if (isInvalidCommand(gameCommand)) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private boolean isInvalidCommand(final GameCommand gameCommand) {
        return gameCommand.isMove() || gameCommand.isStatus();
    }

    private GameState handleGameCommand(final GameCommand gameCommand) {
        if (gameCommand.isStart()) {
            return new Start(chessGame, chessMovementDao).execute(gameCommand, Collections.emptyList());
        }
        if (gameCommand.isLoad()) {
            return new Load(chessGame, chessMovementDao).execute(gameCommand, Collections.emptyList());
        }
        return new End();
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
        return false;
    }
}
