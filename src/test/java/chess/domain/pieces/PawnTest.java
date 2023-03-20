package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,c4", "c3,c5"})
    @DisplayName("white 폰은 위로 한 칸 혹은 두 칸 움직일 수 있다.")
    void move_success_lower_case(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        Pawn pawn = new Pawn(Team.WHITE);

        // when & then
        Assertions.assertDoesNotThrow(() -> pawn.canMove(source, destination));
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,c2", "c3,a2", "c3,b7", "c3,d8"})
    @DisplayName("white폰이 위로 한 칸 혹은 두 칸으로 움직이지 않으면 오류를 발생시킨다.")
    void throws_exception_when_lower_pawn_moves_invalid(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        Pawn pawn = new Pawn(Team.WHITE);

        // when & then
        assertThatThrownBy(
            () -> pawn.canMove(source, destination)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c7,c6", "c7,c5"})
    @DisplayName("black폰은 아래로 한 칸 혹은 두 칸을 움직인다.")
    void move_success_upper_case(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        Pawn pawn = new Pawn(Team.BLACK);

        // when & then
        Assertions.assertDoesNotThrow(() -> pawn.canMove(source, destination));
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,c4", "c3,a2", "c3,b7", "c3,d8"})
    @DisplayName("black폰이 아래로 한 칸 혹은 두 칸으로 움직이지 않으면 에러를 발생한다.")
    void throws_exception_when_upper_pawn_moves_invalid(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        Pawn pawn = new Pawn(Team.BLACK);

        // when & then
        assertThatThrownBy(
            () -> pawn.canMove(source, destination)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("모든 폰은 처음에만 두 번 움직일 수 있다.")
    void throws_exception_when_pawn_move_double_after_first() {
        // given
        Pawn pawn = new Pawn(Team.WHITE);

        // when
        pawn.canMove(Position.from("a2"), Position.from("a3"));

        // then
        assertThatThrownBy(() -> pawn.canMove(Position.from("a3"), Position.from("a5")))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
