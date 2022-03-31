package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private static final Map<Position, Piece> EMPTY_MAP = Collections.emptyMap();

    public static Board getEmptyBoard() {
        return new Board(EMPTY_MAP);
    }

    public static Board getInitialPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieces.putAll(getInitialBishops());
        pieces.putAll(getInitialKings());
        pieces.putAll(getInitialKnights());
        pieces.putAll(getInitialPawns());
        pieces.putAll(getInitialRooks());
        pieces.putAll(getInitialQueens());
        return new Board(pieces);
    }

    private static Map<Position, Pawn> getInitialPawns() {
        Map<Position, Pawn> pawns = new HashMap<>();
        for (File value : File.values()) {
            pawns.put(Position.valueOf(value, Rank.TWO), new Pawn(Color.WHITE));
            pawns.put(Position.valueOf(value, Rank.SEVEN), new Pawn(Color.BLACK));
        }
        return pawns;
    }

    private static Map<Position, Rook> getInitialRooks() {
        Map<Position, Rook> rooks = new HashMap<>();
        rooks.put(Position.valueOf(File.A, Rank.ONE), new Rook(Color.WHITE));
        rooks.put(Position.valueOf(File.A, Rank.EIGHT), new Rook(Color.BLACK));
        rooks.put(Position.valueOf(File.H, Rank.ONE), new Rook(Color.WHITE));
        rooks.put(Position.valueOf(File.H, Rank.EIGHT), new Rook(Color.BLACK));
        return rooks;
    }

    private static Map<Position, Knight> getInitialKnights() {
        Map<Position, Knight> knight = new HashMap<>();
        knight.put(Position.valueOf(File.B, Rank.ONE), new Knight(Color.WHITE));
        knight.put(Position.valueOf(File.B, Rank.EIGHT), new Knight(Color.BLACK));
        knight.put(Position.valueOf(File.G, Rank.ONE), new Knight(Color.WHITE));
        knight.put(Position.valueOf(File.G, Rank.EIGHT), new Knight(Color.BLACK));
        return knight;
    }

    private static Map<Position, Bishop> getInitialBishops() {
        Map<Position, Bishop> bishops = new HashMap<>();
        bishops.put(Position.valueOf(File.C, Rank.ONE), new Bishop(Color.WHITE));
        bishops.put(Position.valueOf(File.C, Rank.EIGHT), new Bishop(Color.BLACK));
        bishops.put(Position.valueOf(File.F, Rank.ONE), new Bishop(Color.WHITE));
        bishops.put(Position.valueOf(File.F, Rank.EIGHT), new Bishop(Color.BLACK));

        return bishops;
    }

    private static Map<Position, Queen> getInitialQueens() {
        Map<Position, Queen> queen = new HashMap<>();
        queen.put(Position.valueOf(File.D, Rank.ONE), new Queen(Color.WHITE));
        queen.put(Position.valueOf(File.D, Rank.EIGHT), new Queen(Color.BLACK));

        return queen;
    }

    private static Map<Position, King> getInitialKings() {
        Map<Position, King> king = new HashMap<>();
        king.put(Position.valueOf(File.E, Rank.ONE), new King(Color.WHITE));
        king.put(Position.valueOf(File.E, Rank.EIGHT), new King(Color.BLACK));

        return king;
    }
}
