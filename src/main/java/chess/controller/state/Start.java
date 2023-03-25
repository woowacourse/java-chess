package chess.controller.state;

import chess.controller.GameCommand;
import chess.dao.ChessMovementDao;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import java.util.List;

public final class Start extends AbstractInitialGameState {

    private final ChessGame chessGame;
    private final ChessMovementDao chessMovementDao;

    Start(final ChessGame chessGame, final ChessMovementDao chessMovementDao) {
        this.chessGame = chessGame;
        this.chessMovementDao = chessMovementDao;
    }

    @Override
    public GameState execute(final GameCommand ignoredGameCommand, final List<Position> ignored) {
        chessMovementDao.delete();
        chessGame.initialChessGame();

        return new Play(chessGame, chessMovementDao);
    }
}
