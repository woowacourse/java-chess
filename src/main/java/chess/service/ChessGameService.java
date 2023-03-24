package chess.service;

import chess.domain.TeamColor;
import chess.domain.position.Position;
import chess.dao.ChessGameDao;
import chess.dao.PieceDao;

public class ChessGameService implements GameService {

    private final ChessGameDao gameDao;
    private final PieceDao pieceDao;

    public ChessGameService(final ChessGameDao gameDao, final PieceDao pieceDao) {
        this.gameDao = gameDao;
        this.pieceDao = pieceDao;
    }

    public void updateMovement(final Position source,
        final Position dest,
        final long gameId,
        final boolean isDestDeleteNeeded) {
        if (isDestDeleteNeeded) {
            pieceDao.deleteByPositionAndGameId(dest, gameId);
        }
        pieceDao.updatePositionByPositionAndGameId(source, gameId, dest);
    }

    public void updateGameStatusEnd(final long gameId) {
        gameDao.updateStatus(gameId, true);
    }

    public void updateGameTurn(final long gameId, final TeamColor teamColor) {
        gameDao.updateTurn(gameId, teamColor);
    }

}
