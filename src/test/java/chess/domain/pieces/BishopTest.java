package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.math.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static chess.domain.math.Direction.UP;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BishopTest {

    @ParameterizedTest(name = "input : {0}")
    @DisplayName("비숍의 방향을 검증한다.")
    @ValueSource(strings = {"UP_LEFT", "UP_RIGHT", "DOWN_LEFT", "DOWN_RIGHT"})
    void hasPattern_success(String direction) {
        Bishop bishop = new Bishop(Team.BLACK);
        assertThat(bishop.hasDirection(Direction.valueOf(direction))).isTrue();
    }

    @Test
    @DisplayName("비숍의 방향을 검증한다.")
    void hasPattern_fail() {
        Bishop bishop = new Bishop(Team.BLACK);

        assertThat(bishop.hasDirection(UP)).isFalse();
    }

    @Test
    @DisplayName("비숍의 팀을 검증한다.")
    void validateTeamTest_exception() {
        Team team = Team.NEUTRALITY;

        assertThatThrownBy(() -> new Bishop(team));
    }
}
