package view;

import domain.piece.Piece;
import domain.type.PieceType;
import java.util.Arrays;

public enum PieceView {

    ROOK("R", "r", PieceType.ROOK),
    KNIGHT("N", "n", PieceType.KNIGHT),
    BISHOP("B", "b", PieceType.BISHOP),
    QUEEN("Q", "q", PieceType.QUEEN),
    KING("K", "k", PieceType.KING),
    PAWN("P", "p", PieceType.PAWN),
    EMPTY(".", ".", PieceType.EMPTY);

    private final String blackSign;
    private final String whiteSign;
    private final PieceType pieceType;

    PieceView(final String blackSign, final String whiteSign, final PieceType pieceType) {
        this.blackSign = blackSign;
        this.whiteSign = whiteSign;
        this.pieceType = pieceType;
    }

    public static String findSign(final Piece piece) {
        final PieceView pieceView = Arrays.stream(PieceView.values()).filter(view -> piece.isSameType(view.pieceType))
            .findAny().orElseThrow(IllegalArgumentException::new);
        if (piece.isBlack()) {
            return pieceView.blackSign;
        }
        return pieceView.whiteSign;
    }
}
