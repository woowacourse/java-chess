package chess.dao;

import chess.domain.game.ChessGame;

public interface ChessGameDao {

    void save(ChessGame chessGame);

    ChessGame select();

    void update(ChessGame chessGame);
}
