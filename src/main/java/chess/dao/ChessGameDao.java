package chess.dao;

import chess.game.ChessGame;

public interface ChessGameDao {

    void save(ChessGame chessGame);

    ChessGame select();

    void update(ChessGame chessGame);
}
