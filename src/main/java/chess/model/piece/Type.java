package chess.model.piece;

import java.util.Arrays;

public enum Type {
    WHITE_PAWN(Pawn.from(Color.WHITE)),
    WHITE_KNIGHT(Knight.from(Color.WHITE)),
    WHITE_BISHOP(Bishop.from(Color.WHITE)),
    WHITE_ROOK(Rook.from(Color.WHITE)),
    WHITE_QUEEN(Queen.from(Color.WHITE)),
    WHITE_KING(King.from(Color.WHITE)),
    BLACK_PAWN(Pawn.from(Color.BLACK)),
    BLACK_KNIGHT(Knight.from(Color.BLACK)),
    BLACK_BISHOP(Bishop.from(Color.BLACK)),
    BLACK_ROOK(Rook.from(Color.BLACK)),
    BLACK_QUEEN(Queen.from(Color.BLACK)),
    BLACK_KING(King.from(Color.BLACK)),
    EMPTY(Empty.getInstance());

    private final Piece piece;

    Type(Piece piece) {
        this.piece = piece;
    }

    private Piece getPiece() {
        return piece;
    }

    public static Type from(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceSignature -> pieceSignature.piece.equals(piece))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 타입입니다."));
    }

    public static Piece getPiece(String typeName) {
        return Arrays.stream(values())
                .filter(type -> type.name().equals(typeName))
                .findFirst()
                .map(Type::getPiece)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 타입입니다."));
    }
}
