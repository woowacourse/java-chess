package chess;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {
	@Test
	void 생성() {
		Position position = Position.getPosition(1, 8);
		assertThat(position).isEqualTo(Position.getPosition(1, 8));
	}

	@Test
	void 이동() {
		Position start = Position.getPosition(1, 1);
		assertThat(start.move(Direction.TOP)).isEqualTo(Position.getPosition(1, 2));
	}

	@Test
	void 위로_이동_가능한_최대_거리_계산() {
		Position start = Position.getPosition(1, 1);
		assertThat(start.getMaxDistance(Direction.TOP)).isEqualTo(7);
	}

	@Test
	void 아래로_이동_가능한_최대_거리_계산() {
		Position start = Position.getPosition(8, 5);
		assertThat(start.getMaxDistance(Direction.BOTTOM)).isEqualTo(4);
	}

	@Test
	void 왼쪽_아래로_이동_가능한_최대_거리_계산() {
		Position start = Position.getPosition(7, 8);
		assertThat(start.getMaxDistance(Direction.LEFT_BOTTOM)).isEqualTo(6);
	}
}