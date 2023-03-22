package chess.view;

import chess.domain.piece.PieceSymbol;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ViewPieceSymbol {
    PAWN(PieceSymbol.PAWN, "p"),
    BISHOP(PieceSymbol.BISHOP, "b"),
    KING(PieceSymbol.KING, "k"),
    KNIGHT(PieceSymbol.KNIGHT, "n"),
    QUEEN(PieceSymbol.QUEEN, "q"),
    ROOK(PieceSymbol.ROOK, "r"),
    EMPTY(PieceSymbol.EMPTY, "."),
    ;

    private final PieceSymbol pieceSymbol;
    private final String viewSymbol;

    ViewPieceSymbol(PieceSymbol pieceSymbol, String viewSymbol) {
        this.pieceSymbol = pieceSymbol;
        this.viewSymbol = viewSymbol;
    }

    public static String getViewSymbolBy(PieceSymbol pieceSymbol, boolean upperFlag) {
        String viewSymbol = Arrays.stream(ViewPieceSymbol.values())
                .filter(it -> it.pieceSymbol == pieceSymbol)
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .viewSymbol;

        return applyUpperFlag(viewSymbol, upperFlag);
    }

    private static String applyUpperFlag(String viewSymbol, boolean upperFlag) {
        if (upperFlag) {
            return viewSymbol.toUpperCase();
        }

        return viewSymbol;
    }
}
