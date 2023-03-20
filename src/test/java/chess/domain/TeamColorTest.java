package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class TeamColorTest {

    @DisplayName("현재 팀 컬러에 따른 상대 팀 컬러를 반환한다.")
    @Test
    void 팀_턴_변경() {
        TeamColor teamColor = TeamColor.WHITE;

        assertThat(teamColor = teamColor.transfer()).isEqualTo(TeamColor.BLACK);
        assertThat(teamColor.transfer()).isEqualTo(TeamColor.WHITE);
    }

}
