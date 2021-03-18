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

class RookTest {
    @ParameterizedTest
    @DisplayName("Rook이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"a", "h"})
    void blackTeamPositionCheck(String col) {
        Rook rook = Rook.of(Team.BLACK, Col.getLocation(col));
        Position rookPosition = rook.getPosition();
        assertThat(rookPosition.getRow()).isEqualTo(0);
        assertThat(rookPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Rook이 White 팀으로 생성되면, row의 실제 좌표 위치는 y이다.")
    @ValueSource(strings = {"a", "h"})
    void whiteTeamPositionCheck(String col) {
        Rook rook = Rook.of(Team.WHITE, Col.getLocation(col));
        Position rookPosition = rook.getPosition();
        assertThat(rookPosition.getRow()).isEqualTo(7);
        assertThat(rookPosition.getRow()).isNotEqualTo(8);
    }

    @ParameterizedTest
    @DisplayName("Rook 초기 col 위치가 a혹은 h가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"b", "c", "d", "e", "f", "g"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Rook.of(Team.BLACK, Col.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Rook이 Black 팀으로 생성되면, initial은 대문자 R이다.")
    void blackTeamInitialCheck() {
        Rook rook = Rook.of(Team.BLACK, 0);
        assertThat(rook.getInitial()).isEqualTo("R");
    }

    @Test
    @DisplayName("Rook이 White 팀으로 생성되면, initial은 소문자 r이다.")
    void whiteTeamInitialCheck() {
        Rook rook = Rook.of(Team.WHITE, 0);
        assertThat(rook.getInitial()).isEqualTo("r");
    }
}