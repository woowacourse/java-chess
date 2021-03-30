package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class Game {
    private final Board board;
    private final Calculator calculator = new Calculator();
    private final Turn turn = new Turn();

    public Game(Board board) {
        this.board = board;
    }

    public void move(Position from, Position to) {
        board.action(turn.color(), from, to);
        turn.next();
    }

    public boolean isNotEnd() {
        return board.isNotEnd();
    }

    public Color currentPlayer() {
        return turn.color();
    }

    public Map<Color, Double> score() {
        return calculator.scoreByColor(board.allPieces());
    }

    public Map<Position, Piece> allBoard() {
        return board.allPieces();
    }
}
