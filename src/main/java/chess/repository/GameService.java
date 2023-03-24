package chess.repository;

import chess.domain.TeamColor;
import chess.domain.position.Position;

public class GameService {

    private final ChessGameDao gameDao;
    private final PieceDao pieceDao;

    public GameService(final ChessGameDao gameDao, final PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public void updateMovement(final Position source, final Position dest, final long gameId) {
        pieceDao.deleteByPositionAndGameId(dest, gameId);
        pieceDao.updatePositionByPositionAndGameId(source, gameId, dest);
    }

    public void updateGameStatusEnd(final long gameId) {
        gameDao.updateStatus(gameId, true);
    }

    public void updateGameTurn(final long gameId, final TeamColor teamColor) {
        gameDao.updateTurn(gameId, teamColor);
    }

}
