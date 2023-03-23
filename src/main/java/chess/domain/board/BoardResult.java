package chess.domain.board;

import chess.domain.piece.Color;
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
        return board.values().stream()
                .filter(piece -> piece.color() == color)
                .mapToDouble(pieceType -> pieceType.point())
                .sum();
    }
}
