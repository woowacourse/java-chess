package chess.dao;

import chess.domain.game.ChessGame;

public interface ChessDao {

    void save(final ChessGame chessGame);

    ChessGame find();

    void update(final ChessGame chessGame);
}
