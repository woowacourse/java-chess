package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class BoardFactory {
    private BoardFactory() {
    }

    public static Board createBoard() {
        Map<Square, Piece> initialArrangement = new TreeMap<>(
                Comparator.comparing(Square::rank).reversed()
                        .thenComparing(Square::file)
        );
        initialArrangement.putAll(createLine(Rank.ONE, createPieceLine(Color.WHITE)));
        initialArrangement.putAll(createLine(Rank.TWO, createPawnLine(Color.WHITE)));
        initialArrangement.putAll(createLine(Rank.THREE, createEmptyLine()));
        initialArrangement.putAll(createLine(Rank.FOUR, createEmptyLine()));
        initialArrangement.putAll(createLine(Rank.FIVE, createEmptyLine()));
        initialArrangement.putAll(createLine(Rank.SIX, createEmptyLine()));
        initialArrangement.putAll(createLine(Rank.SEVEN, createPawnLine(Color.BLACK)));
        initialArrangement.putAll(createLine(Rank.EIGHT, createPieceLine(Color.BLACK)));
        return new Board(initialArrangement);
    }

    private static Map<Square, Piece> createLine(final Rank rank, final Map<File, Piece> line) {
        Map<Square, Piece> arrangedLine = new HashMap<>();
        for (Entry<File, Piece> fileToPiece : line.entrySet()) {
            File file = fileToPiece.getKey();
            Piece piece = fileToPiece.getValue();
            arrangedLine.put(new Square(file, rank), piece);
        }
        return arrangedLine;
    }

    private static Map<File, Piece> createPieceLine(final Color color) {
        return new HashMap<>() {{
            put(File.A, new Rook(color));
            put(File.B, new Knight(color));
            put(File.C, new Bishop(color));
            put(File.D, new Queen(color));
            put(File.E, new King(color));
            put(File.F, new Bishop(color));
            put(File.G, new Knight(color));
            put(File.H, new Rook(color));
        }};
    }

    private static Map<File, Piece> createPawnLine(final Color color) {
        Map<File, Piece> secondLine = new HashMap<>();
        for (File file : File.values()) {
            secondLine.put(file, new Pawn(color));
        }
        return secondLine;
    }

    private static Map<File, Piece> createEmptyLine() {
        Map<File, Piece> emptyPieces = new HashMap<>();
        for (File file : File.values()) {
            emptyPieces.put(file, new EmptyPiece());
        }
        return emptyPieces;
    }
}
