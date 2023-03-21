package chess.game;

import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @ParameterizedTest
    @EnumSource
    @DisplayName("원하는 팀의 차례를 생성한다..")
    void create_Turn(Team team) {
        Turn turn = new Turn(team);

        assertThat(turn.getTeam()).isEqualTo(team);
    }

    @Test
    @DisplayName("화이트 팀의 다음 차례는 블랙팀이다.")
    void whiteNextIsBlack() {
        Turn turn = new Turn(Team.WHITE);

        Turn nextTurn = turn.next();

        assertThat(nextTurn).isEqualTo(new Turn(Team.BLACK));
    }

    @Test
    @DisplayName("블랙 팀의 다음 차례는 화이트팀이다.")
    void blackNextIsWhite() {
        Turn turn = new Turn(Team.BLACK);

        Turn nextTurn = turn.next();

        assertThat(nextTurn).isEqualTo(new Turn(Team.WHITE));
    }
}
