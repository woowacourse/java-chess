package chess.dto;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.HashMap;
import java.util.Map;

public class PieceDto {

    private static final Map<Piece, String> names = new HashMap<>();
    private static final String EMPTY = "";

    static {
        setWhitePiece();
        setBlackPiece();
    }

    private static void setWhitePiece() {
        names.put(new Pawn(Color.WHITE), "p");
        names.put(new Rook(Color.WHITE), "r");
        names.put(new Knight(Color.WHITE), "n");
        names.put(new Bishop(Color.WHITE), "b");
        names.put(new Queen(Color.WHITE), "q");
        names.put(new King(Color.WHITE), "k");
    }

    private static void setBlackPiece() {
        names.put(new Pawn(Color.BLACK), "P");
        names.put(new Rook(Color.BLACK), "R");
        names.put(new Knight(Color.BLACK), "N");
        names.put(new Bishop(Color.BLACK), "B");
        names.put(new Queen(Color.BLACK), "Q");
        names.put(new King(Color.BLACK), "K");
    }

    public static String getName(final Piece piece) {
        String name = names.get(piece);
        if (name != null) {
            return names.get(piece);
        }
        return EMPTY;
    }
}
