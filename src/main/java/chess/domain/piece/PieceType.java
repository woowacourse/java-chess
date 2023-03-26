package chess.domain.piece;

import chess.domain.Camp;
import java.util.function.Function;

public enum PieceType {

    ROOK(5, Rook::new),
    KNIGHT(2.5, Knight::new),
    BISHOP(3, Bishop::new),
    QUEEN(9, Queen::new),
    KING(0, King::new),
    PAWN(1, Pawn::new),
    EMPTY(0, camp -> Empty.getInstance());


    private final double point;
    private final Function<Camp, Piece> pieceSupplier;

    PieceType(final double point, final Function<Camp, Piece> pieceSupplier) {
        this.point = point;
        this.pieceSupplier = pieceSupplier;
    }

    public double getPoint() {
        return point;
    }

    public Piece createPiece(Camp camp) {
        return pieceSupplier.apply(camp);
    }
}
