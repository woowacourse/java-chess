package chess.model.board;

import static java.util.stream.Collectors.toList;

import chess.model.Score;
import chess.model.piece.Piece;
import chess.model.piece.PieceColor;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Positions;
import java.util.List;
import java.util.Map;

public class ScoreCalculator {

    private static final int DEFAULT_FILE_PAWN_COUNT = 1;
    private static final double CORRECT_SAME_FILE_PAWN_SCORE = 0.5;

    public Score calculateScore(final PieceColor targetColor, final Map<Position, Piece> squares) {
        final double sum = calculateTotalSum(targetColor, squares);
        double sameFilePawn = calculateSameFilePawn(targetColor, squares);

        final double totalSum = sum - sameFilePawn * CORRECT_SAME_FILE_PAWN_SCORE;

        return new Score(targetColor, totalSum);
    }

    private double calculateTotalSum(final PieceColor targetColor, final Map<Position, Piece> squares) {
        return squares.values().stream()
                .filter(piece -> piece.getColor().isSameColor(targetColor))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    private double calculateSameFilePawn(final PieceColor targetColor, final Map<Position, Piece> squares) {
        double sameFilePawn = 0;
        for (File file : File.values()) {
            sameFilePawn += countSameFilePawn(targetColor, file, squares);
        }

        return sameFilePawn;
    }

    private double countSameFilePawn(final PieceColor targetColor, final File file, final Map<Position, Piece> squares) {
        final List<Position> sameFilePositions = Positions.getPositionsBy(file);
        final List<Piece> sameFilePieces = findSameFilePieces(sameFilePositions, squares);
        return countPawn(sameFilePieces, targetColor);
    }

    private List<Piece> findSameFilePieces(final List<Position> sameFilePositions, final Map<Position, Piece> squares) {
        return sameFilePositions.stream()
                .map(squares::get)
                .collect(toList());
    }

    private static long countPawn(final List<Piece> sameFilePieces, final PieceColor targetColor) {
        final long count = sameFilePieces.stream()
                .filter(Piece::isPawn)
                .filter(piece -> piece.getColor().isSameColor(targetColor))
                .count();

        return removeDifferentFilePawn(count);
    }

    private static long removeDifferentFilePawn(final long count) {
        if (count <= DEFAULT_FILE_PAWN_COUNT) {
            return 0;
        }

        return count;
    }
}
