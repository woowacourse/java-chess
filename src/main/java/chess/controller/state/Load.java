package chess.controller.state;

import chess.controller.GameCommand;
import chess.dao.ChessMovementDao;
import chess.dao.Movement;
import chess.model.game.ChessGame;
import chess.model.position.Position;
import chess.model.position.PositionConverter;
import java.util.List;

public final class Load extends AbstractInitialGameState {

    private final ChessGame chessGame;
    private final ChessMovementDao chessMovementDao;

    Load(final ChessGame chessGame, final ChessMovementDao chessMovementDao) {
        this.chessGame = chessGame;
        this.chessMovementDao = chessMovementDao;
    }

    @Override
    public GameState execute(final GameCommand ignoredGameCommand, final List<Position> ignoredPositions) {
        chessGame.initialChessGame();
        loadChessGame();

        return new Play(chessGame, chessMovementDao);
    }

    private void loadChessGame() {
        final List<Movement> movements = chessMovementDao.findAll();

        for (Movement movement : movements) {
            final Position source = PositionConverter.convert(movement.getSource());
            final Position target = PositionConverter.convert(movement.getTarget());

            chessGame.move(source, target);
        }
    }
}
