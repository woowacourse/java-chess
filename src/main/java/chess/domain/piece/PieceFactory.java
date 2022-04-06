package chess.domain.piece;

import chess.domain.Color;

import java.util.LinkedHashMap;
import java.util.Map;

public class PieceFactory {
    private static final Map<String, Piece> pieces = new LinkedHashMap<>();

    static {
        pieces.put("bishopwhite", new Bishop(Color.WHITE));
        pieces.put("rookwhite", new Rook(Color.WHITE));
        pieces.put("pawnwhite", new Pawn(Color.WHITE));
        pieces.put("knightwhite", new Knight(Color.WHITE));
        pieces.put("kingwhite", new King(Color.WHITE));
        pieces.put("queenwhite", new Queen(Color.WHITE));

        pieces.put("bishopblack", new Bishop(Color.BLACK));
        pieces.put("rookblack", new Rook(Color.BLACK));
        pieces.put("pawnblack", new Pawn(Color.BLACK));
        pieces.put("knightblack", new Knight(Color.BLACK));
        pieces.put("kingblack", new King(Color.BLACK));
        pieces.put("queenblack", new Queen(Color.BLACK));

        pieces.put("blanknone", new Blank());
    }

    public static Piece of(String piece) {
        return pieces.get(piece);
    }
}
