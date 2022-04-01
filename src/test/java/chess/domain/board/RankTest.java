package chess.domain.board;

import static chess.domain.board.Rank.EIGHT;
import static chess.domain.board.Rank.FIVE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class RankTest {

	@Test
	void add() {
		assertThat(TWO.add(3)).hasValue(FIVE);
	}


	@Test
	void addOverRange() {
		assertThat(EIGHT.add(1).isEmpty()).isTrue();
	}

	@Test
	void abs() {
		assertThat(TWO.abs(FIVE)).isEqualTo(3);
	}

	@Test
	void calculateDifference() {
		assertThat(TWO.calculateDifference(FIVE)).isEqualTo(-1);
	}

	@Test
	void subtract() {
		assertThat(TWO.subtract(FIVE)).isEqualTo(-3);
	}
}
