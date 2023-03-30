package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {
    @DisplayName("점수를 통해 승자를 가릴 수 있다.")
    @Test
    void Should_WhiteWin_When_WhiteScoreOverThanBlackScore() {
        final double whiteScore = 10.0;
        final double blackScore = 5.0;

        assertThat(Team.calculateWinner(whiteScore, blackScore)).isEqualTo(Team.WHITE);
    }

    @DisplayName("점수를 통해 승자를 가릴 수 있다.")
    @Test
    void Should_BlackWin_When_WhiteScoreLessThanBlackScore() {
        final double whiteScore = 5.0;
        final double blackScore = 10.0;

        assertThat(Team.calculateWinner(whiteScore, blackScore)).isEqualTo(Team.BLACK);
    }

    @DisplayName("점수를 통해 승자를 가릴 수 있다.")
    @Test
    void Should_Empty_When_WhiteScoreSameBlackScore() {
        final double whiteScore = 5.0;
        final double blackScore = 5.0;

        assertThat(Team.calculateWinner(whiteScore, blackScore)).isEqualTo(Team.EMPTY);
    }
}
