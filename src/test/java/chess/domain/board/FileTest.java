package chess.domain.board;

import static chess.domain.board.File.B;
import static chess.domain.board.File.E;
import static chess.domain.board.File.H;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class FileTest {

	@Test
	void add() {
		assertThat(B.add(3)).hasValue(E);
	}

	@Test
	void addOverRange() {
		assertThat(H.add(1).isEmpty()).isTrue();
	}

	@Test
	void abs() {
		assertThat(B.abs(E)).isEqualTo(3);
	}

	@Test
	void subtract() {
		assertThat(B.subtract(E)).isEqualTo(-3);
	}
}
