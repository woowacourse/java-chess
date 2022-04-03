package chess.domain.piece;

import java.util.List;
import java.util.Map;

public class PieceSymbol {

    private PieceSymbol() {
        throw new AssertionError();
    }

    private static final int CONSOLE_UI_SYMBOL_INDEX = 0;
    private static final int WEB_UI_SYMBOL_INDEX = 1;

    private static final Map<Class<? extends Piece>, List<String>> symbolsByPiece = Map.of(
            King.class, List.of("k", "king"),
            Queen.class, List.of("q", "queen"),
            Bishop.class, List.of("b", "bishop"),
            Knight.class, List.of("n", "knight"),
            Rook.class, List.of("r", "rook"),
            Pawn.class, List.of("p", "pawn")
    );

    public static String findWebSymbol(final Piece piece) {
        return symbolsByPiece.get(piece.getClass())
                .get(WEB_UI_SYMBOL_INDEX);
    }

    public static String findConsoleSymbol(final Piece piece) {
        final String symbol = symbolsByPiece.get(piece.getClass())
                .get(CONSOLE_UI_SYMBOL_INDEX);
        return convertByTeamColor(piece, symbol);
    }

    private static String convertByTeamColor(final Piece piece, final String symbol) {
        if (piece.isBlackTeam()) {
            return symbol.toUpperCase();
        }
        return symbol;
    }
}
