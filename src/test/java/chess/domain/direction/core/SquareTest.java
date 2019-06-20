package chess.domain.direction.core;

import chess.domain.direction.core.Square;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SquareTest {

    @Test
    void 객체_생성_테스트() {
        assertThat(Square.of(0, 0)).isEqualTo(Square.of(0, 0));
    }

    @Test
    void 동일한_객체인지_테스트() {
        assertThat(Square.of(0, 0) == Square.of(0, 0)).isTrue();
    }
}