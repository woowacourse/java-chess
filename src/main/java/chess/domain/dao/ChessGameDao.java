package chess.domain.dao;

import chess.domain.chessgame.ChessGame;

public interface ChessGameDao {

    void save(ChessGame chessGame);

    ChessGame select();

    void update(ChessGame chessGame);
}
