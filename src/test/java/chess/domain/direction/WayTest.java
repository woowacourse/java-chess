package chess.domain.direction;

import chess.domain.direction.core.Direction;
import chess.domain.direction.core.MoveStrategy;
import chess.domain.direction.core.Square;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WayTest {

    @Test
    void Way생성_테스트() {
        Way way = new Way(Direction.UP, MoveStrategy.BOTH);
        assertThat(way).isEqualTo(new Way(Direction.UP, MoveStrategy.BOTH));
    }

    @Test
    void Way를_통해_해당_좌표로_갈수_있는지_테스트() {
        assertThat(new Way(Direction.DOWN, MoveStrategy.BOTH)
                .canMove(Square.of(3, 2), Square.of(3, 3))).isTrue();
    }

    @Test
    void Way를_통해_갈수가_없는지_테스트() {
        assertThat(new Way(Direction.DOWN, MoveStrategy.BOTH)
                .canMove(Square.of(3, 2), Square.of(3, 0))).isFalse();
    }

    @Test
    void Way를_통해_경로_생성하기_테스트() {
        assertThat(new Way(Direction.DOWN, MoveStrategy.BOTH).generateRoute(Square.of(3, 2), Square.of(3, 8)))
                .isEqualTo(new Route(Arrays.asList(
                        Square.of(3, 2),
                        Square.of(3, 3),
                        Square.of(3, 4),
                        Square.of(3, 5),
                        Square.of(3, 6),
                        Square.of(3, 7),
                        Square.of(3, 8)
                ), MoveStrategy.BOTH));
    }
}