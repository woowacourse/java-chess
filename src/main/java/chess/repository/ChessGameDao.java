package chess.repository;

import chess.domain.chessGame.ChessGameState;

public interface ChessGameDao {
    ChessGameState findChessGame();

    void updateChessGame(ChessGameState gameState);

    void deleteAll();
}
