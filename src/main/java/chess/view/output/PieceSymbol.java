package chess.view.output;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;

public class PieceSymbol {

    private PieceSymbol() {
        throw new AssertionError();
    }

    private static final Map<Class<? extends Piece>, String> symbolsByPiece = Map.of(
            King.class, "k",
            Queen.class, "q",
            Bishop.class, "b",
            Knight.class, "n",
            Rook.class, "r",
            Pawn.class, "p"
    );

    public static String findSymbol(final Piece piece) {
        final String symbol = symbolsByPiece.get(piece.getClass());
        return convertByTeamColor(piece, symbol);
    }

    private static String convertByTeamColor(final Piece piece, final String symbol) {
        if (piece.isBlackTeam()) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
