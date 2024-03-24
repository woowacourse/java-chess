package chess.view;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.model.piece.Type;

import java.util.Arrays;

public enum PieceSignature {
    WHITE_PAWN(PieceFactory.of(Color.WHITE, Type.PAWN), "p"),
    WHITE_KNIGHT(PieceFactory.of(Color.WHITE, Type.KNIGHT), "n"),
    WHITE_BISHOP(PieceFactory.of(Color.WHITE, Type.BISHOP), "b"),
    WHITE_ROOK(PieceFactory.of(Color.WHITE, Type.ROOK), "r"),
    WHITE_QUEEN(PieceFactory.of(Color.WHITE, Type.QUEEN), "q"),
    WHITE_KING(PieceFactory.of(Color.WHITE, Type.KING), "k"),
    BLACK_PAWN(PieceFactory.of(Color.BLACK, Type.PAWN), "P"),
    BLACK_KNIGHT(PieceFactory.of(Color.BLACK, Type.KNIGHT), "N"),
    BLACK_BISHOP(PieceFactory.of(Color.BLACK, Type.BISHOP), "B"),
    BLACK_ROOK(PieceFactory.of(Color.BLACK, Type.ROOK), "R"),
    BLACK_QUEEN(PieceFactory.of(Color.BLACK, Type.QUEEN), "Q"),
    BLACK_KING(PieceFactory.of(Color.BLACK, Type.KING), "K");

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
