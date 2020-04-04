package chess.domain.piece;

import chess.domain.piece.movable.*;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

import java.util.List;

public enum PieceType {
	KING("K", 0.0, new UnblockedMovable(MovableDirections.EVERY)),
	QUEEN("Q", 9.0, new BlockedMovable(MovableDirections.EVERY)),
	KNIGHT("N", 2.5, new UnblockedMovable(MovableDirections.KNIGHT)),
	ROOK("R", 5.0, new BlockedMovable(MovableDirections.LINEAR)),
	BISHOP("B", 3.0, new BlockedMovable(MovableDirections.DIAGONAL)),
	WHITE_PAWN("p", 1.0, new PawnMovable(MovableDirections.WHITE_PAWN)),
	BLACK_PAWN("P", 1.0, new PawnMovable(MovableDirections.BLACK_PAWN)),
	BLANK(".", 0.0, new UnblockedMovable(MovableDirections.NONE));

	private final String resource;
	private final double score;
	private final Movable movable;

	PieceType(String resource, double score, Movable movable) {
		this.resource = resource;
		this.score = score;
		this.movable = movable;
	}

	public Positions findMovablePositions(Position position, List<Piece> pieces, Color color) {
		return movable.findMovablePositions(position, pieces, color);
	}

	public String getResource() {
		return resource;
	}

	public double getScore() {
		return score;
	}

	public boolean isKing() {
		return this.equals(KING);
	}

	public boolean isPawn() {
		return this.equals(WHITE_PAWN) || this.equals(BLACK_PAWN);
	}
}
