package chess.dao;

import chess.domain.game.ChessGame;

public interface ChessDao {

    void save(final ChessGame chessGame);

    ChessGame loadGame();

    boolean hasHistory();

    void delete();
}
