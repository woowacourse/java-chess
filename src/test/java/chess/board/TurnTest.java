package chess.board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TurnTest {

    @Test
    @DisplayName("현재 진행중인 팀이 맞다면 true를 반환한다. ")
    void isCurrentTeam() {
        Turn turn = new Turn(Team.BLACK);
        assertThat(turn.isCurrentTeam(Team.BLACK)).isTrue();
    }

    @Test
    @DisplayName("현재 진행중인 팀에서 상대 팀으로 차례가 넘어간다.")
    void change() {
        Turn blackTeam = new Turn(Team.BLACK);
        Turn changedTeam = blackTeam.change();
        assertThat(changedTeam).isEqualTo(new Turn(Team.WHITE));

    }
}
