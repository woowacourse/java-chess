package chess.domain.piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.domain.Pieces;
import chess.domain.Position;

public class PieceFactory {
	private static PieceFactory instance;
	private static List<String> team1PawnPositions = Arrays.asList("a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2");
	private static List<String> team2PawnPositions = Arrays.asList("a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7");

	private List<Piece> pieces = new ArrayList<>();

	private PieceFactory() {
		initializeTeam1(Team.WHITE);
		initializeTeam2(Team.BLACK);
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

	private void initializeTeam1(Team team) {
		pieces.add(new King(new Position("e1"), team));
		pieces.add(new Queen(new Position("d1"), team));
		pieces.add(new Bishop(new Position("c1"), team));
		pieces.add(new Bishop(new Position("f1"), team));
		pieces.add(new Knight(new Position("b1"), team));
		pieces.add(new Knight(new Position("g1"), team));
		pieces.add(new Rook(new Position("a1"), team));
		pieces.add(new Rook(new Position("h1"), team));
		for (String pawnPosition : team1PawnPositions) {
			pieces.add(new Pawn(new Position(pawnPosition), team));
		}
	}

	private void initializeTeam2(Team team) {
		pieces.add(new King(new Position("e8"), team));
		pieces.add(new Queen(new Position("d8"), team));
		pieces.add(new Bishop(new Position("c8"), team));
		pieces.add(new Bishop(new Position("f8"), team));
		pieces.add(new Knight(new Position("b8"), team));
		pieces.add(new Knight(new Position("g8"), team));
		pieces.add(new Rook(new Position("a8"), team));
		pieces.add(new Rook(new Position("h8"), team));
		for (String pawnPosition : team2PawnPositions) {
			pieces.add(new Pawn(new Position(pawnPosition), team));
		}
	}
}
