package domain;

import domain.type.Color;
import domain.type.Turn;
import java.util.HashMap;

public class ChessGame {

    private Board board;
    private Turn turn;

    public ChessGame() {
        this.turn = Turn.WHITE;
    }

    public void initialize() {
        this.board = new Board(new HashMap<>(), new ScoreCalculator());
        board.initialize();
    }

    public void move(final Location start, final Location end) {
        if (turn.equals(Turn.WHITE)) {
            board.moveWhite(start, end);
            turn = Turn.BLACK;
            return;
        }
        board.moveBlack(start, end);
        turn = Turn.WHITE;
    }

    public double calculateWhiteScore() {
        return board.calculateWhiteScore();
    }

    public double calculateBlackScore() {
        return board.calculateBlackScore();
    }

    public Color judgeResult() {
        final double whiteScore = board.calculateWhiteScore();
        final double blackScore = board.calculateBlackScore();
        if (whiteScore > blackScore) {
            return Color.WHITE;
        }
        if (whiteScore < blackScore) {
            return Color.BLACK;
        }
        return Color.NONE;
    }

    public Board getBoard() {
        return board;
    }
}
