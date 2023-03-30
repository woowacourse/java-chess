package chess.dao;

import chess.domain.ChessGame;
import java.util.List;

public interface ChessGameDao {

    long create();
    ChessGame findById(long id);
    void updateTurn(ChessGame chessGame);
    List<Integer> findAll();
}
