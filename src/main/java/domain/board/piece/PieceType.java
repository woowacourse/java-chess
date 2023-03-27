package domain.board.piece;

import domain.path.direction.Direction;
import java.util.Arrays;
import java.util.List;

public enum PieceType {
    PAWN(1, List.of(
        Direction.UP,
        Direction.DOWN,
        Direction.LEFT,
        Direction.RIGHT,
        Direction.LEFT_UP,
        Direction.LEFT_DOWN,
        Direction.RIGHT_UP,
        Direction.RIGHT_DOWN
    )),
    KING(0, List.of(
        Direction.UP,
        Direction.DOWN,
        Direction.LEFT,
        Direction.RIGHT,
        Direction.LEFT_UP,
        Direction.LEFT_DOWN,
        Direction.RIGHT_UP,
        Direction.RIGHT_DOWN
    )),
    QUEEN(9, List.of(
        Direction.UP,
        Direction.DOWN,
        Direction.LEFT,
        Direction.RIGHT,
        Direction.LEFT_UP,
        Direction.LEFT_DOWN,
        Direction.RIGHT_UP,
        Direction.RIGHT_DOWN
    )),
    ROOK(5, List.of(
        Direction.UP,
        Direction.DOWN,
        Direction.LEFT,
        Direction.RIGHT
    )),
    BISHOP(3, List.of(
        Direction.LEFT_UP,
        Direction.LEFT_DOWN,
        Direction.RIGHT_UP,
        Direction.RIGHT_DOWN
    )),
    KNIGHT(2.5, List.of(
        Direction.UP_UP_LEFT,
        Direction.UP_UP_RIGHT,
        Direction.DOWN_DOWN_LEFT,
        Direction.DOWN_DOWN_RIGHT,
        Direction.RIGHT_RIGHT_UP,
        Direction.RIGHT_RIGHT_DOWN,
        Direction.LEFT_LEFT_UP,
        Direction.LEFT_LEFT_DOWN
    )),
    EMPTY(0, List.of());

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
