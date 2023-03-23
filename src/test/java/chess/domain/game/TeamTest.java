package chess.domain.game;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamTest {

    @DisplayName("팀을 반대로 바꿀 수 있다")
    @ParameterizedTest(name = "{0} -> {1}")
    @CsvSource({"BLACK,WHITE", "WHITE,BLACK", "NEUTRAL,NEUTRAL"})
    void alter(Team team, Team other) {
        assertThat(team.alter()).isEqualTo(other);
    }
}