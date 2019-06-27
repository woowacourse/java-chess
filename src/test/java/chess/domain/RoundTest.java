package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {
    Round round;

    @BeforeEach
    void setUp() {
        int startRound = 0;
        round = new Round(startRound);
    }

    @Test
    void 시작할때_블랙팀() {
        assertThat(round.getTeam()).isEqualTo(Team.BLACK);
    }

    @Test
    void 한번_진행후_흰팀() {
        round.nextRound();
        assertThat(round.getTeam()).isEqualTo(Team.WHITE);
    }
}