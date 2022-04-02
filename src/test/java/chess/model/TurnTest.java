package chess.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("처음 턴이 화이트턴이면 true를 반환한다.")
    void initTurnTest() {
        Turn turn = Turn.init();

        assertThat(turn.isCurrentTeam(Team.WHITE)).isTrue();
    }

    @Test
    @DisplayName("화이트팀에서 턴을 바꾸면 블랙팀 턴이다.")
    void isCurrentTeamTest() {
        Turn turn = Turn.init();
        turn = turn.change();

        assertThat(turn.isCurrentTeam(Team.BLACK)).isTrue();
    }
}