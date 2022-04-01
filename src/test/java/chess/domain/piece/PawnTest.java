package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @DisplayName("흰색 팀일 때 이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"e2,d3", "e2,e3", "e2,f3", "a2,a4"})
    void movable_while(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Pawn pawn = new Pawn(Team.WHITE);

        // then
        assertThatNoException().isThrownBy(() -> pawn.movable(to, from));
    }

    @DisplayName("흰색 팀일 때 이동 불가 확인")
    @ParameterizedTest
    @CsvSource(value = {"a2,a1"})
    void movable_while_x(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Pawn pawn = new Pawn(Team.WHITE);

        // then
        assertThatThrownBy(() -> pawn.movable(to, from)).hasMessageContaining("움직일 수 있는 방향이 아닙니다.");
    }

    @DisplayName("첫수 외 두칸 이동 불가 확인")
    @ParameterizedTest
    @CsvSource(value = {"a7,a5", "a3,a5"})
    void movable_first_move(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Pawn pawn = new Pawn(Team.WHITE);

        // then
        assertThatThrownBy(() -> pawn.movable(to, from)).hasMessageContaining("첫수일 경우만 가능합니다.");
    }

    @DisplayName("검은 팀일 때 이동 가능 확인")
    @ParameterizedTest
    @CsvSource(value = {"e7,e6", "e7,d6", "e7,f6", "a7,a5"})
    void movable_black(String toValue, String fromValue) {
        // given
        Position to = new Position(toValue);
        Position from = new Position(fromValue);

        Pawn pawn = new Pawn(Team.BLACK);

        // then
        assertThatNoException().isThrownBy(() -> pawn.movable(to, from));
    }

    @DisplayName("검은 팀일 때 이동 불가 확인")
    @Test
    void movable_black_x() {
        // given
        Position to = new Position("a1");
        Position from = new Position("a2");

        Pawn pawn = new Pawn(Team.BLACK);

        // then
        assertThatThrownBy(() -> pawn.movable(to, from)).hasMessageContaining("움직일 수 있는 방향이 아닙니다.");
    }

    @DisplayName("이름")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,P"})
    void name(Team team, String expect) {
        // given
        Pawn pawn = new Pawn(team);

        // then
        assertThat(pawn.getName()).isEqualTo(expect);
    }

    @DisplayName("화이트 폰의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a2,a3", "a2,a4"})
    void findDirection_white(Position from, Position to) {
        // given
        Pawn pawn = new Pawn(Team.WHITE);

        // when
        Direction find = pawn.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(Direction.NORTH);
    }

    @DisplayName("블랙 폰의 방향을 체크한다.")
    @ParameterizedTest
    @CsvSource(value = {"a2,a1", "a7,a5"})
    void findDirection_black_while_direction(Position from, Position to) {
        // given
        Pawn pawn = new Pawn(Team.BLACK);

        // when
        Direction find = pawn.findDirection(from, to);

        // then
        assertThat(find).isEqualTo(Direction.SOUTH);
    }
}
