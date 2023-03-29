package chess.domain.game;

import chess.domain.piece.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TurnTest {

    @Test
    void WHITE_부터_순서를_가진다() {
        //given
        Turn turn = new Turn();
        //when & then
        Assertions.assertThat(turn.getCurrentTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 주어진_팀부터_순서를_가진다() {
        //given
        Turn turn = new Turn(Team.WHITE);
        //when & then
        Assertions.assertThat(turn.getCurrentTeam()).isEqualTo(Team.WHITE);
    }

    @Test
    void 주어진_팀부터_순서를_가진다2() {
        //given
        Turn turn = new Turn(Team.BLACK);
        //when & then
        Assertions.assertThat(turn.getCurrentTeam()).isEqualTo(Team.BLACK);
    }
}
