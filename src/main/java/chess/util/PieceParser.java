package chess.util;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Name;
import chess.domain.pieces.Pawn;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Place;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;

public class PieceParser {
    
    public static Piece parsePiece(final String name) {
        if (name.equals("r") || name.equals("R")) {
            return new Rook(new Name(name));
        }

        if (name.equals("n") || name.equals("N")) {
            return new Knight(new Name(name));
        }

        if (name.equals("b") || name.equals("B")) {
            return new Bishop(new Name(name));
        }

        if (name.equals("q") || name.equals("Q")) {
            return new Queen(new Name(name));
        }

        if (name.equals("p") || name.equals("P")) {
            return new Pawn(new Name(name));
        }

        if (name.equals("k") || name.equals("K")) {
            return new King(new Name(name));
        }

        return new Place();
    }
}
