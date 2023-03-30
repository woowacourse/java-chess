package chess.repository;

import chess.domain.chessGame.ChessGame;

public interface ChessGameDao {
    ChessGame findChessGame();

    void updateChessGame(ChessGame gameState);

    void deleteAll();
}
