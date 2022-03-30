package chess.domain.direction;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;

class KnightDirectionTest {

	@Test
	@DisplayName("북북동쪽 방향인지 확인")
	void north_north_east() {
		Position from = new Position(4, 4);
		Position to = new Position(6, 5);
		Direction northNorthEast = KnightDirection.NORTH_NORTH_EAST;
		assertThat(northNorthEast.isValidDirection(from, to)).isTrue();
	}

	@Test
	@DisplayName("동북동쪽 방향인지 확인")
	void east_north_east() {
		Position from = new Position(4, 4);
		Position to = new Position(5, 6);
		Direction eastNorthEast = KnightDirection.EAST_NORTH_EAST;
		assertThat(eastNorthEast.isValidDirection(from, to)).isTrue();
	}

	@Test
	@DisplayName("동남동쪽 방향인지 확인")
	void east_south_east() {
		Position from = new Position(4, 4);
		Position to = new Position(3, 6);
		Direction eastSouthEast = KnightDirection.EAST_SOUTH_EAST;
		assertThat(eastSouthEast.isValidDirection(from, to)).isTrue();
	}

	@Test
	@DisplayName("남남동쪽 방향인지 확인")
	void south_south_east() {
		Position from = new Position(4, 4);
		Position to = new Position(2, 5);
		Direction southSouthEast = KnightDirection.SOUTH_SOUTH_EAST;
		assertThat(southSouthEast.isValidDirection(from, to)).isTrue();
	}

	@Test
	@DisplayName("남남서쪽 방향인지 확인")
	void south_south_west() {
		Position from = new Position(4, 4);
		Position to = new Position(2, 3);
		Direction southSouthWest = KnightDirection.SOUTH_SOUTH_WEST;
		assertThat(southSouthWest.isValidDirection(from, to)).isTrue();
	}

	@Test
	@DisplayName("서남서쪽 방향인지 확인")
	void west_south_west() {
		Position from = new Position(4, 4);
		Position to = new Position(3, 2);
		Direction westSouthWest = KnightDirection.WEST_SOUTH_WEST;
		assertThat(westSouthWest.isValidDirection(from, to)).isTrue();
	}

	@Test
	@DisplayName("서북서쪽 방향인지 확인")
	void west_north_west() {
		Position from = new Position(4, 4);
		Position to = new Position(5, 2);
		Direction westNorthWest = KnightDirection.WEST_NORTH_WEST;
		assertThat(westNorthWest.isValidDirection(from, to)).isTrue();
	}

	@Test
	@DisplayName("북북서쪽 방향인지 확인")
	void north_north_west() {
		Position from = new Position(4, 4);
		Position to = new Position(6, 3);
		Direction northNorthWest = KnightDirection.NORTH_NORTH_WEST;
		assertThat(northNorthWest.isValidDirection(from, to)).isTrue();
	}
}