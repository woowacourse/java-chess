package domain.game;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.Map;

public class ChessGame {

    private final Board board;
    private Side currentTurn;
    private GameState state;

    public ChessGame(Board board, Side currentTurn, GameState state) {
        this.board = board;
        this.currentTurn = currentTurn;
        this.state = state;
    }

    public GameState move(Position sourcePosition, Position targetPosition) {
        if (board.isMovable(sourcePosition, targetPosition, currentTurn)) {
            this.state = calculateNextGameState(targetPosition);
            board.move(sourcePosition, targetPosition);
            return this.state;
        }
        currentTurn = currentTurn.nextSide();
        return this.state;
    }

    public boolean isPlayable() {
        return state == GameState.RUN;
    }

    public void end() {
        state = GameState.END;
    }

    public Map<Side, Double> calculateScores() {
        return board.calculateScore();
    }

    public Side calculateWinner() {
        if (this.state == GameState.KING_DEAD) {
            return board.calculateKingExistSide(Side.WHITE);
        }
        return board.calculateWinner();
    }

    private GameState calculateNextGameState(Position targetPosition) {
        if (board.isKing(targetPosition)) {
            state = GameState.KING_DEAD;
            return GameState.KING_DEAD;
        }
        return GameState.RUN;
    }

    public Map<Position, Piece> getBoard() {
        return board.getChessBoard();
    }

    public Side getCurrentTurn() {
        return currentTurn;
    }

    public GameState getState() {
        return state;
    }
}
