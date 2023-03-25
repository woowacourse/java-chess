package chess.domain.chessgame;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.chessgame.state.GameState;
import chess.domain.chessgame.state.Ready;

public final class ChessGame {

    private GameState gameState = new Ready();

    public void start() {
        gameState = gameState.start();
    }

    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        gameState.move(from, to);
    }

    public void end() {
        gameState = gameState.end();
    }

    public boolean isNotEnd() {
        return gameState.isNotEnd();
    }

    public ChessBoard getChessBoard() {
        return gameState.getChessBoard();
    }
}
