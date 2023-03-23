package chess.domain.game;

import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class TurnTest {

    @Test
    void 두_개의_팀이_아니면_예외() {
        //when & then
        List<Team> teams = List.of(Team.BLACK, Team.WHITE, Team.EMPTY);
        Assertions.assertThatThrownBy(() -> new Turn(teams))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("팀은 2팀만 참가할 수 있습니다.");
    }

    @Test
    void 팀이_중복이면_예외() {
        //when & then
        List<Team> teams = List.of(Team.WHITE, Team.WHITE);
        Assertions.assertThatThrownBy(() -> new Turn(teams))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("팀은 중복될 수 없습니다.");
    }
}
