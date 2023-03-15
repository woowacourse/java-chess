package chess.view;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ViewPieceSymbol {

    PAWN("PAWN", "p"),
    BISHOP("BISHOP", "b"),
    KING("KING", "k"),
    KNIGHT("KNIGHT", "n"),
    QUEEN("QUEEN", "q"),
    ROOK("ROOK", "r"),
    EMPTY("EMPTY", "."),
    ;

    private final String pieceSymbol;
    private final String viewSymbol;

    ViewPieceSymbol(String pieceSymbol, String viewSymbol) {
        this.pieceSymbol = pieceSymbol;
        this.viewSymbol = viewSymbol;
    }

    public static String getViewSymbolBy(String pieceSymbol, boolean upperFlag) {
        String viewSymbol = Arrays.stream(ViewPieceSymbol.values())
                .filter(it -> it.pieceSymbol.equals(pieceSymbol))
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
