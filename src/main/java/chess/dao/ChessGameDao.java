package chess.dao;

import chess.domain.piece.Side;
import java.sql.Connection;

public interface ChessGameDao {
    Connection getConnection();

    Side selectTurn();

    void updateTurn(Side side);

    void delete();

    void insertTurn(Side turn);
}
