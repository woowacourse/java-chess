package chess.domain.pieces;

import chess.domain.position.Col;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightTest {
    @ParameterizedTest
    @DisplayName("Knight가 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"b", "g"})
    void blackTeamPositionCheck(String col) {
        Knight knight = Knight.of(Team.BLACK, Col.getLocation(col));
        Position KnightPosition = knight.getPosition();

        assertThat(KnightPosition.getRow()).isEqualTo(0);
        assertThat(KnightPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Knight가 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"b", "g"})
    void whiteTeamPositionCheck(String col) {
        Knight knight = Knight.of(Team.WHITE, Col.getLocation(col));
        Position KnightPosition = knight.getPosition();

        assertThat(KnightPosition.getRow()).isEqualTo(7);
        assertThat(KnightPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Knight 초기 col 위치가 b혹은 g가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "c", "d", "e", "f", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Knight.of(Team.BLACK, Col.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Knight가 Black 팀으로 생성되면, initial은 대문자 N이다.")
    void blackTeamInitialCheck() {
        Knight knight = Knight.of(Team.BLACK, 1);
        assertThat(knight.getInitial()).isEqualTo("N");
    }

    @Test
    @DisplayName("Knight가 White 팀으로 생성되면, initial은 소문자 n이다.")
    void whiteTeamInitialCheck() {
        Knight knight = Knight.of(Team.WHITE, 1);
        assertThat(knight.getInitial()).isEqualTo("n");
    }
}