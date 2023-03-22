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

import chess.domain.board.BoardSnapShot;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {

    @Nested
    class isMovable_메서드는 {

        @Nested
        class 검은색일때 {

            @Test
            void 경로가_존재하지_않으면_예외를_던진다() {
                final Pawn pawn = new Pawn(BLACK);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(E_SEVEN, pawn));

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.isMovable(E_SEVEN, E_FOUR, boardSnapShot))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }

            @Test
            void 대각선으로_움직일_수_있으면_true_반환한다() {
                final Pawn pawn = new Pawn(BLACK);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(
                        E_SEVEN, pawn,
                        F_SIX, new Pawn(WHITE)
                ));

                assertThat(pawn.isMovable(E_SEVEN, F_SIX, boardSnapShot)).isTrue();
            }

            @Test
            void 한칸_앞으로_움직일_수_있으면_true_반환한다() {
                final Pawn pawn = new Pawn(BLACK);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(E_SEVEN, pawn));

                assertThat(pawn.isMovable(E_SEVEN, E_SIX, boardSnapShot)).isTrue();
            }

            @Test
            void 두칸_앞으로_움직일_수_있으면_true_반환한다() {
                final Pawn pawn = new Pawn(BLACK);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(E_SEVEN, pawn));

                assertThat(pawn.isMovable(E_SEVEN, E_FIVE, boardSnapShot)).isTrue();
            }

            @Test
            void 움직일_수_없으면_false_반환한다() {
                final Pawn pawn = new Pawn(BLACK);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(
                        E_SEVEN, pawn,
                        E_FIVE, new Pawn(WHITE)
                ));

                assertThat(pawn.isMovable(E_SEVEN, E_FIVE, boardSnapShot)).isFalse();
            }
        }

        @Nested
        class 흰색일때 {

            @Test
            void 경로가_존재하지_않으면_예외를_던진다() {
                final Pawn pawn = new Pawn(WHITE);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(F_TWO, pawn));

                assertThatIllegalArgumentException()
                        .isThrownBy(() -> pawn.isMovable(F_TWO, G_FOUR, boardSnapShot))
                        .withMessage("해당 기물이 움직일 수 있는 경로가 아닙니다.");
            }

            @Test
            void 대각선으로_움직일_수_있으면_true_반환한다() {
                final Pawn pawn = new Pawn(WHITE);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(
                        E_TWO, pawn,
                        F_THREE, new Pawn(BLACK)
                ));

                assertThat(pawn.isMovable(E_TWO, F_THREE, boardSnapShot)).isTrue();
            }

            @Test
            void 한칸_앞으로_움직일_수_있으면_true_반환한다() {
                final Pawn pawn = new Pawn(WHITE);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(E_TWO, pawn));

                assertThat(pawn.isMovable(E_TWO, E_THREE, boardSnapShot)).isTrue();
            }

            @Test
            void 두칸_앞으로_움직일_수_있으면_true_반환한다() {
                final Pawn pawn = new Pawn(WHITE);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(E_TWO, pawn));

                assertThat(pawn.isMovable(E_TWO, E_FOUR, boardSnapShot)).isTrue();
            }

            @Test
            void 움직일_수_없으면_false_반환한다() {
                final Pawn pawn = new Pawn(WHITE);
                final BoardSnapShot boardSnapShot = BoardSnapShot.from(Map.of(
                        E_TWO, pawn,
                        E_FOUR, new Pawn(BLACK)
                ));

                assertThat(pawn.isMovable(E_TWO, E_FOUR, boardSnapShot)).isFalse();
            }
        }
    }

    @Test
    void 폰은_폰이다() {
        final Pawn pawn = new Pawn(WHITE);

        assertThat(pawn.isPawn()).isTrue();
    }
}
