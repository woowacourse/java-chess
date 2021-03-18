package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Col;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KingTest {
    @ParameterizedTest
    @DisplayName("King이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"e"})
    void blackTeamPositionCheck(String col) {
        King king = King.of(Team.BLACK, Col.getLocation(col));
        Position kingPosition = king.getPosition();

        assertThat(kingPosition.getRow()).isEqualTo(0);
        assertThat(kingPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("King이 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"e"})
    void whiteTeamPositionCheck(String col) {
        King king = King.of(Team.WHITE, Col.getLocation(col));
        Position kingPosition = king.getPosition();

        assertThat(kingPosition.getRow()).isEqualTo(7);
        assertThat(kingPosition.getRow()).isNotEqualTo(1);
    }


    @ParameterizedTest
    @DisplayName("King 초기 col 위치가 e가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "b", "c", "d", "f", "g", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> King.of(Team.BLACK, Col.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("King이 Black 팀으로 생성되면, initial은 대문자 K이다.")
    void blackTeamInitialCheck() {
        King king = King.of(Team.BLACK, 4);
        assertThat(king.getInitial()).isEqualTo("K");
    }

    @Test
    @DisplayName("King이 White 팀으로 생성되면, initial은 소문자 k이다.")
    void whiteTeamInitialCheck() {
        King king = King.of(Team.WHITE, 4);
        assertThat(king.getInitial()).isEqualTo("k");
    }
}