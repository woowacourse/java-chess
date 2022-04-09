package chess.domain.piece.vo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamColorTest {

    @ParameterizedTest
    @DisplayName("턴을 바꾼다.")
    @CsvSource({"WHITE,BLACK", "BLACK,WHITE"})
    void findByRankException(TeamColor teamColor, TeamColor expected) {
        //when
        TeamColor actual = teamColor.nextTurn();
        //then
        Assertions.assertThat(actual).isEqualTo(expected);
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
