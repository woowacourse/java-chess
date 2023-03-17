package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.A4;
import static techcourse.fp.chess.domain.PositionFixtures.A5;
import static techcourse.fp.chess.domain.PositionFixtures.A6;
import static techcourse.fp.chess.domain.PositionFixtures.A7;
import static techcourse.fp.chess.domain.PositionFixtures.H2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Pawn;

class PawnTest {

    @Nested
    public class WhitePawnTest {
        private final Pawn pawn = Pawn.createByColor(Color.WHITE);

        @DisplayName("file이 다르면 예외가 발생한다.")
        @Test
        void fail_test() {
            assertThatThrownBy(() -> pawn.findPath(A2, H2, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("같은 좌표는 예외가 발생한다.")
        @Test
        void fail_test2() {
            assertThatThrownBy(() -> pawn.findPath(A2, A2, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("rank의 차이가 3 이상이면 예외가 발생한다.")
        @Test
        void fail_test3() {
            assertThatThrownBy(() -> pawn.findPath(A2, A5, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("한 칸 전진이 ")
        @Nested
        class OneStepMove {

            @DisplayName("성공한다.")
            @Test
            void fail_test4() {
                assertThat(pawn.findPath(A2, A3, Color.EMPTY)).isEmpty();
            }

            @DisplayName("실패한다 - 도착지에 아군 기물 존재")
            @Test
            void fail_test5() {
                assertThatThrownBy(() -> pawn.findPath(A2, A3, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패한다 - 도착지에 적 기물 존재")
            @Test
            void fail_test6() {
                assertThatThrownBy(() -> pawn.findPath(A2, A3, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

        }

        @DisplayName("두 칸 전진이 ")
        @Nested
        class TwoStepMove {

            @DisplayName("성공한다.")
            @Test
            void fail_test7() {
                assertThat(pawn.findPath(A2, A4, Color.EMPTY)).containsExactly(A3);
            }

            @DisplayName("실패 한다. - 도착지에 아군 기물 존재")
            @Test
            void fail_test8() {
                assertThatThrownBy(() -> pawn.findPath(A2, A4, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패 한다. - 도착지에 적 기물 존재")
            @Test
            void fail_test9() {
                assertThatThrownBy(() -> pawn.findPath(A2, A4, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패 한다. - 폰이 최초 이동이 아닌 경우")
            @Test
            void fail_by_start_position() {
                assertThatThrownBy(() -> pawn.findPath(A3, A5, Color.EMPTY))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }
        }
    }

    @Nested
    public class BlackPawnTest {
        private final Pawn pawn = Pawn.createByColor(Color.BLACK);

        @DisplayName("file이 다르면 예외가 발생한다.")
        @Test
        void fail_test() {
            assertThatThrownBy(() -> pawn.findPath(H2, A2, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("같은 좌표는 예외가 발생한다.")
        @Test
        void fail_test2() {
            assertThatThrownBy(() -> pawn.findPath(A2, A2, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("rank의 차이가 3 이상이면 예외가 발생한다.")
        @Test
        void fail_test3() {
            assertThatThrownBy(() -> pawn.findPath(A5, A1, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("한 칸 전진이 ")
        @Nested
        class OneStepMove {

            @DisplayName("성공한다.")
            @Test
            void fail_test4() {
                assertThat(pawn.findPath(A7, A6, Color.EMPTY)).isEmpty();
            }

            @DisplayName("실패한다 - 도착지에 아군 기물 존재")
            @Test
            void fail_test5() {
                assertThatThrownBy(() -> pawn.findPath(A7, A6, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패한다 - 도착지에 적 기물 존재")
            @Test
            void fail_test6() {
                assertThatThrownBy(() -> pawn.findPath(A7, A6, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }
        }

        @DisplayName("두 칸 전진이 ")
        @Nested
        class TwoStepMove {

            @DisplayName("성공한다.")
            @Test
            void fail_test7() {
                assertThat(pawn.findPath(A7, A5, Color.EMPTY)).containsExactly(A6);
            }

            @DisplayName("실패한다. - 도착지에 아군 기물 존재")
            @Test
            void fail_test8() {
                assertThatThrownBy(() -> pawn.findPath(A7, A5, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패한다. - 도착지에 적 기물 존재")
            @Test
            void fail_test9() {
                assertThatThrownBy(() -> pawn.findPath(A7, A5, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패 한다. - 폰이 최초 이동이 아닌 경우")
            @Test
            void fail_by_start_position() {
                assertThatThrownBy(() -> pawn.findPath(A6, A4, Color.EMPTY))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }
        }
    }
}
