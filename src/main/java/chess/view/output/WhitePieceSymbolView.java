package chess.view.output;

import chess.domain.piece.PieceSymbol;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum WhitePieceSymbolView {
    PAWN(PieceSymbol.PAWN, "p"),
    BISHOP(PieceSymbol.BISHOP, "b"),
    KING(PieceSymbol.KING, "k"),
    KNIGHT(PieceSymbol.KNIGHT, "n"),
    QUEEN(PieceSymbol.QUEEN, "q"),
    ROOK(PieceSymbol.ROOK, "r"),
    ;

    private final PieceSymbol pieceSymbol;
    private final String viewSymbol;

    WhitePieceSymbolView(PieceSymbol pieceSymbol, String viewSymbol) {
        this.pieceSymbol = pieceSymbol;
        this.viewSymbol = viewSymbol;
    }

    public static String getViewSymbolBy(PieceSymbol pieceSymbol) {
        return Arrays.stream(WhitePieceSymbolView.values())
                .filter(it -> it.pieceSymbol == pieceSymbol)
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .viewSymbol;
    }
}
