package chess.model.piece;


import java.util.Arrays;

public enum PieceType {

    p(Pawn::new),
    r(Rook::new),
    b(Bishop::new),
    n(Knight::new),
    q(Queen::new),
    k(King::new),
    empty(((id, color, squareId) -> new Empty(id, Color.EMPTY, squareId))),
    ;

    private final PieceMapper function;

    PieceType(PieceMapper function) {
        this.function = function;
    }

    public static Piece getPiece(String name, int pieceId, Color color, int squareId) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .function.mapToPiece(pieceId, color, squareId);
    }

}
