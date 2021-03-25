package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Team;
import chess.domain.chessgame.Turn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TurnTest {

    @Test
    @DisplayName("현재 팀을 반환하는지 테스트")
    void nextTurn() {
        Turn turn = new Turn(Team.WHITE);

        assertThat(turn.now()).isEqualTo(Team.WHITE);
    }

    @Test
    @DisplayName("다음 팀을 반환하는지 테스트")
    void name() {
        Turn turn = new Turn(Team.WHITE);
        List<Team> teams = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            turn.next();
            teams.add(turn.now());
        }

        List<Team> expectedTeams = Arrays.asList(Team.BLACK, Team.WHITE, Team.BLACK, Team.WHITE);

        assertThat(teams).isEqualTo(expectedTeams);
    }
}
