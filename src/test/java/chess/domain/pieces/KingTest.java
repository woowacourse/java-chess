package chess.domain.pieces;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.board.Position;
import chess.domain.direction.Route;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3:b2", "c3:c2", "c3:d2", "c3:b3", "c3:d3", "c3:b4", "c3:c4", "c3:d4"}, delimiter = ':')
    @DisplayName("king은 팔방으로 한칸만 움직인다.")
    void move_success(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        King king = new King(Team.WHITE);

        // when & then
        assertDoesNotThrow(() -> king.canMove(source, destination, true));
    }

    @ParameterizedTest(name = "{displayName} 시작위치: {0} 도착위치: {1}")
    @CsvSource(value = {"c3:b1", "c3:c1", "c3:d1", "c3:b5"}, delimiter = ':')
    @DisplayName("King이 팔방으로 한칸만 움직이지 않으면 예외를 발생시킨다.")
    void throws_exception_when_move_invalid(final String start, final String end) {
        // given
        Position source = Position.from(start);
        Position destination = Position.from(end);
        King king = new King(Team.WHITE);

        // when & then
        assertThatThrownBy(
            () -> king.canMove(source, destination, true)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("King의 이동경로는 빈 리스트이다.")
    void generate_king_move_route() {
        // given
        Position source = Position.from("e1");
        Position destination = Position.from("e2");
        King king = new King(Team.WHITE);
        List<Position> expectRoute = List.of();

        // when
        Route result = king.generateRoute(source, destination);

        // then
        assertThat(result.getRoute()).isEqualTo(expectRoute);
    }
}
