package chess.dao;

import chess.domain.piece.Side;

public interface ChessGameDao {
    Side selectTurn();

    void updateTurn(Side side);

    void delete();

    void insertTurn(Side turn);
}
