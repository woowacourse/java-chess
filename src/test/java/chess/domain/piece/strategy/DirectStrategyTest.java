package chess.domain.piece.strategy;

import static chess.domain.piece.strategy.vector.DirectVector.NORTH;
import static chess.domain.piece.strategy.vector.DirectVector.SOUTH;
import static chess.util.SquareFixture.A_ONE;
import static chess.util.SquareFixture.A_TWO;
import static chess.util.SquareFixture.B_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DirectStrategyTest {

    @Nested
    class findRoute_메서드는 {

        @Test
        void 움직일_수_있는_방향이_존재하면_경로를_반환한다() {
            final DirectStrategy strategy = new DirectStrategy(List.of(NORTH, SOUTH));

            assertThat(strategy.findRoute(A_ONE, A_TWO)).containsExactly(A_TWO);
        }

        @Test
        void 움직일_수_있는_방향이_존재하지_않으면_예외를_던진다() {
            final DirectStrategy strategy = new DirectStrategy(List.of(NORTH, SOUTH));

            assertThatIllegalArgumentException()
                    .isThrownBy(() -> strategy.findRoute(A_ONE, B_ONE));
        }
    }
}
