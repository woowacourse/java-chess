package chess.position;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

    @Test
    @DisplayName("targetPosition까지의 경로를 구할 수 있다.(가로 이동)")
    void should_get_route_to_target_position_horizontally() {
        Position startPosition = new Position(File.a, Rank.ONE);
        Position targetPosition = new Position(File.e, Rank.ONE);

        List<Position> route = startPosition.findRoute(targetPosition);
        assertThat(route).containsExactly(
                new Position(File.b, Rank.ONE),
                new Position(File.c, Rank.ONE),
                new Position(File.d, Rank.ONE));
    }

    @Test
    @DisplayName("targetPosition까지의 경로를 구할 수 있다.(세로 이동)")
    void should_get_route_to_target_position_vertically() {
        Position startPosition = new Position(File.a, Rank.ONE);
        Position targetPosition = new Position(File.a, Rank.FIVE);

        List<Position> route = startPosition.findRoute(targetPosition);
        assertThat(route).containsExactly(
                new Position(File.a, Rank.TWO),
                new Position(File.a, Rank.THREE),
                new Position(File.a, Rank.FOUR));
    }

    @Test
    @DisplayName("targetPosition까지의 경로를 구할 수 있다.(대각선 이동)")
    void should_get_route_to_target_position_diagonally() {
        Position startPosition = new Position(File.a, Rank.ONE);
        Position targetPosition = new Position(File.e, Rank.FIVE);

        List<Position> route = startPosition.findRoute(targetPosition);
        assertThat(route).containsExactly(
                new Position(File.b, Rank.TWO),
                new Position(File.c, Rank.THREE),
                new Position(File.d, Rank.FOUR));
    }
}
