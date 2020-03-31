package chess.domain.piece;

import chess.domain.Position;

import java.util.*;

public class PieceFactory {
    private static final int team1BackRank = 1;
    private static final int team1PawnsRank = 2;
    private static final int team2BackRank = 8;
    private static final int team2PawnsRank = 7;
    private static List<String> PawnPositions = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

    private static PieceFactory instance;

    private Map<Position, Piece> pieces = new HashMap<>();

    private PieceFactory() {
        initializeTeam(team1BackRank, team1PawnsRank, Team.WHITE);
        initializeTeam(team2BackRank, team2PawnsRank, Team.BLACK);
    }

    public static PieceFactory getInstance() {
        if (instance == null) {
            instance = new PieceFactory();
        }
        return instance;
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    private void initializeTeam(int backRank, int pawnRank, Team team) {
        pieces.put(new Position("e" + backRank), new King(new Position("e" + backRank), team));
        pieces.put(new Position("d" + backRank), new Queen(new Position("d" + backRank), team));
        pieces.put(new Position("c" + backRank), new Bishop(new Position("c" + backRank), team));
        pieces.put(new Position("f" + backRank), new Bishop(new Position("f" + backRank), team));
        pieces.put(new Position("b" + backRank), new Knight(new Position("b" + backRank), team));
        pieces.put(new Position("g" + backRank), new Knight(new Position("g" + backRank), team));
        pieces.put(new Position("a" + backRank), new Rook(new Position("a" + backRank), team));
        pieces.put(new Position("h" + backRank), new Rook(new Position("h" + backRank), team));
        for (String pawnPosition : PawnPositions) {
            pieces.put(new Position(pawnPosition + pawnRank), new Pawn(new Position(pawnPosition + pawnRank), team));
        }
    }
}
