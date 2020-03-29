package domain.move;

import domain.pieces.Piece;
import domain.point.Point;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public enum PieceDirectionType {
    KING("k", Direction.getAllDirection()),
    QUEEN("q", Direction.getAllDirection()),
    ROOK("r", Direction.getRookDirection()),
    BISHOP("b", Direction.getBishopDirection()),
    KNIGHT("n", Direction.getKnightDirection()),
    BLACK_PAWN("P", Direction.getBlackPawnDirection()),
    WHITE_PAWN("p", Direction.getWhitePawnDirection());

    private String initial;
    private List<Direction> directions;

    PieceDirectionType(String initial, List<Direction> directions) {
        this.initial = initial;
        this.directions = directions;
    }

    public static List<Direction> find(Map<Point, Piece> pieces, Point from) {
        return Arrays.stream(PieceDirectionType.values())
            .filter(type -> isSameInitial(type, pieces.get(from).getInitial()))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(""))
            .directions;

    }

    private static boolean isSameInitial(PieceDirectionType type, String initial) {
        if (initial.equals("P") || initial.equals("p")) {
            return type.initial.equals(initial);
        }
        return type.initial.equalsIgnoreCase(initial);
    }
}
