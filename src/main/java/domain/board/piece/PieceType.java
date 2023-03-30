package domain.board.piece;

import domain.path.pathValidtor.PathValidator;
import domain.path.pathValidtor.PathValidatorFactory;
import java.util.Arrays;

public enum PieceType {
    PAWN(1, PathValidatorFactory.pawnPathValidator()),
    KING(0, PathValidatorFactory.kingPathValidator()),
    QUEEN(9, PathValidatorFactory.queenPathValidator()),
    ROOK(5, PathValidatorFactory.rookPathValidator()),
    BISHOP(3, PathValidatorFactory.bishopPathValidator()),
    KNIGHT(2.5, PathValidatorFactory.knightPathValidator()),
    EMPTY(0, PathValidatorFactory.emptyPiecePathValidator());

    private final double score;
    private final PathValidator pathValidator;

    PieceType(final double score, final PathValidator pathValidator) {
        this.score = score;
        this.pathValidator = pathValidator;
    }

    public double getScore() {
        return score;
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
