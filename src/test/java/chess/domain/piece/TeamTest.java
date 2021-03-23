package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTest {
    @Test
    @DisplayName("상대 팀 이름 확인 기능")
    void oppositeName() {
        assertThat(Team.BLACK.oppositeTeamName()).isEqualTo("white");
        assertThat(Team.WHITE.oppositeTeamName()).isEqualTo("black");
        assertThat(Team.NOTHING.oppositeTeamName()).isEqualTo("");
    }

    @Test
    @DisplayName("팀이 존재하지 않는지 확인 기능")
    void undefined() {
        assertThat(Team.NOTHING.undefined()).isTrue();
    }

    @RepeatedTest(5)
    @DisplayName("블랙 또는 화이트 팀이 선택되는지 확인 기능")
    void anyTeam() {
        assertThat(Team.NOTHING.anyTeamExcludingThis()).isNotEqualTo(Team.NOTHING);
    }
}