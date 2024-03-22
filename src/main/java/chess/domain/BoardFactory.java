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
import java.util.List;
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
            pawns.put(Position.of(file, Rank.TWO), Pawn.WHITE);
            pawns.put(Position.of(file, Rank.SEVEN), Pawn.BLACK);
        }

        return pawns;
    }

    private static <T> Map<Position, T> createSpecialPieces(List<File> files, T whitePiece, T blackPiece) {
        Map<Position, T> specialPieces = new HashMap<>();
        for (File file : files) {
            specialPieces.put(Position.of(file, Rank.ONE), whitePiece);
            specialPieces.put(Position.of(file, Rank.EIGHT), blackPiece);
        }

        return specialPieces;
    }

    private static Map<Position, Rook> createInitialRooks() {
        return createSpecialPieces(List.of(File.A, File.H), Rook.WHITE, Rook.BLACK);
    }

    private static Map<Position, Knight> createInitialKnights() {
        return createSpecialPieces(List.of(File.B, File.G), Knight.WHITE, Knight.BLACK);
    }

    private static Map<Position, Bishop> createInitialBishops() {
        return createSpecialPieces(List.of(File.C, File.F), Bishop.WHITE, Bishop.BLACK);
    }

    private static Map<Position, Queen> createInitialQueens() {
        return createSpecialPieces(List.of(File.D), Queen.WHITE, Queen.BLACK);
    }

    private static Map<Position, King> createInitialKings() {
        return createSpecialPieces(List.of(File.E), King.WHITE, King.BLACK);
    }
}
