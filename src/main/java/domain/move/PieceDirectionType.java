package domain.move;

import domain.move.exceptions.isNotExistPieceDirectionTypeException;
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
            .orElseThrow(() -> new isNotExistPieceDirectionTypeException("PieceDirectionType 이 존재하지 않습니다."))
            .directions;

    }

    private static boolean isSameInitial(PieceDirectionType type, String initial) {
        if (initial.equals(BLACK_PAWN.initial) || initial.equals(WHITE_PAWN.initial)) {
            return type.initial.equals(initial);
        }
        return type.initial.equalsIgnoreCase(initial);
    }
}
