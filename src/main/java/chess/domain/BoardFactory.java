package chess.domain;

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

public class BoardFactory {

    public static Map<Position, Piece> getInitialPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(getInitialBishops());
        pieces.putAll(getInitialKings());
        pieces.putAll(getInitialKnights());
        pieces.putAll(getInitialPawns());
        pieces.putAll(getInitialRooks());
        pieces.putAll(getInitialQueens());
        return pieces;
    }

    private static Map<Position, Pawn> getInitialPawns() {
        Map<Position, Pawn> pawns = new HashMap<>();
        for (Abscissa value : Abscissa.values()) {
            pawns.put(Position.valueOf(value, Ordinate.TWO), new Pawn(Color.WHITE));
            pawns.put(Position.valueOf(value, Ordinate.SEVEN), new Pawn(Color.BLACK));
        }
        return pawns;
    }

    private static Map<Position, Rook> getInitialRooks() {
        Map<Position, Rook> rooks = new HashMap<>();
        rooks.put(Position.valueOf(Abscissa.a, Ordinate.ONE), new Rook(Color.WHITE));
        rooks.put(Position.valueOf(Abscissa.a, Ordinate.EIGHT), new Rook(Color.BLACK));
        rooks.put(Position.valueOf(Abscissa.h, Ordinate.ONE), new Rook(Color.WHITE));
        rooks.put(Position.valueOf(Abscissa.h, Ordinate.EIGHT), new Rook(Color.BLACK));
        return rooks;
    }

    private static Map<Position, Knight> getInitialKnights() {
        Map<Position, Knight> knight = new HashMap<>();
        knight.put(Position.valueOf(Abscissa.b, Ordinate.ONE), new Knight(Color.WHITE));
        knight.put(Position.valueOf(Abscissa.b, Ordinate.EIGHT), new Knight(Color.BLACK));
        knight.put(Position.valueOf(Abscissa.g, Ordinate.ONE), new Knight(Color.WHITE));
        knight.put(Position.valueOf(Abscissa.g, Ordinate.EIGHT), new Knight(Color.BLACK));
        return knight;
    }

    private static Map<Position, Bishop> getInitialBishops() {
        Map<Position, Bishop> bishops = new HashMap<>();
        bishops.put(Position.valueOf(Abscissa.c, Ordinate.ONE), new Bishop(Color.WHITE));
        bishops.put(Position.valueOf(Abscissa.c, Ordinate.EIGHT), new Bishop(Color.BLACK));
        bishops.put(Position.valueOf(Abscissa.f, Ordinate.ONE), new Bishop(Color.WHITE));
        bishops.put(Position.valueOf(Abscissa.f, Ordinate.EIGHT), new Bishop(Color.BLACK));

        return bishops;
    }

    private static Map<Position, Queen> getInitialQueens() {
        Map<Position, Queen> queen = new HashMap<>();
        queen.put(Position.valueOf(Abscissa.d, Ordinate.ONE), new Queen(Color.WHITE));
        queen.put(Position.valueOf(Abscissa.d, Ordinate.EIGHT), new Queen(Color.BLACK));

        return queen;
    }

    private static Map<Position, King> getInitialKings() {
        Map<Position, King> king =new HashMap<>();
        king.put(Position.valueOf(Abscissa.e, Ordinate.ONE), new King(Color.WHITE));
        king.put(Position.valueOf(Abscissa.e, Ordinate.EIGHT), new King(Color.BLACK));

        return king;
    }
}
