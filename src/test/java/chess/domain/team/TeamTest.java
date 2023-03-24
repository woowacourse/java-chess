package chess.domain.team;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.team.Team.BLACK;
import static chess.domain.team.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {

    @Test
    @DisplayName("흰팀의 반대는 검은팀이다")
    void reverseWhiteIsBlack() {
        // given
        Team team = WHITE;
        Team expected = BLACK;

        // when
        Team actual = team.reverse();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("검은팀의 반대는 흰팀이다")
    void reverseBlackIsWhite() {
        // given
        Team team = BLACK;
        Team expected = WHITE;

        // when
        Team actual = team.reverse();

        // then
        assertThat(actual).isEqualTo(expected);
    }
}
