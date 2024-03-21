package chess.view;

import chess.domain.piece.Piece;

import java.util.Arrays;

public enum PieceView {

    KING("K", "k"),
    QUEEN("Q", "q"),
    ROOK("R", "r"),
    BISHOP("B", "b"),
    KNIGHT("N", "n"),
    PAWN("P", "p"),
    EMPTY(".", "."),
    ;

    private final String blackView;
    private final String whiteView;

    PieceView(String blackView, String whiteView) {
        this.blackView = blackView;
        this.whiteView = whiteView;
    }

    public static String toView(Piece piece) {
        PieceView pieceView = Arrays.stream(PieceView.values())
                .filter(p -> piece.isSameType(p.name()))
                .findFirst()
                .orElseThrow();

        return selectByColor(piece, pieceView);
    }

    private static String selectByColor(Piece piece, PieceView pieceView) {
        if (piece.isBlack()) {
            return pieceView.blackView;
        }

        return pieceView.whiteView;
    }
}
