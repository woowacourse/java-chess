package chess.domain.direction;

import chess.domain.direction.core.Direction;
import chess.domain.piece.core.Square;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class WayTest {

    @Test
    void Way생성_테스트() {
        Way way = new Way(Direction.UP, 1);
        assertThat(way).isEqualTo(new Way(Direction.UP, 1));
    }

    @Test
    void Way를_통해_해당_좌표로_갈수_있는지_테스트() {
         assertThat(new Way(Direction.DOWN, 1)
                 .isGo(new Square(3,2), new Square(3,3))).isTrue();
    }

    @Test
    void Way를_통해_갈수가_없는지_테스트() {
        assertThat(new Way(Direction.DOWN, 1)
                .isGo(new Square(3,2), new Square(3,4))).isFalse();
    }

    @Test
    void Way를_통해_경로_생성하기_테스트() {
        assertThat(new Way(Direction.DOWN).generateRoute(new Square(3,2), new Square(3,8)))
                .isEqualTo(new Route(Arrays.asList(
                        new Square(3,2),
                        new Square(3,3),
                        new Square(3,4),
                        new Square(3,5),
                        new Square(3,6),
                        new Square(3,7),
                        new Square(3,8)
                )));
    }
}