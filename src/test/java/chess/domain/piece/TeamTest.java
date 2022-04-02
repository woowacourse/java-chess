package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class TeamTest {

	@Test
	void isBlack() {
		Team black = Team.BLACK;
		Team white = Team.WHITE;
		Team neutrality = Team.NEUTRALITY;

		assertAll(
				() -> assertThat(black.isBlack()).isTrue(),
				() -> assertThat(white.isBlack()).isFalse(),
				() -> assertThat(neutrality.isBlack()).isFalse()
		);
	}

	@Test
	void isWhite() {
		Team black = Team.BLACK;
		Team white = Team.WHITE;
		Team neutrality = Team.NEUTRALITY;

		assertAll(
				() -> assertThat(black.isWhite()).isFalse(),
				() -> assertThat(white.isWhite()).isTrue(),
				() -> assertThat(neutrality.isWhite()).isFalse()
		);
	}
}
