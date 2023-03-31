package database;

import chess.dao.BoardDao;
import chess.domain.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public class FakeBoardJdbcDao implements BoardDao {
    @Override
    public void create(final Map<Position, Piece> board, final int gameId) {

    }

    @Override
    public Map<Position, Piece> findByLastGameBoard(final int gameId) {
        return null;
    }

    @Override
    public void deleteAll(final int gameId) {

    }
}
