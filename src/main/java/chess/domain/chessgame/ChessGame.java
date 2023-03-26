package chess.domain.chessgame;

import chess.dao.RoomName;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.SquareCoordinate;
import chess.domain.chessgame.state.GameState;
import chess.domain.chessgame.state.Ready;
import chess.domain.winningstatus.WinningStatus;

public final class ChessGame {

    private GameState gameState = new Ready();

    public void start() {
        gameState = gameState.start();
    }

    public void move(final SquareCoordinate from, final SquareCoordinate to) {
        gameState.move(from, to);
        if (gameState.isKingDead()) {
            gameState = gameState.close();
        }
    }

    public WinningStatus status() {
        return gameState.status();
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public void save(RoomName roomName) {
        gameState = gameState.save(roomName);
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
