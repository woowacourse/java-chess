package chess.domain.strategy;

import chess.domain.piece.ColorType;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("폰 움직임 전략")
public class PawnMoveStrategyTest {

    @DisplayName("폰은 올바른 위치가 입력되면 이동 가능을 반환한다.")
    @Test
    void pawnCorrectDestination() {
        // given
        MoveStrategy moveStrategy = new PawnMoveStrategy();
        Square source = Square.of(File.a, Rank.THREE);
        Square destination = Square.of(File.a, Rank.FOUR);
        ColorType colorType = ColorType.WHITE;

        // when
        boolean actual = moveStrategy.check(source, destination, colorType);

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("폰은 잘못된 위치가 입력되면 이동 불가능을 반환한다.")
    @Test
    void pawnInCorrectDestination() {
        // given
        MoveStrategy moveStrategy = new PawnMoveStrategy();
        Square source = Square.of(File.a, Rank.THREE);
        Square destination = Square.of(File.c, Rank.FOUR);
        ColorType colorType = ColorType.BLACK;

        // when
        boolean actual = moveStrategy.check(source, destination, colorType);

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("폰은 상대 말이 있을 경우 대각선 1칸이 입력되면 이동 가능을 반환한다.")
    @Test
    void moveOneDiagonal() {
        // given
        MoveStrategy moveStrategy = new PawnMoveStrategy();
        Square source = Square.of(File.a, Rank.THREE);
        Square destination = Square.of(File.b, Rank.FOUR);
        ColorType colorType = ColorType.WHITE;

        // when
        boolean actual = moveStrategy.check(source, destination, colorType);

        // then
        assertThat(actual).isTrue();
    }
}
