package chess.domain.piece.character;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamTest {
    @DisplayName("상대 팀을 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,WHITE", "WHITE,BLACK"})
    void opponent(Team myTeam, Team opponentTeam) {
        assertThat(myTeam.opponent()).isEqualTo(opponentTeam);
    }
}