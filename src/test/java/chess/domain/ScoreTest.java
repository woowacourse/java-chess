package chess.domain;

import chess.domain.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreTest {
	private Score score;

	@BeforeEach
	void setUp() {
		score = new Score(20.1);
	}

	@Test
	void 자기_점수가_더_높은_경우() {
		assertTrue(score.isHigher(new Score(19.9)));
	}

	@Test
	void 자기_점수가_더_낮은_경우() {
		assertFalse(score.isHigher(new Score(20.2)));
	}

	@Test
	void 점수가_같은_경우() {
		assertEquals(score, new Score(20.1));
	}

	@Test
	void 점수_더하기() {
		assertThat(score.add(new Score(10.1))).isEqualTo(new Score(30.2));
	}
}
