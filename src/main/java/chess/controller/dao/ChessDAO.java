package chess.controller.dao;

import chess.domain.ChessGame;

public interface ChessDAO {

    void saveGame(ChessGame chessGame);

    ChessGame select();

    void update();

    void delete();
}
