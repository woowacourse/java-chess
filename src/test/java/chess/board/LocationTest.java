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
		Location starting = Location.of('c', 1);
		Location destination = Location.of('c', 7);

		assertThat(starting.isSameColumn(destination)).isTrue();
	}

	@DisplayName("해당 로케이션이 폰의 초기위치인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"2,a,WHITE", "2,b,WHITE", "2,c,WHITE", "2,d,WHITE", "2,e,WHITE", "2,f,WHITE", "2,g,WHITE",
		"2,h,WHITE", "7,a,BLACK", "7,b,BLACK", "7,c,BLACK", "7,d,BLACK", "7,e,BLACK", "7,f,BLACK", "7,g,BLACK",
		"7,h,BLACK"})
	void isInitialPawnLocation(int row, char col, Team team) {
		Location starting = Location.of(col, row);
		assertThat(starting.isInitialPawnLocation(team)).isTrue();
	}

	@DisplayName("로케이션의 출발지와 목적지로 weight만큼 전진하는 정보를 만듬")
	@ParameterizedTest
	@CsvSource(value = {"5,a,4,b", "5,c,4,c", "5,e,4,d", "3,e,3,d", "1,e,2,d", "1,c,2,c", "1,a,2,b", "3,a,3,b"})
	void calculateNextLocation(int row, char column, int expectRow, char expectColumn) {
		int weight = 1;
		Location starting = Location.of('c', 3);
		Location destination = Location.of(column, row);
		Location actual = starting.calculateNextLocation(destination, weight);

		assertThat(actual).isEqualTo(Location.of(expectColumn, expectRow));
	}

	@DisplayName("로케이션이 목적지가 세로 선상인지 확인")
	@ParameterizedTest
	@CsvSource(value = {"4,c", "2,c", "1,c", "5,c"})
	void isVertical(int row, char column) {
		Location starting = Location.of('c', 3);
		Location destination = Location.of(column, row);
		starting.isSameColumn(destination);
	}
}