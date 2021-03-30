package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final Board board;
    private final Turn turn;

    public Game(Board board) {
        this.board = board;
        turn = new Turn();
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
        Map<Color, Double> scores = new HashMap<>();
        scores.put(Color.BLACK, board.score(Color.BLACK));
        scores.put(Color.WHITE, board.score(Color.WHITE));
        return scores;
    }

    public Map<Position, Piece> allBoard() {
        return board.allPieces();
    }
}
