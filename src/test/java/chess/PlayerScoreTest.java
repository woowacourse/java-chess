package chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerScoreTest {
	private PlayerScore playerScore;

	@BeforeEach
	void setUp() {
		playerScore = new PlayerScore();
	}

	@Test
	void 초기화_확인() {
		assertThat(playerScore.getScore(Player.BLACK)).isEqualTo(new Score(0));
		assertThat(playerScore.getScore(Player.WHITE)).isEqualTo(new Score(0));
	}

	@Test
	void 플레이어_점수_더하기() {
		assertThat(playerScore.addScore(Player.BLACK, new Score(10))).isEqualTo(new Score(10));
		assertThat(playerScore.addScore(Player.WHITE, new Score(20))).isEqualTo(new Score(20));
	}
}
