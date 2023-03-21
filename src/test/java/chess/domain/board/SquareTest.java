package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.ONE;
import static chess.util.SquareFixture.A_ONE;
import static chess.util.SquareFixture.A_SEVEN;
import static chess.util.SquareFixture.A_SIX;
import static chess.util.SquareFixture.A_THREE;
import static chess.util.SquareFixture.A_TWO;
import static chess.util.SquareFixture.B_ONE;
import static chess.util.SquareFixture.B_TWO;
import static chess.util.SquareFixture.C_THREE;
import static chess.util.SquareFixture.H_EIGHT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.piece.strategy.vector.SlidingVector;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SquareTest {

    @Test
    void 파일과_랭크가_같으면_같은_체크_칸이다() {
        assertThat(A_ONE).isEqualTo(new Square(A, ONE));
    }

    @Test
    void 체스칸으로_변환할_수_없다면_예외를_던진다() {
        final String input = "i1";

        assertThatIllegalArgumentException()
                .isThrownBy(() -> Square.from(input));
    }

    @Nested
    class next_메서드는 {

        @Test
        void 다음칸으로_이동할_수_있으면_다음칸을_반환한다() {
            assertThat(A_ONE.next(SlidingVector.NORTHEAST)).isEqualTo(B_TWO);
        }

        @Test
        void 다음칸으로_이동할_수_없으면_예외를_던진다() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> H_EIGHT.next(SlidingVector.NORTHEAST));
        }
    }

    @Nested
    class isInitPawnPosition_메서드는 {

        @Nested
        class BLACK_인경우 {

            @Test
            void 일곱번째_랭크라면_true_반환한다() {
                assertThat(A_SEVEN.isInitPawnPosition(true)).isTrue();
            }

            @Test
            void 일곱번째_랭크가_아니라면_false_반환한다() {
                assertThat(A_SIX.isInitPawnPosition(true)).isFalse();
            }
        }

        @Nested
        class WHITE_인경우 {

            @Test
            void 두번째_랭크라면_true_반환한다() {
                assertThat(A_TWO.isInitPawnPosition(false)).isTrue();
            }

            @Test
            void 두번째_랭크가_아니라면_false_반환한다() {
                assertThat(A_THREE.isInitPawnPosition(false)).isFalse();
            }
        }
    }

    @Test
    void 체스칸_사이의_파일차이를_계산한다() {
        assertThat(C_THREE.calculateDistanceFile(B_ONE)).isEqualTo(1);
    }

    @Test
    void 체스칸_사이의_랭크차이를_계산한다() {
        assertThat(C_THREE.calculateDistanceRank(B_ONE)).isEqualTo(2);
    }
}
