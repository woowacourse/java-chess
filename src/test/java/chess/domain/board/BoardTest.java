package chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {
	@Test
	@DisplayName("체스판이 정상적으로 생성된 경우")
	void constructor() {
		assertThat(new Board(Arrays.asList(
				Rank.createBlanks(0),
				Rank.createBlanks(1),
				Rank.createBlanks(2),
				Rank.createBlanks(3),
				Rank.createBlanks(4),
				Rank.createBlanks(5),
				Rank.createBlanks(6),
				Rank.createBlanks(7)
		))).isInstanceOf(Board.class);
	}

	@Test
	@DisplayName("체스판 한 줄이 8개로 이루어지지 않은 경우 예외 발생")
	void constructor_invalid_size() {
		assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> new Board(Arrays.asList(
				Rank.createBlanks(0),
				Rank.createBlanks(1),
				Rank.createBlanks(2),
				Rank.createBlanks(3),
				Rank.createBlanks(4)
		)));
	}
}
