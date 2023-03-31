package chess.domain.game;

import chess.domain.game.state.GameState;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Terminated;
import chess.domain.piece.Piece;

import java.util.Map;

public class ChessGame {

    private static final long RUNNING_KING_NUMBER = 2;

    private final Board board;
    private GameState gameState;

    public ChessGame(Board board) {
        this.board = board;
        this.gameState = Ready.getState();
    }

    public void start() {
        gameState = gameState.start();
    }

    public boolean isNotTerminated() {
        return gameState.isNotTerminated();
    }

    public void progress(Position source, Position target) {
        gameState.progress(source, target, board);
        if (shouldTerminateGame(board.countKingNumber())) {
            gameState = Terminated.getState();
        }
    }

    public double calculateBlackScore() {
        return gameState.calculateBlackScore(board);
    }

    public double calculateWhiteScore() {
        return gameState.calculateWhiteScore(board);
    }

    public void terminateGame() {
        gameState = Terminated.getState();
    }

    private boolean shouldTerminateGame(long kingNumber) {
        return RUNNING_KING_NUMBER != kingNumber;
    }

    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }
}
