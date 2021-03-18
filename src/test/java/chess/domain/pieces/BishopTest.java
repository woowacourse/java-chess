package chess.domain.pieces;

import chess.domain.position.Col;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {
    @ParameterizedTest
    @DisplayName("Bishop이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"c", "f"})
    void blackTeamPositionCheck(String col) {
        Bishop bishop = Bishop.of(Team.BLACK, Col.getLocation(col));
        Position bishopPosition = bishop.getPosition();

        assertThat(bishopPosition.getRow()).isEqualTo(0);
        assertThat(bishopPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Bishop이 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"c", "f"})
    void whiteTeamPositionCheck(String col) {
        Bishop bishop = Bishop.of(Team.WHITE, Col.getLocation(col));
        Position bishopPosition = bishop.getPosition();

        assertThat(bishopPosition.getRow()).isEqualTo(7);
        assertThat(bishopPosition.getRow()).isNotEqualTo(1);
    }


    @ParameterizedTest
    @DisplayName("Bishop 초기 col 위치가 c혹은 f가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "b", "d", "e", "g", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Bishop.of(Team.BLACK, Col.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Bishop이 Black 팀으로 생성되면, initial은 대문자 B이다.")
    void blackTeamInitialCheck() {
        Bishop bishop = Bishop.of(Team.BLACK, 2);
        assertThat(bishop.getInitial()).isEqualTo("B");
    }

    @Test
    @DisplayName("Bishop이 White 팀으로 생성되면, initial은 소문자 b이다.")
    void whiteTeamInitialCheck() {
        Bishop bishop = Bishop.of(Team.WHITE, 2);
        assertThat(bishop.getInitial()).isEqualTo("b");
    }

}