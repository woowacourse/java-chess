package chess.domain.piece;

import java.util.Arrays;

public enum PieceType {
    ROOK(RookPiece.class, "r"),
    KNIGHT(KnightPiece.class, "n"),
    BISHOP(BishopPiece.class, "b"),
    QUEEN(QueenPiece.class, "q"),
    KING(KingPiece.class, "k"),
    PAWN(PawnPiece.class, "p"),
    EMPTY(EmptyPiece.class, ".");

    private final Class<Piece> type;
    private final String name;

    PieceType(Class type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameOf(Piece piece) {
        PieceType targetType = Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType.type == piece.getClass())
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("해당 Type 은 존재하지 않습니다."));
        return targetType.name;
    }
}
