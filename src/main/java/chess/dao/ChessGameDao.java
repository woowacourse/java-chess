package chess.dao;

import chess.domain.game.ChessGame;

public interface ChessGameDao {
    void save(ChessGame chessGame);

    ChessGame findChessGame();

    void update(ChessGame chessGame);

    void init(ChessGame chessGame);
}
