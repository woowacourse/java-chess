package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @DisplayName("폰 이동 가능 확인")
    @ParameterizedTest(name = "{2}팀 {0} -> {1} 이동")
    @CsvSource(value = {"e2,d3,WHITE", "e2,e3,WHITE", "e2,f3,WHITE", "a2,a4,WHITE", "e7,e6,BLACK", "e7,d6,BLACK",
            "e7,f6,BLACK", "a7,a5,BLACK"})
    void movable_while(String toValue, String fromValue, Team team) {
        // given
        Position to = Position.of(toValue);
        Position from = Position.of(fromValue);

        Pawn pawn = new Pawn(team);

        // then
        assertThatNoException().isThrownBy(() -> pawn.movable(to, from));
    }

    @DisplayName("폰 이동 불가 확인")
    @ParameterizedTest(name = "{2}팀 {0} -> {1} 이동")
    @CsvSource(value = {"a2,a1,WHITE", "a7,a5,WHITE", "a3,a5,WHITE", "a1,a2,BLACK"})
    void movable_while_x(String toValue, String fromValue, Team team) {
        // given
        Position to = Position.of(toValue);
        Position from = Position.of(fromValue);

        Pawn pawn = new Pawn(team);

        // then
        assertThatThrownBy(() -> pawn.movable(to, from)).hasMessageContaining("Pawn");
    }

    @DisplayName("폰의 방향을 체크")
    @ParameterizedTest(name = "{2}팀은 {3} 쪽으로 이동 가능")
    @CsvSource(value = {"a2,a3,WHITE,NORTH", "a2,a4,WHITE,NORTH", "a2,a1,BLACK,SOUTH", "a7,a5,BLACK,SOUTH"})
    void findDirection_white(Position from, Position to, Team team, Direction direction) {
        // given
        Pawn pawn = new Pawn(team);

        // when
        Direction find = pawn.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(direction);
    }
}
