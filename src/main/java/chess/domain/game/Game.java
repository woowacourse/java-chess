package chess.domain.game;

import chess.domain.location.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.state.BlackTurn;
import chess.domain.state.State;

import java.util.Map;

public class Game {
    private final Board board;
    private final Calculator calculator = new Calculator();
    private State state;

    public Game(Board board) {
        this.board = board;
        state = BlackTurn.getInstance();
    }

    public void move(Position from, Position to) {
        board.action(state.color(), from, to);
        if (board.isKingDead()) {
            state = state.end();
            return;
        }
        state = state.opposite();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public boolean isNotEnd() {
        return !isEnd();
    }

    public Color currentPlayer() {
        return state.color();
    }

    public Map<Color, Double> score() {
        return calculator.scoreByColor(board.allPieces());
    }

    public double score(Color color) {
        return calculator.score(color, board.allPieces());
    }

    public Map<Position, Piece> allBoard() {
        return board.allPieces();
    }
}
