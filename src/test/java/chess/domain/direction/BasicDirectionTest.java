package chess.domain.direction;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.position.Position;

class BasicDirectionTest {

	@ParameterizedTest
	@ValueSource(ints = {3, 2, 1})
	@DisplayName("남쪽 방향인지 확인")
	void south(int row) {
		Position from = new Position(4, 4);
		Position to = new Position(row, 4);
		Direction south = BasicDirection.SOUTH;
		assertThat(south.isValidDirection(from, to)).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {5, 6, 7, 8})
	@DisplayName("동쪽 방향인지 확인")
	void east(int column) {
		Position from = new Position(4, 4);
		Position to = new Position(4, column);
		Direction east = BasicDirection.EAST;
		assertThat(east.isValidDirection(from, to)).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3})
	@DisplayName("서쪽 방향인지 확인")
	void west(int column) {
		Position from = new Position(4, 4);
		Position to = new Position(4, column);
		Direction west = BasicDirection.WEST;
		assertThat(west.isValidDirection(from, to)).isTrue();
	}

	@ParameterizedTest
	@ValueSource(ints = {5, 6, 7, 8})
	@DisplayName("북쪽 방향인지 확인")
	void north(int row) {
		Position from = new Position(4, 4);
		Position to = new Position(row, 4);
		Direction north = BasicDirection.NORTH;
		assertThat(north.isValidDirection(from, to)).isTrue();
	}
}