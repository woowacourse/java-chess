package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.strategy.Route;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WhitePawnTest {

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,c4", "c2,c4"})
    @DisplayName("white 폰은 위로 한 칸 혹은 두 칸 움직일 수 있다.")
    void move_success_white_case(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        WhitePawn pawn = new WhitePawn(Team.WHITE);

        // when & then
        Assertions.assertDoesNotThrow(() -> pawn.canMove(source, destination, false));
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3,c2", "c3,a2", "c3,b7", "c3,d8"})
    @DisplayName("white폰이 위로 한 칸 혹은 두 칸으로 움직이지 않으면 오류를 발생시킨다.")
    void throws_exception_when_white_pawn_moves_invalid(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        WhitePawn pawn = new WhitePawn(Team.WHITE);

        // when & then
        assertThatThrownBy(
            () -> pawn.canMove(source, destination, false)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("White Pawn이 첫 두칸 움직임의 경로를 만든다.")
    void generate_white_pawn_first_move_route() {
        // given
        Position source = Position.from("a2");
        Position destination = Position.from("a4");
        WhitePawn pawn = new WhitePawn(Team.WHITE);
        List<Position> expectRoute = List.of(
            Position.from("a3")
        );

        // when
        Route result = pawn.generateRoute(source, destination);

        // then
        assertThat(result.getRoute()).isEqualTo(expectRoute);
    }

    @ParameterizedTest(name = "{displayName} {0} -> {1}")
    @CsvSource(value = {"a2, b3", "b2, a3"})
    @DisplayName("white pawn이 공격 움직임일 때 대각선 이동이 가능하다.")
    void move_diagonal_success_when_attack_move(String start, String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        WhitePawn pawn = new WhitePawn(Team.BLACK);

        // when & then
        assertDoesNotThrow(() -> pawn.canMove(source, destination, true));
    }

    @ParameterizedTest(name = "{displayName} {0} -> {1}")
    @CsvSource(value = {"a2, b3", "b2, a3"})
    @DisplayName("white pawn이 공격 움직임이 아닐 때 대각선 이동이 불가능하다.")
    void move_diagonal_fail_when_not_attack_move(String start, String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        WhitePawn pawn = new WhitePawn(Team.BLACK);

        // when & then
        assertThatThrownBy(() -> pawn.canMove(source, destination, false))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("white pawn의 한 칸 이동의 경로는 빈리스트를 반환한다.")
    void generate_route_when_white_pawn_one_move() {
        // given
        Position source = Position.from("a2");
        Position destination = Position.from("a3");
        WhitePawn pawn = new WhitePawn(Team.WHITE);
        List<Position> expectRoute = List.of();

        // when
        Route result = pawn.generateRoute(source, destination);

        // then
        assertThat(result.getRoute()).isEqualTo(expectRoute);
    }
}
