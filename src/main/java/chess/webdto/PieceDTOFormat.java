package chess.webdto;

import chess.domain.piece.*;

import java.util.HashMap;
import java.util.Map;

public class PieceDTOFormat {
    private static final Map<Piece, String> pieceFormat = new HashMap<>();

    static {
        pieceFormat.put(new Pawn(1), "Pawn");
        pieceFormat.put(new Pawn(-1), "Pawn");
        pieceFormat.put(new Rook(), "Rook");
        pieceFormat.put(new Knight(), "Knight");
        pieceFormat.put(new Bishop(), "Bishop");
        pieceFormat.put(new Queen(), "Queen");
        pieceFormat.put(new King(), "King");
    }

    private PieceDTOFormat() {
    }

    public static String convert(final Piece piece) {
        return pieceFormat.get(piece);
    }
}
