package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    private BoardFactory() {

    }

    public static Board startGame() {
        Map<Position, Piece> map = new HashMap<>();
        initializePawn(map);
        initializeKnight(map);
        initializeBishop(map);
        initializeRook(map);
        initializeQueen(map);
        initializeKing(map);

        return new Board(map);
    }

    private static void initializePawn(Map<Position, Piece> map) {
        for (File file : File.values()) {
            map.put(new Position(file, Rank.TWO), new Pawn(Team.WHITE));
        }
        for (File file : File.values()) {
            map.put(new Position(file, Rank.SEVEN), new Pawn(Team.BLACK));
        }
    }

    private static void initializeKnight(Map<Position, Piece> map) {
        map.put(new Position(File.B, Rank.ONE), new Knight(Team.WHITE));
        map.put(new Position(File.G, Rank.ONE), new Knight(Team.WHITE));
        map.put(new Position(File.B, Rank.EIGHT), new Knight(Team.BLACK));
        map.put(new Position(File.G, Rank.EIGHT), new Knight(Team.BLACK));
    }

    private static void initializeBishop(Map<Position, Piece> map) {
        map.put(new Position(File.C, Rank.ONE), new Bishop(Team.WHITE));
        map.put(new Position(File.F, Rank.ONE), new Bishop(Team.WHITE));
        map.put(new Position(File.C, Rank.EIGHT), new Bishop(Team.BLACK));
        map.put(new Position(File.F, Rank.EIGHT), new Bishop(Team.BLACK));
    }

    private static void initializeRook(Map<Position, Piece> map) {
        map.put(new Position(File.A, Rank.ONE), new Rook(Team.WHITE));
        map.put(new Position(File.H, Rank.ONE), new Rook(Team.WHITE));
        map.put(new Position(File.A, Rank.EIGHT), new Rook(Team.BLACK));
        map.put(new Position(File.H, Rank.EIGHT), new Rook(Team.BLACK));
    }

    private static void initializeQueen(Map<Position, Piece> map) {
        map.put(new Position(File.D, Rank.ONE), new Queen(Team.WHITE));
        map.put(new Position(File.D, Rank.EIGHT), new Queen(Team.BLACK));
    }

    private static void initializeKing(Map<Position, Piece> map) {
        map.put(new Position(File.E, Rank.ONE), new King(Team.WHITE));
        map.put(new Position(File.E, Rank.EIGHT), new King(Team.BLACK));
    }
}
