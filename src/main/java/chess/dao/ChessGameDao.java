package chess.dao;

import chess.domain.Board;
import chess.game.state.GameState;

public interface ChessGameDao {
    Board findBoard();

    GameState findGameState();

    void saveChessGame(Board board, GameState gameState);
}
