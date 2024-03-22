package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
            pawns.put(Position.from(file, Rank.TWO), Pawn.ofWhite());
            pawns.put(Position.from(file, Rank.SEVEN), Pawn.ofBlack());
        }

        return pawns;
    }

    private static Map<Position, Rook> createInitialRooks() {
        Map<Position, Rook> rooks = new HashMap<>();
        rooks.put(Position.from(File.A, Rank.ONE), Rook.WHITE);
        rooks.put(Position.from(File.H, Rank.ONE), Rook.WHITE);
        rooks.put(Position.from(File.A, Rank.EIGHT), Rook.BLACK);
        rooks.put(Position.from(File.H, Rank.EIGHT), Rook.BLACK);

        return rooks;
    }

    private static Map<Position, Knight> createInitialKnights() {
        Map<Position, Knight> knights = new HashMap<>();
        knights.put(Position.from(File.B, Rank.ONE), Knight.WHITE);
        knights.put(Position.from(File.G, Rank.ONE), Knight.WHITE);
        knights.put(Position.from(File.B, Rank.EIGHT), Knight.BLACK);
        knights.put(Position.from(File.G, Rank.EIGHT), Knight.BLACK);

        return knights;
    }

    private static Map<Position, Bishop> createInitialBishops() {
        Map<Position, Bishop> bishops = new HashMap<>();
        bishops.put(Position.from(File.C, Rank.ONE), Bishop.WHITE);
        bishops.put(Position.from(File.F, Rank.ONE), Bishop.WHITE);
        bishops.put(Position.from(File.C, Rank.EIGHT), Bishop.BLACK);
        bishops.put(Position.from(File.F, Rank.EIGHT), Bishop.BLACK);

        return bishops;
    }

    private static Map<Position, Queen> createInitialQueens() {
        Map<Position, Queen> queens = new HashMap<>();
        queens.put(Position.from(File.D, Rank.ONE), Queen.WHITE);
        queens.put(Position.from(File.D, Rank.EIGHT), Queen.BLACK);

        return queens;
    }

    private static Map<Position, King> createInitialKings() {
        Map<Position, King> kings = new HashMap<>();
        kings.put(Position.from(File.E, Rank.ONE), King.WHITE);
        kings.put(Position.from(File.E, Rank.EIGHT), King.BLACK);

        return kings;
    }
}
