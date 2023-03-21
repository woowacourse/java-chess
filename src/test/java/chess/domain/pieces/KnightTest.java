package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Position;
import chess.domain.strategy.Route;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KnightTest {

    @ParameterizedTest
    @CsvSource(value = {"c3,a2", "c3,a4", "c3,b1", "c3,b5", "c3,d1", "c3,d5", "c3,e2", "c3,e4"})
    @DisplayName("Knight는 ")
    void move_success(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        Knight knight = new Knight(Team.WHITE);

        // when  & then
        Assertions.assertDoesNotThrow(
            () -> knight.canMove(source, destination, true)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"c3,a1", "c3,a3", "c3,b2", "c3,b4", "c3,d2", "c3,d6", "c3,e3", "c3,e5"})
    @DisplayName("Knight가 정상적인 위치로 움직이지 않는 경우 예외를 발생시킨다.")
    void throws_exception_when_knight_moves_invalid(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        Knight knight = new Knight(Team.WHITE);

        // when  & then
        assertThatThrownBy(
            () -> knight.canMove(source, destination, true)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("knight의 이동경로는 빈 리스트를 제공한다.")
    void generate_knight_move_route() {
        // given
        Position source = Position.from("b1");
        Position destination = Position.from("c3");
        Knight knight = new Knight(Team.WHITE);
        List<Position> expectRoute = List.of();

        // when
        Route result = knight.generateRoute(source, destination);

        // then
        assertThat(result.getRoute()).isEqualTo(expectRoute);
    }
}
