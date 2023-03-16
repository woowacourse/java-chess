package chess.domain.board;

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
import java.util.Map.Entry;

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> initialArrangement = new HashMap<>();
        initialArrangement.putAll(createLine(Rank.ONE, createFirstLine(true)));
        initialArrangement.putAll(createLine(Rank.TWO, createSecondLine(true)));
        initialArrangement.putAll(createLine(Rank.EIGHT, createFirstLine(false)));
        initialArrangement.putAll(createLine(Rank.SEVEN, createSecondLine(false)));
        return new Board(initialArrangement);
    }

    private static Map<File, Piece> createFirstLine(boolean isWhite) {
        return new HashMap<>() {{
            put(File.A, Rook.from(isWhite));
            put(File.B, Knight.from(isWhite));
            put(File.C, Bishop.from(isWhite));
            put(File.D, Queen.from(isWhite));
            put(File.E, King.from(isWhite));
            put(File.F, Bishop.from(isWhite));
            put(File.G, Knight.from(isWhite));
            put(File.H, Rook.from(isWhite));
        }};
    }

    private static Map<File, Piece> createSecondLine(boolean isWhite) {
        Map<File, Piece> secondLine = new HashMap<>();
        for (File file : File.values()) {
            secondLine.put(file, Pawn.from(isWhite));
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
