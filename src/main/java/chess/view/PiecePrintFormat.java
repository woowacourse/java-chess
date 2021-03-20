package chess.view;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PiecePrintFormat {
    private static final Map<Piece, String> pieceFormat = new HashMap<>();

    static {
        pieceFormat.put(new Pawn(1), "p");
        pieceFormat.put(new Pawn(-1), "P");
        pieceFormat.put(new Rook(), "R");
        pieceFormat.put(new Knight(), "N");
        pieceFormat.put(new Bishop(), "B");
        pieceFormat.put(new Queen(), "Q");
        pieceFormat.put(new King(), "K");
    }

    private PiecePrintFormat() {
    }

    public static String convert(final Piece piece) {
        return pieceFormat.get(piece);
    }
}
