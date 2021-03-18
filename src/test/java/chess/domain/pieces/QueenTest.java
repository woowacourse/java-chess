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

class QueenTest {
    @ParameterizedTest
    @DisplayName("Queen이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"d"})
    void blackTeamPositionCheck(String col) {
        Queen queen = Queen.of(Team.BLACK, Col.getLocation(col));
        Position queenPosition = queen.getPosition();

        assertThat(queenPosition.getRow()).isEqualTo(0);
        assertThat(queenPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Queen이 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"d"})
    void whiteTeamPositionCheck(String col) {
        Queen queen = Queen.of(Team.WHITE, Col.getLocation(col));
        Position queenPosition = queen.getPosition();

        assertThat(queenPosition.getRow()).isEqualTo(7);
        assertThat(queenPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Queen 초기 col 위치가 d가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "b", "c", "e", "f", "g", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Queen.of(Team.BLACK, Col.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Queen이 Black 팀으로 생성되면, initial은 대문자 Q이다.")
    void blackTeamInitialCheck() {
        Queen queen = Queen.of(Team.BLACK, 3);
        assertThat(queen.getInitial()).isEqualTo("Q");
    }

    @Test
    @DisplayName("Queen이 White 팀으로 생성되면, initial은 소문자 q이다.")
    void whiteTeamInitialCheck() {
        Queen queen = Queen.of(Team.WHITE, 3);
        assertThat(queen.getInitial()).isEqualTo("q");
    }
}