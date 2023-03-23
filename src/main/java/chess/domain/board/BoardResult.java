package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardResult {
    private static final int PAWN_COUNT = 2;
    private static final double PAWN_POINT = 0.5;

    private final Map<Position, Piece> board;

    public BoardResult(final Map<Position, Piece> board) {
        this.board = board;
    }

    public double calculatePoints(final Color color) {
        return calculateTotalPoints(color) - calculatePawnPoints(color);
    }

    private double calculateTotalPoints(final Color color) {
        return board.values().stream()
                .filter(piece -> piece.color() == color)
                .peek(piece -> System.out.println(piece))
                .mapToDouble(pieceType -> pieceType.point())
                .sum();
    }

    private double calculatePawnPoints(final Color color) {
        final Map<File, List<Position>> positionsByPawn = board.keySet().stream()
                .filter(key -> board.get(key).type() == PieceType.PAWN)
                .filter(key -> board.get(key).color() == color)
                .collect(Collectors.groupingBy(position -> position.file()));

        return positionsByPawn.values().stream()
                .filter(pawnPositions -> pawnPositions.size() >= PAWN_COUNT)
                .mapToDouble(pawnPositions -> pawnPositions.size() * PAWN_POINT)
                .sum();
    }

    public Color calculateWinner() {
        if (isKingDead(Color.WHITE)) {
            return Color.BLACK;
        }
        if (isKingDead(Color.BLACK)) {
            return Color.WHITE;
        }
        return winsByScore();
    }

    private boolean isKingDead(final Color color) {
        return !board.containsValue(King.from(color));
    }

    private Color winsByScore() {
        final double whiteScore = calculatePoints(Color.WHITE);
        final double blackScore = calculatePoints(Color.BLACK);

        if (whiteScore == blackScore) {
            return Color.EMPTY;
        }
        if (whiteScore > blackScore) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
