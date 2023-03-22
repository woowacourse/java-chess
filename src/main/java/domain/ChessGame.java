package domain;

import domain.piece.Piece;
import domain.type.Color;
import domain.type.PieceType;
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

    public Color move(final Location start, final Location end) {
        if (turn.equals(Turn.WHITE)) {
            return convert(board.moveWhite(start, end));
        }
        return convert(board.moveBlack(start, end));
    }

    private Color convert(final Piece piece) {
        if (piece.isSameType(PieceType.KING)) {
            return piece.getColor().reverse();
        }
        turn = turn.convert();
        return Color.NONE;
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
