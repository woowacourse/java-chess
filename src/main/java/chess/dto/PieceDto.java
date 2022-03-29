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
        names.put(new Pawn(Color.WHITE), "♙");
        names.put(new Rook(Color.WHITE), "♖");
        names.put(new Knight(Color.WHITE), "♘");
        names.put(new Bishop(Color.WHITE), "♗");
        names.put(new Queen(Color.WHITE), "♕");
        names.put(new King(Color.WHITE), "♔");
    }

    private static void setBlackPiece() {
        names.put(new Pawn(Color.BLACK), "♟");
        names.put(new Rook(Color.BLACK), "♜");
        names.put(new Knight(Color.BLACK), "♞");
        names.put(new Bishop(Color.BLACK), "♝");
        names.put(new Queen(Color.BLACK), "♛");
        names.put(new King(Color.BLACK), "♚");
    }

    public static String getName(final Piece piece) {
        String name = names.get(piece);
        if (name != null) {
            return names.get(piece);
        }
        return EMPTY;
    }
}
