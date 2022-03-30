package chess.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.position.Position;
import chess.domain.position.UnitPosition;

class PositionTest {

	@Test
	@DisplayName("Position의 row의 차이를 구한다.")
	void subtractRow() {
		Position position = new Position(5, 3);
		int row = position.subtractRow(new Position(1, 2));
		assertThat(row).isEqualTo(4);
	}

	@Test
	@DisplayName("Position의 colulmn 차이를 구한다.")
	void subtractColumn() {
		Position position = new Position(5, 3);
		int row = position.subtractColumn(new Position(1, 2));
		assertThat(row).isEqualTo(1);
	}

	@Test
	@DisplayName("UnitPosition을 받아 해당 방향으로 Position을 변경한다.")
	void convert() {
		Position position = new Position(1, 1);
		Position convertPosition = position.convert(new UnitPosition(1, 0));
		assertThat(convertPosition).isEqualTo(new Position(2, 1));
	}

	@Test
	@DisplayName("threshold만큼 이동해서 해당 위치로 갈 수 있는지 확인한다.")
	void canReach() {
		Position from = new Position(1, 1);
		Position to = new Position(1, 5);
		assertThat(from.canReach(to, new UnitPosition(0, 1), 4)).isTrue();
	}
}