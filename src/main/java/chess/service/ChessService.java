package chess.service;

import chess.domain.ChessGame;

public interface ChessService {

    void create();

    ChessGame getChessGame();

    void move(ChessGame chessGame, String source, String target);
}
