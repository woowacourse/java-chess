package chess.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.team.Team;

class LocationTest {

	@DisplayName("로케이션이 목적지와 같은 컬럼에 있는지 확인")
	@Test
	void isSameCol() {
		Location starting = new Location(1, 'c');
		Location destination = new Location(7, 'c');

		assertThat(starting.isSameCol(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 대각선에 있는지 확인")
	@Test
	void isDiagonal() {
		Location starting = new Location(1, 'c');
		Location destination = new Location(2, 'b');

		assertThat(starting.isDiagonal(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 킹의 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,b", "4,c", "4,d", "3,d", "2,d", "2,c", "2,b", "3,b"})
	void isKingRange(int row, char column) {
		Location starting = new Location(3, 'c');
		Location destination = new Location(row, column);

		assertThat(starting.isKingRange(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 나이트의 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"5,d", "5,b", "4,e", "4,a", "2,a", "2,e", "1,b", "1,d"})
	void isKnightRange(int row, char column) {
		Location starting = new Location(3, 'c');
		Location destination = new Location(row, column);

		assertThat(starting.isKnightRange(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 퀸의 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,b", "4,c", "4,d", "3,d", "2,d", "2,c", "2,b", "3,b"})
	void isQueenRang(int row, char column) {
		Location starting = new Location(3, 'c');
		Location destination = new Location(row, column);

		assertThat(starting.isQueenRange(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 가로, 세로 직선범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c", "3,d", "2,c", "3,b"})
	void isStraight(int row, char column) {
		Location starting = new Location(3, 'c');
		Location destination = new Location(row, column);

		assertThat(starting.isStraight(destination)).isTrue();
	}

	@DisplayName("해당 로케이션이 폰의 초기위치인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"2,a,false", "2,b,false", "2,c,false", "2,d,false", "2,e,false", "2,f,false", "2,g,false",
		"2,h,false", "7,a,true", "7,b,true", "7,c,true", "7,d,true", "7,e,true", "7,f,true", "7,g,true", "7,h,true"})
	void isInitialPawnLocation(int row, char col, boolean black) {
		Location starting = new Location(row, col);
		assertThat(starting.isInitialPawnLocation(black)).isTrue();
	}

	@DisplayName("로케이션이 목적지가 폰의 초기 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c,WHITE", "5,c,WHITE", "2,c,BLACK", "1,c,BLACK"})
	void isInitialPawnForwardRange(int row, char column, Team team) {
		Location starting = new Location(3, 'c');
		Location destination = new Location(row, column);

		assertThat(starting.isInitialPawnForwardRange(destination, team)).isTrue();
	}

	@DisplayName("로케이션이 목적지가 폰의 1칸 직진 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c,WHITE", "2,c,BLACK"})
	void isPawnForwardRange(int row, char column, Team team) {
		Location starting = new Location(3, 'c');
		Location destination = new Location(row, column);

		assertThat(starting.isPawnForwardRange(destination, team)).isTrue();
	}

	@Test
	void isForwardDiagonal() {
	}

	@Test
	void calculateNextLocation() {
	}

	@Test
	void isVertical() {
	}
}