package chess.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public class BoardFactory {

    public static Map<Position, Piece> getInitialPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        placePawns(pieces);
        placeRooks(pieces);
        placeKnights(pieces);
        placeBishops(pieces);
        placeQueens(pieces);
        placeKings(pieces);
        return pieces;
    }

    private static void placePawns(Map<Position, Piece> pieces) {
        for (Abscissa value : Abscissa.values()) {
            pieces.put(Position.valueOf(value, Ordinate.TWO), new Pawn(Color.WHITE));
            pieces.put(Position.valueOf(value, Ordinate.SEVEN), new Pawn(Color.BLACK));
        }
    }

    private static void placeRooks(Map<Position, Piece> pieces) {
        List<Abscissa> rookAbscissa = List.of(Abscissa.a, Abscissa.h);
        for (Abscissa abscissa : rookAbscissa) {
            pieces.put(Position.valueOf(abscissa, Ordinate.ONE), new Rook(Color.WHITE));
            pieces.put(Position.valueOf(abscissa, Ordinate.EIGHT), new Rook(Color.BLACK));
        }
    }

    private static void placeKnights(Map<Position, Piece> pieces) {
        List<Abscissa> knightAbscissa = List.of(Abscissa.b, Abscissa.g);
        for (Abscissa abscissa : knightAbscissa) {
            pieces.put(Position.valueOf(abscissa, Ordinate.ONE), new Knight(Color.WHITE));
            pieces.put(Position.valueOf(abscissa, Ordinate.EIGHT), new Knight(Color.BLACK));
        }
    }

    private static void placeBishops(Map<Position, Piece> pieces) {
        List<Abscissa> bishopAbscissa = List.of(Abscissa.c, Abscissa.f);
        for (Abscissa abscissa : bishopAbscissa) {
            pieces.put(Position.valueOf(abscissa, Ordinate.ONE), new Bishop(Color.WHITE));
            pieces.put(Position.valueOf(abscissa, Ordinate.EIGHT), new Bishop(Color.BLACK));
        }
    }

    private static void placeQueens(Map<Position, Piece> pieces) {
        pieces.put(Position.valueOf(Abscissa.d, Ordinate.ONE), new Queen(Color.WHITE));
        pieces.put(Position.valueOf(Abscissa.d, Ordinate.EIGHT), new Queen(Color.BLACK));
    }

    private static void placeKings(Map<Position, Piece> pieces) {
        pieces.put(Position.valueOf(Abscissa.e, Ordinate.ONE), new King(Color.WHITE));
        pieces.put(Position.valueOf(Abscissa.e, Ordinate.EIGHT), new King(Color.BLACK));
    }
}
