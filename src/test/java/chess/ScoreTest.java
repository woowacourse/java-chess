package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
	private Score score;

	@BeforeEach
	void setUp() {
		score = new Score(20);
	}

	@Test
	void 자기_점수가_더_높은_경우() {
		assertThat(score.compare(new Score(19))).isEqualTo(Result.WIN);
	}

	@Test
	void 자기_점수가_더_낮은_경우() {
		assertThat(score.compare(new Score(21))).isEqualTo(Result.LOSE);
	}

	@Test
	void 점수가_같은_경우() {
		assertThat(score.compare(new Score(20))).isEqualTo(Result.DRAW);
	}

	@Test
	void 점수_더하기() {
		assertThat(score.add(new Score(10))).isEqualTo(new Score(30));
	}
}
