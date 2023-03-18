package chess.domain.piece;

import static chess.domain.piece.Color.BLACK;
import static chess.domain.piece.Color.WHITE;
import static chess.util.SquareFixture.E_FIVE;
import static chess.util.SquareFixture.E_FOUR;
import static chess.util.SquareFixture.E_SEVEN;
import static chess.util.SquareFixture.E_SIX;
import static chess.util.SquareFixture.E_THREE;
import static chess.util.SquareFixture.E_TWO;
import static chess.util.SquareFixture.F_SIX;
import static chess.util.SquareFixture.F_THREE;
import static chess.util.SquareFixture.F_TWO;
import static chess.util.SquareFixture.G_FOUR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Nested
    class findRoute_메서드는 {

        @Nested
        class 검은색일때 {
            @Test
            void 대각선으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(BLACK);

                assertThat(pawn.findRoute(E_SEVEN, F_SIX)).containsExactly(F_SIX);
            }

            @Test
            void 한칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(BLACK);

                assertThat(pawn.findRoute(E_SEVEN, E_SIX)).containsExactly(E_SIX);
            }

            @Test
            void 두칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(BLACK);

                assertThat(pawn.findRoute(E_SEVEN, E_FIVE)).containsExactly(E_FIVE);
            }

            @Test
            void 움직일_수_없는_칸이면_예외를_던진다() {
                final Pawn pawn = new Pawn(BLACK);

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.findRoute(E_SEVEN, E_FOUR))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }
        }

        @Nested
        class 흰색일때 {
            @Test
            void 대각선으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(WHITE);

                assertThat(pawn.findRoute(E_TWO, F_THREE)).containsExactly(F_THREE);
            }

            @Test
            void 한칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(WHITE);

                assertThat(pawn.findRoute(E_TWO, E_THREE)).containsExactly(E_THREE);
            }

            @Test
            void 두칸_앞으로_움직이는_경로를_구한다() {
                final Pawn pawn = new Pawn(WHITE);

                assertThat(pawn.findRoute(E_TWO, E_FOUR)).containsExactly(E_FOUR);
            }

            @Test
            void 움직일_수_없는_칸이면_예외를_던진다() {
                final Pawn pawn = new Pawn(WHITE);

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.findRoute(F_TWO, G_FOUR))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }
        }
    }
}
