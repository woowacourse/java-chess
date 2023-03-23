package domain.game;

import domain.piece.Position;
import domain.piece.Side;

import java.util.Map;

public class ChessGame {

    private final Board board;
    private Side currentTurn = Side.WHITE;
    private GameState state;

    public ChessGame(Board board) {
        this.board = board;
        this.state = GameState.RUN;
    }

    public void move(Position sourcePosition, Position targetPosition) {
        if (board.isMovable(sourcePosition, targetPosition, currentTurn)) {
            this.state = calculateNextGameState(targetPosition);
            board.move(sourcePosition, targetPosition);
        }
        currentTurn = currentTurn.nextSide();
    }

    public boolean isExitGame() {
        return state == GameState.END;
    }

    public Map<Side, Double> calculateScores() {
        return board.calculateScore();
    }

    public Side calculateWinner() {
        return board.calculateWinner();
    }

    private GameState calculateNextGameState(Position targetPosition) {
        if (board.isKing(targetPosition)) {
            return GameState.END;
        }
        return GameState.RUN;
    }
}
