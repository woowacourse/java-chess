package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.InitialBlackPawn;
import chess.domain.piece.pawn.InitialWhitePawn;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    @Nested
    public class WhitePawnTest {
        private final Pawn whitePawn = WhitePawn.create();

        @DisplayName("file이 다르면 예외가 발생한다.")
        @Test
        void fail_test() {
            assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A2, PositionFixtures.H2, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("같은 좌표는 예외가 발생한다.")
        @Test
        void fail_test2() {
            assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A2, PositionFixtures.A2, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("rank의 차이가 3 이상이면 예외가 발생한다.")
        @Test
        void fail_test3() {
            assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A2, PositionFixtures.A5, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("한 칸 전진이 ")
        @Nested
        class OneStepMove {

            @DisplayName("성공한다.")
            @Test
            void fail_test4() {
                assertThat(whitePawn.findPath(PositionFixtures.A2, PositionFixtures.A3, Color.EMPTY)).isEmpty();
            }

            @DisplayName("실패한다 - 도착지에 아군 기물 존재")
            @Test
            void fail_test5() {
                assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A2, PositionFixtures.A3, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패한다 - 도착지에 적 기물 존재")
            @Test
            void fail_test6() {
                assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A2, PositionFixtures.A3, Color.BLACK))
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
                assertThat(InitialWhitePawn.create().findPath(PositionFixtures.A2, PositionFixtures.A4, Color.EMPTY)).containsExactly(PositionFixtures.A3);
            }

            @DisplayName("실패 한다. - 도착지에 아군 기물 존재")
            @Test
            void fail_test8() {
                assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A2, PositionFixtures.A4, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패 한다. - 도착지에 적 기물 존재")
            @Test
            void fail_test9() {
                assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A2, PositionFixtures.A4, Color.BLACK))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패 한다. - 폰이 최초 이동이 아닌 경우")
            @Test
            void fail_by_start_position() {
                assertThatThrownBy(() -> whitePawn.findPath(PositionFixtures.A3, PositionFixtures.A5, Color.EMPTY))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }
        }
    }

    @Nested
    public class BlackPawnTest {
        private final Pawn blackPawn = BlackPawn.create();

        @DisplayName("file이 다르면 예외가 발생한다.")
        @Test
        void fail_test() {
            assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.H2, PositionFixtures.A2, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("같은 좌표는 예외가 발생한다.")
        @Test
        void fail_test2() {
            assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.A2, PositionFixtures.A2, Color.BLACK))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("rank의 차이가 3 이상이면 예외가 발생한다.")
        @Test
        void fail_test3() {
            assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.A5, PositionFixtures.A1, Color.WHITE))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("한 칸 전진이 ")
        @Nested
        class OneStepMove {

            @DisplayName("성공한다.")
            @Test
            void fail_test4() {
                assertThat(blackPawn.findPath(PositionFixtures.A7, PositionFixtures.A6, Color.EMPTY)).isEmpty();
            }

            @DisplayName("실패한다 - 도착지에 아군 기물 존재")
            @Test
            void fail_test5() {
                assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.A7, PositionFixtures.A6, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패한다 - 도착지에 적 기물 존재")
            @Test
            void fail_test6() {
                assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.A7, PositionFixtures.A6, Color.WHITE))
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
                assertThat(InitialBlackPawn.create().findPath(PositionFixtures.A7, PositionFixtures.A5, Color.EMPTY)).containsExactly(PositionFixtures.A6);
            }

            @DisplayName("실패한다. - 도착지에 아군 기물 존재")
            @Test
            void fail_test8() {
                assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.A7, PositionFixtures.A5, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패한다. - 도착지에 적 기물 존재")
            @Test
            void fail_test9() {
                assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.A7, PositionFixtures.A5, Color.WHITE))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }

            @DisplayName("실패 한다. - 폰이 최초 이동이 아닌 경우")
            @Test
            void fail_by_start_position() {
                assertThatThrownBy(() -> blackPawn.findPath(PositionFixtures.A6, PositionFixtures.A4, Color.EMPTY))
                        .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
            }
        }
    }
}
