package chess.view;

import chess.model.piece.*;

import java.util.Arrays;

public enum PieceSignature {
    WHITE_PAWN(Pawn.from(Color.WHITE), "p"),
    WHITE_KNIGHT(Pawn.from(Color.WHITE), "n"),
    WHITE_BISHOP(Bishop.from(Color.WHITE), "b"),
    WHITE_ROOK(Rook.from(Color.WHITE), "r"),
    WHITE_QUEEN(Queen.from(Color.WHITE), "q"),
    WHITE_KING(King.from(Color.WHITE), "k"),
    BLACK_PAWN(Pawn.from(Color.BLACK), "P"),
    BLACK_KNIGHT(Knight.from(Color.BLACK), "N"),
    BLACK_BISHOP(Bishop.from(Color.BLACK), "B"),
    BLACK_ROOK(Rook.from(Color.BLACK), "R"),
    BLACK_QUEEN(Queen.from(Color.BLACK), "Q"),
    BLACK_KING(King.from(Color.BLACK), "K");

    private static final String EMPTY = ".";

    private final Piece piece;
    private final String signature;

    PieceSignature(Piece piece, String signature) {
        this.piece = piece;
        this.signature = signature;
    }

    public static String getSignature(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceSignature -> pieceSignature.piece.equals(piece))
                .findFirst()
                .map(PieceSignature::getSignature)
                .orElse(EMPTY);
    }

    private String getSignature() {
        return signature;
    }
}
