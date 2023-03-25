package chess.dao;

import chess.domain.ChessGame;

public interface ChessGameDao {
    void save(ChessGame chessGame);

    ChessGame select();

    void update(ChessGame chessGame);

    void delete();
}
