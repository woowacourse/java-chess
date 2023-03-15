package view;

import domain.piece.Bishop;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import java.util.Arrays;

public enum PieceView {

    ROOK("R", "r", Rook.class),
    KNIGHT("N", "n", Knight.class),
    BISHOP("B", "b", Bishop.class),
    QUEEN("Q", "q", Queen.class),
    KING("K", "k", King.class),
    PAWN("P", "p", Pawn.class);

    private final String blackSign;
    private final String whiteSign;
    private final Class<? extends Piece> piece;

    PieceView(final String blackSign, final String whiteSign, final Class<? extends Piece> piece) {
        this.blackSign = blackSign;
        this.whiteSign = whiteSign;
        this.piece = piece;
    }

    public static String findSign(final Piece piece) {
        final PieceView pieceView = Arrays.stream(PieceView.values())
            .filter(view -> view.piece.equals(piece.getClass()))
            .findAny()
            .orElseThrow(IllegalArgumentException::new);
        if (piece.isBlack()) {
            return pieceView.blackSign;
        }
        return pieceView.whiteSign;
    }
}
