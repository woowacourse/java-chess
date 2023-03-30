package domain.board.piece;

import domain.path.direction.Direction;
import domain.path.pathValidtor.PathValidator;
import domain.path.pathValidtor.PathValidatorFactory;
import java.util.Arrays;
import java.util.List;

public enum PieceType {
    PAWN(1, Direction.pawnDirections(), PathValidatorFactory.pawnPathValidator()),
    KING(0, Direction.kingDirections(), PathValidatorFactory.kingPathValidator()),
    QUEEN(9, Direction.queenDirections(), PathValidatorFactory.queenPathValidator()),
    ROOK(5, Direction.rookDirections(), PathValidatorFactory.rookPathValidator()),
    BISHOP(3, Direction.bishopDirections(), PathValidatorFactory.bishopPathValidator()),
    KNIGHT(2.5, Direction.knightDirections(), PathValidatorFactory.knightPathValidator()),
    EMPTY(0, Direction.emptyDirections(), PathValidatorFactory.emptyPiecePathValidator());

    private final double score;
    private final List<Direction> possibleDirections;
    private final PathValidator pathValidator;

    PieceType(double score, List<Direction> possibleDirections, PathValidator pathValidator) {
        this.score = score;
        this.possibleDirections = possibleDirections;
        this.pathValidator = pathValidator;
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

    public PathValidator getPathValidator() {
        return pathValidator;
    }
}
