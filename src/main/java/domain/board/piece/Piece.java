package domain.board.piece;

import domain.path.Path;
import domain.path.pathValidtor.PathValidator;
import domain.path.pathValidtor.PathValidatorFactory;

public final class Piece {

    private final PieceState pieceState;
    private final PathValidator pathValidator;

    private Piece(final PieceState pieceState, final PathValidator pathValidator) {
        this.pieceState = pieceState;
        this.pathValidator = pathValidator;
    }

    public static Piece of(final PieceType pieceType, final Camp camp) {
        return new Piece(
            new PieceState(pieceType, camp),
            PathValidatorFactory.findByPieceType(pieceType)
        );
    }

    public static Piece pawnBelongs(final Camp camp) {
        return new Piece(
            new PieceState(PieceType.PAWN, camp),
            PathValidatorFactory.pawnPathValidator()
        );
    }

    public static Piece rookBelongs(final Camp camp) {
        return new Piece(
            new PieceState(PieceType.ROOK, camp),
            PathValidatorFactory.rookPathValidator()
        );
    }

    public static Piece bishopBelongs(final Camp camp) {
        return new Piece(
            new PieceState(PieceType.BISHOP, camp),
            PathValidatorFactory.bishopPathValidator()
        );
    }

    public static Piece knightBelongs(final Camp camp) {
        return new Piece(
            new PieceState(PieceType.KNIGHT, camp),
            PathValidatorFactory.knightPathValidator()
        );
    }

    public static Piece queenBelongs(final Camp camp) {
        return new Piece(
            new PieceState(PieceType.QUEEN, camp),
            PathValidatorFactory.queenPathValidator()
        );
    }

    public static Piece kingBelongs(final Camp camp) {
        return new Piece(
            new PieceState(PieceType.KING, camp),
            PathValidatorFactory.kingPathValidator()
        );
    }

    public static Piece empty() {
        return new Piece((
            new PieceState(PieceType.EMPTY, Camp.NONE)),
            PathValidatorFactory.emptyPiecePathValidator()
        );
    }

    public void validatePath(final Path path) {
        pathValidator.validate(path);
    }

    public boolean isEmpty() {
        return pieceState.getType() == PieceType.EMPTY;
    }

    public double getScore() {
        return pieceState.getType().getScore();
    }

    public PieceType getType() {
        return pieceState.getType();
    }

    public Camp getCamp() {
        return pieceState.getCamp();
    }

    @Override
    public String toString() {
        return "Piece{" +
            "pieceState=" + pieceState +
            ", pathValidator=" + pathValidator +
            '}';
    }
}
