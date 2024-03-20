package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("비숍 움직임 전략")
class BishopMoveStrategyTest {

    private MoveStrategy moveStrategy;

    @BeforeEach
    void setUp() {
        moveStrategy = new BishopMoveStrategy();
    }

    @DisplayName("비숍은 대각선 방향의 위치가 들어오면 이동 가능을 반환한다.")
    @Test
    void bishopCorrectDestination() {
        // given
        Square source = Square.of(File.c, Rank.EIGHT);
        Square destination = Square.of(File.h, Rank.THREE);
        ColorType colorType = ColorType.BLACK;

        // when
        boolean actual = moveStrategy.check(source, destination, colorType);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("비숍은 대각선 방향이 아닌 위치가 들어오면 이동 불가능을 반환한다.")
    @Test
    void bishopInCorrectDestination() {
        // given
        Square source = Square.of(File.c, Rank.EIGHT);
        Square destination = Square.of(File.c, Rank.THREE);
        ColorType colorType = ColorType.BLACK;

        // when
        boolean actual = moveStrategy.check(source, destination, colorType);

        // then
        assertThat(actual).isFalse();
    }
}