package chess.domain.piece;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import chess.domain.Color;
import chess.domain.Position;

public class Pawn extends Piece {

	private static final int BLACK_PAWN_INITIAL_ROW = 7;
	private static final int WHITE_PAWN_INITIAL_ROW = 2;

	private final String symbol;

	public Pawn(Color color, Position position, String symbol) {
		super(color, position);
		this.symbol = symbol;
	}

	public static Pawn createWhite(int row, int column) {
		return new Pawn(Color.WHITE, new Position(row, column), "♟");
	}

	public static Pawn createBlack(int row, int column) {
		return new Pawn(Color.BLACK, new Position(row, column), "♙");
	}

	@Override
	public String getSymbol() {
		return this.symbol;
	}

	public void move(int row, int column) {
		validatePosition(row, column);
		this.position = this.position.change(row, column);
	}

	private void validatePosition(int row, int column) {
		Set<Position> positions = generateMovablePosition();
		if (!positions.contains(new Position(row, column))) {
			throw new IllegalArgumentException();
		}
	}

	private Set<Position> generateMovablePosition() {
		if (this.color == Color.BLACK) {
			return generateBlackMovablePosition();
		}
		return generateWhiteMovablePosition();
	}

	private Set<Position> generateBlackMovablePosition() {
		Set<Position> positions = new HashSet<>(List.of(
			this.position.change(row -> row - 1, column -> column),
			this.position.change(row -> row - 1, column -> column - 1),
			this.position.change(row -> row - 1, column -> column + 1)
		));

		if (!this.position.isDifferentRow(BLACK_PAWN_INITIAL_ROW)) {
			positions.add(this.position.change(row -> row - 2, column -> column));
		}
		return positions;
	}

	private Set<Position> generateWhiteMovablePosition() {
		Set<Position> positions = new HashSet<>(List.of(
			this.position.change(row -> row + 1, column -> column),
			this.position.change(row -> row + 1, column -> column - 1),
			this.position.change(row -> row + 1, column -> column + 1)
		));

		if (!this.position.isDifferentRow(WHITE_PAWN_INITIAL_ROW)) {
			positions.add(this.position.change(row -> row + 2, column -> column));
		}
		return positions;
	}
}
