package chess.domain.board;

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
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

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
        for (File value : File.values()) {
            pieces.put(Position.valueOf(value, Rank.TWO), new Pawn(Color.WHITE));
            pieces.put(Position.valueOf(value, Rank.SEVEN), new Pawn(Color.BLACK));
        }
    }

    private static void placeRooks(Map<Position, Piece> pieces) {
        List<File> rookFiles = List.of(File.A, File.H);
        for (File file : rookFiles) {
            pieces.put(Position.valueOf(file, Rank.ONE), new Rook(Color.WHITE));
            pieces.put(Position.valueOf(file, Rank.EIGHT), new Rook(Color.BLACK));
        }
    }

    private static void placeKnights(Map<Position, Piece> pieces) {
        List<File> knightFiles = List.of(File.B, File.G);
        for (File file : knightFiles) {
            pieces.put(Position.valueOf(file, Rank.ONE), new Knight(Color.WHITE));
            pieces.put(Position.valueOf(file, Rank.EIGHT), new Knight(Color.BLACK));
        }
    }

    private static void placeBishops(Map<Position, Piece> pieces) {
        List<File> bishopFiles = List.of(File.C, File.F);
        for (File file : bishopFiles) {
            pieces.put(Position.valueOf(file, Rank.ONE), new Bishop(Color.WHITE));
            pieces.put(Position.valueOf(file, Rank.EIGHT), new Bishop(Color.BLACK));
        }
    }

    private static void placeQueens(Map<Position, Piece> pieces) {
        pieces.put(Position.valueOf(File.D, Rank.ONE), new Queen(Color.WHITE));
        pieces.put(Position.valueOf(File.D, Rank.EIGHT), new Queen(Color.BLACK));
    }

    private static void placeKings(Map<Position, Piece> pieces) {
        pieces.put(Position.valueOf(File.E, Rank.ONE), new King(Color.WHITE));
        pieces.put(Position.valueOf(File.E, Rank.EIGHT), new King(Color.BLACK));
    }
}
