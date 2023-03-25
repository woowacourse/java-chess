package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
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
            put(File.A, new Piece(color, PieceType.ROOK));
            put(File.B, new Piece(color, PieceType.KNIGHT));
            put(File.C, new Piece(color, PieceType.BISHOP));
            put(File.D, new Piece(color, PieceType.QUEEN));
            put(File.E, new Piece(color, PieceType.KING));
            put(File.F, new Piece(color, PieceType.BISHOP));
            put(File.G, new Piece(color, PieceType.KNIGHT));
            put(File.H, new Piece(color, PieceType.ROOK));
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
