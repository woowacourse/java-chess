package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {
    private BoardFactory() {
    }

    public static Board createInitialBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(createInitialPawns());
        pieces.putAll(createInitialRooks());
        pieces.putAll(createInitialKnights());
        pieces.putAll(createInitialBishops());
        pieces.putAll(createInitialKings());
        pieces.putAll(createInitialQueens());

        return new Board(pieces);
    }

    private static Map<Position, Pawn> createInitialPawns() {
        Map<Position, Pawn> pawns = new HashMap<>();
        for (File file : File.values()) {
            pawns.put(new Position(file, Rank.TWO), new Pawn(Color.BLACK));
            pawns.put(new Position(file, Rank.SEVEN), new Pawn(Color.WHITE));
        }

        return pawns;
    }

    private static Map<Position, Rook> createInitialRooks() {
        Map<Position, Rook> rooks = new HashMap<>();
        rooks.put(new Position(File.a, Rank.ONE), new Rook(Color.BLACK));
        rooks.put(new Position(File.h, Rank.ONE), new Rook(Color.BLACK));
        rooks.put(new Position(File.a, Rank.EIGHT), new Rook(Color.WHITE));
        rooks.put(new Position(File.h, Rank.EIGHT), new Rook(Color.WHITE));

        return rooks;
    }

    private static Map<Position, Knight> createInitialKnights() {
        Map<Position, Knight> knights = new HashMap<>();
        knights.put(new Position(File.b, Rank.ONE), new Knight(Color.BLACK));
        knights.put(new Position(File.g, Rank.ONE), new Knight(Color.BLACK));
        knights.put(new Position(File.b, Rank.EIGHT), new Knight(Color.WHITE));
        knights.put(new Position(File.g, Rank.EIGHT), new Knight(Color.WHITE));

        return knights;
    }

    private static Map<Position, Bishop> createInitialBishops() {
        Map<Position, Bishop> bishops = new HashMap<>();
        bishops.put(new Position(File.c, Rank.ONE), new Bishop(Color.BLACK));
        bishops.put(new Position(File.f, Rank.ONE), new Bishop(Color.BLACK));
        bishops.put(new Position(File.c, Rank.EIGHT), new Bishop(Color.WHITE));
        bishops.put(new Position(File.f, Rank.EIGHT), new Bishop(Color.WHITE));

        return bishops;
    }

    private static Map<Position, Queen> createInitialQueens() {
        Map<Position, Queen> queens = new HashMap<>();
        queens.put(new Position(File.d, Rank.ONE), new Queen(Color.BLACK));
        queens.put(new Position(File.d, Rank.EIGHT), new Queen(Color.WHITE));

        return queens;
    }

    private static Map<Position, King> createInitialKings() {
        Map<Position, King> kings = new HashMap<>();
        kings.put(new Position(File.e, Rank.ONE), new King(Color.BLACK));
        kings.put(new Position(File.e, Rank.EIGHT), new King(Color.WHITE));

        return kings;
    }
}
