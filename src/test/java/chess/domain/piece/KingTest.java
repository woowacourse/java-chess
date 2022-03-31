package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @DisplayName("올바른 이동")
    @Test
    void move() {
        // given
        Position to = Position.of("a1");
        Position from = Position.of("a2");

        King king = new King(Team.BLACK);

        // then
        assertThatNoException().isThrownBy(() -> king.movable(to, from));
    }

    @DisplayName("올바르지 않은 이동")
    @Test
    void move_x() {
        // given
        Position to = Position.of("a1");
        Position from = Position.of("a5");

        King king = new King(Team.BLACK);

        // then
        assertThatThrownBy(() -> king.movable(to, from)).hasMessageContaining("King");
    }

    @DisplayName("킹의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a2,a1,SOUTH", "a1,a2,NORTH"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        King king = new King(Team.BLACK);

        // when
        Direction find = king.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}