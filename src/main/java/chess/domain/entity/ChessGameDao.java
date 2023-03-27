package chess.domain.entity;

import chess.service.ChessGame;

public interface ChessGameDao {
    void create(ChessGame chessGame);
    ChessGame select();
    void update(ChessGame chessGame);
}
