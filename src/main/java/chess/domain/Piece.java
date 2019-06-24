package chess.domain;

import chess.domain.moverule.Empty;
import chess.domain.moverule.King;
import chess.domain.moverule.Pawn;

import java.util.Arrays;
import java.util.Objects;

public class Piece {
	private static final Piece EMPTY = new Piece(Color.EMPTY, Empty.getInstance());

	private final Color color;
	private final MoveRule moveRule;

	public enum Color {
		WHITE(1, "WHITE"),
		BLACK(2, "BLACK"),
		EMPTY(3, "EMPTY");

		private final int index;
		private final String name;

		Color(final int index, final String name) {
			this.index = index;
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}

	private Piece(final Color color, final MoveRule moveRule) {
		this.color = color;
		this.moveRule = moveRule;
	}

	public static Piece of(final Color color, final MoveRule moveRule) {
		return new Piece(color, moveRule);
	}

	public static Piece empty() {
		return EMPTY;
	}

	public boolean isSameColor(final Color other) {
		return color == other;
	}

	public boolean isSameName(final String name) {
		return moveRule.getName().equals(name);
	}

	public boolean isSameTeam(final Piece other) {
		return this.color == other.color;
	}

	public boolean isEmpty() {
		return Color.EMPTY == color;
	}

	public boolean isPawn() {
		return Arrays.asList(Pawn.values()).contains(this.moveRule);
	}

	public boolean isValidMove(final Position origin, final Position target) {
		return moveRule.isValidMove(origin, target);
	}

	public boolean isValidAttack(final Position origin, final Position target) {
		return moveRule.isValidAttack(origin, target);
	}

	public Piece orElseFirstPawn() {
		if (this.moveRule == Pawn.FIRST_BOTTOM) {
			return Piece.of(this.color, Pawn.SECOND_BOTTOM);
		}
		if (this.moveRule == Pawn.FIRST_TOP) {
			return Piece.of(this.color, Pawn.SECOND_TOP);
		}
		return Piece.of(this.color, this.moveRule);
	}

	public double getScore() {
		return moveRule.getScore();
	}

	public boolean isKing() {
		return this.moveRule == King.getInstance();
	}

	public String getSymbol() {
		return PieceSymbol.getSymbol(this);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Piece piece = (Piece) o;
		return color == piece.color &&
				Objects.equals(moveRule, piece.moveRule);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, moveRule);
	}

	@Override
	public String toString() {
		return "Piece{" +
				"color=" + color +
				", moveRule=" + moveRule +
				'}';
	}
}
