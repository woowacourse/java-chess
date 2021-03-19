package chess.view;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PieceNameConverter {
    private static final Map<Piece, String> converter = new HashMap<>();

    static {
        converter.put(new Pawn(1), "p");
        converter.put(new Pawn(-1), "P");
        converter.put(new Rook(), "R");
        converter.put(new Knight(), "N");
        converter.put(new Bishop(), "B");
        converter.put(new Queen(), "Q");
        converter.put(new King(), "K");
    }

    private PieceNameConverter() {
    }

    public static String convert(final Piece piece) {
        return converter.get(piece);
    }
}
