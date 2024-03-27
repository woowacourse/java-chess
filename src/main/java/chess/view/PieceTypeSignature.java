package chess.view;

import chess.model.piece.Type;

import java.util.Arrays;

public enum PieceTypeSignature {
    WHITE_PAWN(Type.WHITE_PAWN, "p"),
    WHITE_KNIGHT(Type.WHITE_KNIGHT, "n"),
    WHITE_BISHOP(Type.WHITE_BISHOP, "b"),
    WHITE_ROOK(Type.WHITE_ROOK, "r"),
    WHITE_QUEEN(Type.WHITE_QUEEN, "q"),
    WHITE_KING(Type.WHITE_KING, "k"),
    BLACK_PAWN(Type.BLACK_PAWN, "P"),
    BLACK_KNIGHT(Type.BLACK_KNIGHT, "N"),
    BLACK_BISHOP(Type.BLACK_BISHOP, "B"),
    BLACK_ROOK(Type.BLACK_ROOK, "R"),
    BLACK_QUEEN(Type.BLACK_QUEEN, "Q"),
    BLACK_KING(Type.BLACK_KING, "K"),
    EMPTY(Type.EMPTY, ".");

    private final Type type;
    private final String signature;

    PieceTypeSignature(Type type, String signature) {
        this.type = type;
        this.signature = signature;
    }

    public static String getSignature(Type pieceType) {
        return Arrays.stream(values())
                .filter(pieceTypeSignature -> pieceTypeSignature.type == pieceType)
                .findFirst()
                .map(PieceTypeSignature::getSignature)
                .orElse(EMPTY.signature);
    }

    private String getSignature() {
        return signature;
    }
}
