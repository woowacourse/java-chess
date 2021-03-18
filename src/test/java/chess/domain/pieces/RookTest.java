package chess.domain.pieces;

import chess.domain.position.Col;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("Rook이 Black 팀으로 생성되면, initial은 대문자 R이다.")
    void blackTeamInitialCheck() {
        Rook rook = Rook.of(Team.BLACK, 1);
        assertThat(rook.getInitial()).isEqualTo("R");
    }

    @Test
    @DisplayName("Rook이 White 팀으로 생성되면, initial은 소문자 r이다.")
    void whiteTeamInitialCheck() {
        Rook rook = Rook.of(Team.WHITE, 1);
        assertThat(rook.getInitial()).isEqualTo("r");
    }
}