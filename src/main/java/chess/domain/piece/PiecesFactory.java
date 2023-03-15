package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;

public class PiecesFactory {
    public static Pieces create(Color color) {
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new King(color));
        pieces.add(new Queen(color));
        pieces = addPiece(pieces, new Bishop(color));
        pieces = addPiece(pieces, new Rook(color));
        pieces = addPiece(pieces, new Knight(color));
        pieces = addPawn(pieces, color);
        return new Pieces(pieces);
    }

    private static List<Piece> addPawn(final List<Piece> pieces, final Color color) {
        for (int i = 0; i < 8; i++) {
            pieces.add(new Pawn(color));
        }
        return pieces;
    }

    private static List<Piece> addPiece(final List<Piece> pieces, final Piece piece) {
        for (int i = 0; i < 2; i++) {
            pieces.add(piece);
        }
        return pieces;
    }
}
