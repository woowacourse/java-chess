package chess.view.output;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum BlackPieceSymbolView {
    PAWN(Pawn.class, "P"),
    BISHOP(Bishop.class, "B"),
    KING(King.class, "K"),
    KNIGHT(Knight.class, "N"),
    QUEEN(Queen.class, "Q"),
    ROOK(Rook.class, "R"),
    ;

    private final Class<? extends Piece> piece;
    private final String viewSymbol;

    BlackPieceSymbolView(Class<? extends Piece> piece, String viewSymbol) {
        this.piece = piece;
        this.viewSymbol = viewSymbol;
    }

    public static String getViewSymbolBy(Piece piece) {
        return Arrays.stream(BlackPieceSymbolView.values())
                .filter(it -> it.piece.isInstance(piece))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .viewSymbol;
    }
}
