package chess.domain.direction;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.Position;

class DiagonalDirectionTest {

	@ParameterizedTest
	@CsvSource(value = {"5:5", "6:6", "7:7", "8:8"}, delimiter = ':')
	@DisplayName("북동쪽 방향인지 확인")
	void north_east(int row, int column) {
		Position from = new Position(4, 4);
		Position to = new Position(row, column);
		Direction northEast = DiagonalDirection.NORTH_EAST;
		assertThat(northEast.isValidDirection(from, to)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"5:3", "6:2", "7:1"}, delimiter = ':')
	@DisplayName("북서쪽 방향인지 확인")
	void north_west(int row, int column) {
		Position from = new Position(4, 4);
		Position to = new Position(row, column);
		Direction northWest = DiagonalDirection.NORTH_WEST;
		assertThat(northWest.isValidDirection(from, to)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"3:5", "2:6", "1:7"}, delimiter = ':')
	@DisplayName("남동쪽 방향인지 확인")
	void south_east(int row, int column) {
		Position from = new Position(4, 4);
		Position to = new Position(row, column);
		Direction southEast = DiagonalDirection.SOUTH_EAST;
		assertThat(southEast.isValidDirection(from, to)).isTrue();
	}

	@ParameterizedTest
	@CsvSource(value = {"3:3", "2:2", "1:1"}, delimiter = ':')
	@DisplayName("남서쪽 방향인지 확인")
	void south_west(int row, int column) {
		Position from = new Position(4, 4);
		Position to = new Position(row, column);
		Direction southWest = DiagonalDirection.SOUTH_WEST;
		assertThat(southWest.isValidDirection(from, to)).isTrue();
	}
}