package chess.domain.piece.strategy;

import chess.domain.Position;
import chess.domain.direction.BasicDirection;
import chess.domain.direction.DiagonalDirection;
import chess.domain.direction.Direction;
import chess.domain.direction.DirectionGenerator;

public class BlackPawnMovingStrategy implements MovingStrategy {

	private static int BLACK_PAWN_INITIAL_ROW = 7;

	@Override
	public boolean check(Position from, Position to) {
		return true;
	}

	// private void validatePosition(int row, int column) {
	// 	Set<Position> positions = generateMovablePosition();
	// 	if (!positions.contains(new Position(row, column))) {
	// 		throw new IllegalArgumentException();
	// 	}
	// }
	//
	// private Set<Position> generateMovablePosition() {
	// 	if (this.color == Color.BLACK) {
	// 		return generateBlackMovablePosition();
	// 	}
	// 	return generateWhiteMovablePosition();
	// }
	//
	// private Set<Position> generateBlackMovablePosition() {
	// 	Set<Position> positions = new HashSet<>(List.of(
	// 		this.position.change(row -> row - 1, column -> column),
	// 		this.position.change(row -> row - 1, column -> column - 1),
	// 		this.position.change(row -> row - 1, column -> column + 1)
	// 	));
	//
	// 	if (!this.position.isDifferentRow(BLACK_PAWN_INITIAL_ROW)) {
	// 		positions.add(this.position.change(row -> row - 2, column -> column));
	// 	}
	// 	return positions;
	// }
	//
	// private Set<Position> generateWhiteMovablePosition() {
	// 	Set<Position> positions = new HashSet<>(List.of(
	// 		this.position.change(row -> row + 1, column -> column),
	// 		this.position.change(row -> row + 1, column -> column - 1),
	// 		this.position.change(row -> row + 1, column -> column + 1)
	// 	));
	//
	// 	if (!this.position.isDifferentRow(WHITE_PAWN_INITIAL_ROW)) {
	// 		positions.add(this.position.change(row -> row + 2, column -> column));
	// 	}
	// 	return positions;
	// }
}
