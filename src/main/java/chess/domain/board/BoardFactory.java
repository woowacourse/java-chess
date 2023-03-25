package chess.domain.board;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import chess.domain.game.Team;
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

public class BoardFactory {

    public static Board createBoard() {
        Map<Position, Piece> initialArrangement = new HashMap<>();
        initialArrangement.putAll(createLine(Rank.ONE, createFirstLine(Team.WHITE)));
        initialArrangement.putAll(createLine(Rank.TWO, createSecondLine(Team.WHITE)));
        initialArrangement.putAll(createLine(Rank.EIGHT, createFirstLine(Team.BLACK)));
        initialArrangement.putAll(createLine(Rank.SEVEN, createSecondLine(Team.BLACK)));
        return new Board(initialArrangement);
    }

    private static Map<File, Piece> createFirstLine(Team team) {
        return new HashMap<>() {{
            put(File.A, new Rook(team));
            put(File.B, new Knight(team));
            put(File.C, new Bishop(team));
            put(File.D, new Queen(team));
            put(File.E, new King(team));
            put(File.F, new Bishop(team));
            put(File.G, new Knight(team));
            put(File.H, new Rook(team));
        }};
    }

    private static Map<File, Piece> createSecondLine(Team team) {
        Map<File, Piece> secondLine = new HashMap<>();
        for (File file : File.values()) {
            secondLine.put(file, new Pawn(team));
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
