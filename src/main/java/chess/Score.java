package chess;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class Score {

    private static final double EXIST_PAWN_SAME_COLUMN = 0.5;
    private static final int DUPLICATE = 2;

    private final double score;

    private Score(double score) {
        this.score = score;
    }

    public static Score create(Map<Position, Piece> pieces, Color color) {
        return new Score(compute(pieces, color));
    }

    private static double compute(Map<Position, Piece> pieces, Color color) {
        double score = pieces.keySet()
                .stream()
                .filter(position-> pieces.get(position).isColor(color))
                .mapToDouble(piece -> pieces.get(piece).getScore())
                .sum();

        for (int column = 0; column < Chessboard.SIZE.size(); column++) {
            score -= EXIST_PAWN_SAME_COLUMN * countSameColumnPawn(pieces, column, color);
        }
        return score;
    }

    private static long countSameColumnPawn(Map<Position, Piece> pieces, int column, Color color) {
        long count = Chessboard.SIZE.stream()
                .filter(row -> pieces.containsKey(new Position(row, column)))
                .filter(row -> pieces.get(new Position(row, column)).isColor(color)
                        && pieces.get(new Position(row, column)).isSameType(Pawn.class))
                .count();
        if (count < DUPLICATE) {
            return 0;
        }
        return count;
    }

    public double getScore() {
        return score;
    }
}
