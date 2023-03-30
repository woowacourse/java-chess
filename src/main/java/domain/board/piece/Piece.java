package domain.board.piece;

import domain.path.Path;

public final class Piece {

    private final PieceState pieceState;

    public Piece(PieceState pieceState) {
        this.pieceState = pieceState;
    }

    public static Piece of(final PieceType pieceType, final Camp camp) {
        return new Piece(new PieceState(pieceType, camp));
    }

    public static Piece pawnBelongs(final Camp camp) {
        return new Piece(new PieceState(PieceType.PAWN, camp));
    }

    public static Piece rookBelongs(final Camp camp) {
        return new Piece(new PieceState(PieceType.ROOK, camp));
    }

    public static Piece bishopBelongs(final Camp camp) {
        return new Piece(new PieceState(PieceType.BISHOP, camp));
    }

    public static Piece knightBelongs(final Camp camp) {
        return new Piece(new PieceState(PieceType.KNIGHT, camp));
    }

    public static Piece queenBelongs(final Camp camp) {
        return new Piece(new PieceState(PieceType.QUEEN, camp));
    }

    public static Piece kingBelongs(final Camp camp) {
        return new Piece(new PieceState(PieceType.KING, camp));
    }

    public static Piece empty() {
        return new Piece(new PieceState(PieceType.EMPTY, Camp.NONE));
    }

    public void validatePath(final Path path) {
        pieceState.getType()
            .getPathValidator()
            .validate(path);
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
            '}';
    }
}
