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
		Location starting = Location.of(1, 'c');
		Location destination = Location.of(7, 'c');

		assertThat(starting.isSameCol(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 대각선에 있는지 확인")
	@Test
	void isDiagonal() {
		Location starting = Location.of(1, 'c');
		Location destination = Location.of(2, 'b');

		assertThat(starting.isDiagonal(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 킹의 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,b", "4,c", "4,d", "3,d", "2,d", "2,c", "2,b", "3,b"})
	void isKingRange(int row, char column) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);

		assertThat(starting.isKingRange(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 나이트의 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"5,d", "5,b", "4,e", "4,a", "2,a", "2,e", "1,b", "1,d"})
	void isKnightRange(int row, char column) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);

		assertThat(starting.isKnightRange(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 퀸의 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,b", "4,c", "4,d", "3,d", "2,d", "2,c", "2,b", "3,b"})
	void isQueenRang(int row, char column) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);

		assertThat(starting.isQueenRange(destination)).isTrue();
	}

	@DisplayName("로케이션이 목적지와 가로, 세로 직선범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c", "3,d", "2,c", "3,b"})
	void isStraight(int row, char column) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);

		assertThat(starting.isStraight(destination)).isTrue();
	}

	@DisplayName("해당 로케이션이 폰의 초기위치인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"2,a,WHITE", "2,b,WHITE", "2,c,WHITE", "2,d,WHITE", "2,e,WHITE", "2,f,WHITE", "2,g,WHITE",
		"2,h,WHITE", "7,a,BLACK", "7,b,BLACK", "7,c,BLACK", "7,d,BLACK", "7,e,BLACK", "7,f,BLACK", "7,g,BLACK",
		"7,h,BLACK"})
	void isInitialPawnLocation(int row, char col, Team team) {
		Location starting = Location.of(row, col);
		assertThat(starting.isInitialPawnLocation(team)).isTrue();
	}

	@DisplayName("로케이션이 목적지가 폰의 초기 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c,WHITE", "5,c,WHITE", "2,c,BLACK", "1,c,BLACK"})
	void isInitialPawnForwardRange(int row, char column, Team team) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);

		assertThat(starting.isInitialPawnForwardRange(destination, team)).isTrue();
	}

	@DisplayName("로케이션이 목적지가 폰의 1칸 직진 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c,WHITE", "2,c,BLACK"})
	void isPawnForwardRange(int row, char column, Team team) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);

		assertThat(starting.isPawnForwardRange(destination, team)).isTrue();
	}

	@DisplayName("로케이션이 목적지가 폰의 대각선 범위인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,b,WHITE", "4,c,WHITE", "2,b,BLACK", "2,d,BLACK"})
	void isForwardDiagonal(int row, char column, Team team) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);
		starting.isForwardDiagonal(destination, team);
	}

	@DisplayName("로케이션의 출발지와 목적지로 weight만큼 전진하는 정보를 만듬")
	@ParameterizedTest
	@CsvSource(value = {"5,a,4,b", "5,c,4,c", "5,e,4,d", "3,e,3,d", "1,e,2,d", "1,c,2,c", "1,a,2,b", "3,a,3,b"})
	void calculateNextLocation(int row, char column, int expectRow, char expectColumn) {
		int weight = 1;
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);
		Location actual = starting.calculateNextLocation(destination, weight);

		assertThat(actual).isEqualTo(Location.of(expectRow, expectColumn));
	}

	@DisplayName("로케이션이 목적지가 세로 선상인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c", "2,c", "1,c", "5,c"})
	void isVertical(int row, char column) {
		Location starting = Location.of(3, 'c');
		Location destination = Location.of(row, column);
		starting.isVertical(destination);
	}
}