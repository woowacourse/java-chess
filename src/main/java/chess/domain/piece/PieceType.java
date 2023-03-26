package chess.domain.piece;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceType {
    QUEEN(Queen::new),
    ROOK(Rook::new),
    KNIGHT(Knight::new),
    PAWN(Pawn::new),
    BISHOP(Bishop::new),
    KING(King::new);

    private final BiFunction<PieceType, TeamColor, Piece> toPiece;

    PieceType(BiFunction<PieceType, TeamColor, Piece> toPiece) {
        this.toPiece = toPiece;
    }

    public static Piece toPiece(final PieceType pieceType, final TeamColor color) {
        return Arrays.stream(values())
                .filter(it -> it.name().equalsIgnoreCase(pieceType.name()))
                .map(it -> it.toPiece.apply(pieceType, color))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("기물을 찾을 수 없습니다."));
    }
}
