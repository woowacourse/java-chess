package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Xpoint;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import java.util.Arrays;

public class ScoreCalculator {

    public static final double HALF_SCORE = 0.5;

    private final Board board;

    public ScoreCalculator(Board board) {
        this.board = board;
    }

    public double totalWhiteScore() {
        return scoreExceptPawns(Color.WHITE) + scoreOfPawns(Color.WHITE);
    }

    public double totalBlackScore() {
        return scoreExceptPawns(Color.BLACK) + scoreOfPawns(Color.BLACK);
    }

    private double scoreOfPawns(final Color color) {
        return Arrays.stream(Xpoint.values())
            .mapToDouble(xPoint -> verticalPawnScore(xPoint, color))
            .sum();
    }

    private double verticalPawnScore(final Xpoint xpoint, final Color color) {
        long countOfPawnsInVertical = countOfPawnsInVertical(xpoint, color);

        if (countOfPawnsInVertical > 1) {
            return countOfPawnsInVertical * HALF_SCORE;
        }

        return countOfPawnsInVertical;
    }

    private long countOfPawnsInVertical(final Xpoint xpoint, final Color color) {
        return this.board.piecesByXpoint(xpoint)
            .stream()
            .filter(Piece::isPawn)
            .filter(piece -> piece.isSameColor(color))
            .count();
    }

    private double scoreExceptPawns(final Color color) {
        return this.board.piecesByColor(color)
            .stream()
            .filter(Piece::isNotPawn)
            .mapToDouble(Piece::score)
            .sum();
    }
}
