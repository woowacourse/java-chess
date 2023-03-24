package chess.controller.dao;

import chess.domain.ChessGame;

public interface ChessDAO {

    void insert(ChessGame chessGame);

    ChessGame select();

    void update();
}
