package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.character.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {
    @DisplayName("비숍은 대각선으로 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"1,1", "2,2", "3,3", "5,5", "6,6", "7,7", "8,8", "1,7", "2,6", "3,5", "5,3", "6,2", "7,1"})
    void bishopIsMovable(int row, int column) {
        assertThat(new Bishop(Team.WHITE).isMovable(new Movement(
                Position.of(4, 4),
                Position.of(row, column))))
                .isTrue();
    }

    @DisplayName("비숍은 대각선이 아닌 경우, 이동할 수 없다.")
    @Test
    void bishopMoveOverDiagonalLine() {
        assertThat(new Bishop(Team.WHITE).isMovable(new Movement(
                Position.of(4, 4),
                Position.of(1, 4))))
                .isFalse();
    }

    @DisplayName("두 위치 사이의 비숍이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        assertThat(new Bishop(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(7, 7))))
                .containsExactly(Position.of(5, 5), Position.of(6, 6));
    }

    @DisplayName("두 위치 사이의 비숍이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionMinus() {
        assertThat(new Bishop(Team.WHITE)
                .findBetweenPositions(new Movement(
                        Position.of(4, 4),
                        Position.of(1, 1))))
                .containsExactly(Position.of(3, 3), Position.of(2, 2));
    }
}
