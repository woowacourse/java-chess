package chess.domain.piece;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.Position;

public class PieceFactory {
    private static List<String> team1PawnPositions = Arrays.asList("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2");
    private static List<String> team2PawnPositions = Arrays.asList("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7");

    private Map<Position, Piece> pieces = new HashMap<>();

    private PieceFactory() {
        initializeTeam1(Team.WHITE);
        initializeTeam2(Team.BLACK);
    }

    public static PieceFactory create() {
        return new PieceFactory();
    }

    public Map<Position, Piece> getPieces() {
        return pieces;
    }

    private void initializeTeam1(Team team) {
        pieces.put(new Position("e1"), new King(new Position("e1"), team));
        pieces.put(new Position("d1"), new Queen(new Position("d1"), team));
        pieces.put(new Position("c1"), new Bishop(new Position("c1"), team));
        pieces.put(new Position("f1"), new Bishop(new Position("f1"), team));
        pieces.put(new Position("b1"), new Knight(new Position("b1"), team));
        pieces.put(new Position("g1"), new Knight(new Position("g1"), team));
        pieces.put(new Position("a1"), new Rook(new Position("a1"), team));
        pieces.put(new Position("h1"), new Rook(new Position("h1"), team));
        for (String pawnPosition : team1PawnPositions) {
            pieces.put(new Position(pawnPosition), new Pawn(new Position(pawnPosition), team));
        }
    }

    private void initializeTeam2(Team team) {
        pieces.put(new Position("e8"), new King(new Position("e8"), team));
        pieces.put(new Position("d8"), new Queen(new Position("d8"), team));
        pieces.put(new Position("c8"), new Bishop(new Position("c8"), team));
        pieces.put(new Position("f8"), new Bishop(new Position("f8"), team));
        pieces.put(new Position("b8"), new Knight(new Position("b8"), team));
        pieces.put(new Position("g8"), new Knight(new Position("g8"), team));
        pieces.put(new Position("a8"), new Rook(new Position("a8"), team));
        pieces.put(new Position("h8"), new Rook(new Position("h8"), team));
        for (String pawnPosition : team2PawnPositions) {
            pieces.put(new Position(pawnPosition), new Pawn(new Position(pawnPosition), team));
        }
    }
}
