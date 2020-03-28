package chess.domain.piece;

import java.util.*;

import chess.domain.Pieces;
import chess.domain.Position;

public class PieceFactory {
	private static final int team1BackRank = 1;
	private static final int team1PawnsRank = 2;
	private static final int team2BackRank = 8;
	private static final int team2PawnsRank = 7;
	private static List<String> PawnPositions = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h");

	private static PieceFactory instance;

	private List<Piece> pieces = new ArrayList<>();

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

	public static Pieces getPieces() {
		return new Pieces(getInstance().pieces);
	}

	private void initializeTeam(int backRank, int pawnRank, Team team) {
		pieces.add(new King(new Position("e"+backRank), team));
		pieces.add(new Queen(new Position("d"+backRank), team));
		pieces.add(new Bishop(new Position("c"+backRank), team));
		pieces.add(new Bishop(new Position("f"+backRank), team));
		pieces.add(new Knight(new Position("b"+backRank), team));
		pieces.add(new Knight(new Position("g"+backRank), team));
		pieces.add(new Rook(new Position("a"+backRank), team));
		pieces.add(new Rook(new Position("h"+backRank), team));
		for (String pawnPosition : PawnPositions) {
			pieces.add(new Pawn(new Position(pawnPosition+pawnRank), team));
		}
	}
}
