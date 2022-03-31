package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @DisplayName("이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"a1,a5", "a5,a1", "d1,d5", "d5,d1", "a1,c3", "c3,a1", "a3,c1", "c1,a3"})
    void movable(String toValue, String fromValue) {
        // given
        Position to = Position.of(toValue);
        Position from = Position.of(fromValue);

        Queen queen = new Queen(Team.BLACK);

        // then
        assertThatNoException().isThrownBy(() -> queen.movable(to, from));
    }

    @DisplayName("이동 불가 확인")
    @Test
    void notMovable() {
        // given
        Position to = Position.of("a1");
        Position from = Position.of("b3");

        Queen queen = new Queen(Team.BLACK);

        // then
        assertThatThrownBy(() -> queen.movable(to, from)).hasMessageContaining("Queen");
    }

    @DisplayName("퀸의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1,c3,NORTH_EAST", "c3,a1,SOUTH_WEST"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        Queen queen = new Queen(Team.WHITE);

        // when
        Direction find = queen.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}