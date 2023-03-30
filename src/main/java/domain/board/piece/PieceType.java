package domain.board.piece;

import domain.path.direction.Direction;
import java.util.Arrays;
import java.util.List;

public enum PieceType {
    PAWN(1, Direction.pawnDirections()),
    KING(0, Direction.kingDirections()),
    QUEEN(9, Direction.queenDirections()),
    ROOK(5, Direction.rookDirections()),
    BISHOP(3, Direction.bishopDirections()),
    KNIGHT(2.5, Direction.knightDirections()),
    EMPTY(0, Direction.emptyDirections());

    private final double score;
    private final List<Direction> possibleDirections;

    PieceType(final double score, final List<Direction> possibleDirections) {
        this.score = score;
        this.possibleDirections = possibleDirections;
    }

    public double getScore() {
        return score;
    }

    public List<Direction> getPossibleDirections() {
        return possibleDirections;
    }

    public static PieceType findByName(String name) {
        return Arrays.stream(values())
            .filter(pieceType -> pieceType.name().equals(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 이름의 기물이 존재하지 않습니다."));
    }
}
