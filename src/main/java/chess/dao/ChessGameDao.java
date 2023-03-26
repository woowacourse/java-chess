package chess.dao;

import chess.domain.ChessGame;

public interface ChessGameDao {

    long create();
    ChessGame findById(long id);
    void updateTurn(ChessGame chessGame);
}
