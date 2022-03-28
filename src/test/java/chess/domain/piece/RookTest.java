package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {

    @DisplayName("이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"a1,a5", "a5,a1", "d1,d5", "d5,d1"})
    void movable(String toValue, String fromValue) {
        // given
        Position to = Position.of(toValue);
        Position from = Position.of(fromValue);

        Rook rook = new Rook(Team.BLACK);

        // then
        assertThatNoException().isThrownBy(() -> rook.movable(to, from));
    }

    @DisplayName("이동 불가 확인")
    @Test
    void notMovable() {
        // given
        Position to = Position.of("a1");
        Position from = Position.of("b3");

        Rook rook = new Rook(Team.BLACK);

        // then
        assertThatThrownBy(() -> rook.movable(to, from)).hasMessageContaining("Rook");
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,R", "WHITE,r"})
    void name(Team team, String expect) {
        // given
        Rook rook = new Rook(team);

        // then
        assertThat(rook.getName()).isEqualTo(expect);
    }

    @DisplayName("룩의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a1,a3,NORTH", "a3,a1,SOUTH"})
    void findDirection(Position from, Position to, Direction direction) {
        // given
        Rook rook = new Rook(Team.WHITE);

        // when
        Direction find = rook.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}