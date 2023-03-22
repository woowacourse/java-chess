package chess.domain.board;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> initialArrangement = new HashMap<>();
        initialArrangement.putAll(createLine(Rank.ONE, createFirstLine(Color.WHITE)));
        initialArrangement.putAll(createLine(Rank.TWO, createSecondLine(Color.WHITE)));
        initialArrangement.putAll(createLine(Rank.EIGHT, createFirstLine(Color.BLACK)));
        initialArrangement.putAll(createLine(Rank.SEVEN, createSecondLine(Color.BLACK)));
        return new Board(initialArrangement);
    }

    private static Map<File, Piece> createFirstLine(Color color) {
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

    private static Map<File, Piece> createSecondLine(Color color) {
        Map<File, Piece> secondLine = new HashMap<>();
        for (File file : File.values()) {
            secondLine.put(file, new Pawn(color));
        }
        return secondLine;
    }

    private static Map<Position, Piece> createLine(Rank rank, Map<File, Piece> line) {
        Map<Position, Piece> arrangedLine = new HashMap<>();
        for (Entry<File, Piece> fileToPiece : line.entrySet()) {
            File file = fileToPiece.getKey();
            Piece piece = fileToPiece.getValue();
            arrangedLine.put(new Position(file, rank), piece);
        }
        return arrangedLine;
    }
}
