package chess.domain.piece;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Square;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    public static List<Piece> createPosition() {
        final Map<Square, Piece> position = new LinkedHashMap<>();

        position.put(Square.of(File.A, Rank.EIGHT), new Rook(Color.BLACK));

        return new ArrayList<>(position.values());
    }
}
