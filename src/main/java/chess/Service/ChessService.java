package chess.Service;

import chess.database.DBChessBoardDao;
import chess.domain.ChessGame;
import chess.domain.position.Position;

import java.util.List;

public class ChessService {

    public static final int POSITION_SET_INDEX = 2;

    private final DBChessBoardDao dbChessBoardDao;

    public ChessService(DBChessBoardDao dbChessBoardDao) {
        this.dbChessBoardDao = dbChessBoardDao;
    }

    public void saveGame(final Position fromPosition, final Position toPosition) {
        dbChessBoardDao.save(fromPosition, toPosition);
    }

    public ChessGame checkNotation(ChessGame game) {
        List<Position> gameNotation = dbChessBoardDao.select();
        if (gameNotation != null) {
            for (int i = 0; i < gameNotation.size(); i += POSITION_SET_INDEX) {
                game.move(gameNotation.get(i), gameNotation.get(i + 1));
            }
        }
        return game;
    }

    public void deleteData(boolean isKingLive) {
        if (!isKingLive) {
            dbChessBoardDao.delete();
        }
    }
}
