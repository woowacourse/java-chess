package chess.view.output;

import chess.domain.piece.PieceSymbol;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum BlackPieceSymbolView {
    PAWN(PieceSymbol.PAWN, "P"),
    BISHOP(PieceSymbol.BISHOP, "B"),
    KING(PieceSymbol.KING, "K"),
    KNIGHT(PieceSymbol.KNIGHT, "N"),
    QUEEN(PieceSymbol.QUEEN, "Q"),
    ROOK(PieceSymbol.ROOK, "R"),
    ;

    private final PieceSymbol pieceSymbol;
    private final String viewSymbol;

    BlackPieceSymbolView(PieceSymbol pieceSymbol, String viewSymbol) {
        this.pieceSymbol = pieceSymbol;
        this.viewSymbol = viewSymbol;
    }

    public static String getViewSymbolBy(PieceSymbol pieceSymbol) {
        return Arrays.stream(BlackPieceSymbolView.values())
                .filter(it -> it.pieceSymbol == pieceSymbol)
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .viewSymbol;
    }
}
