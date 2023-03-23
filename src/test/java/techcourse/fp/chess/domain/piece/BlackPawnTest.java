package techcourse.fp.chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static techcourse.fp.chess.domain.PieceFixtures.BLACK_PAWN;
import static techcourse.fp.chess.domain.PieceFixtures.EMPTY;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A4;
import static techcourse.fp.chess.domain.PositionFixtures.A5;
import static techcourse.fp.chess.domain.PositionFixtures.A6;
import static techcourse.fp.chess.domain.PositionFixtures.A7;
import static techcourse.fp.chess.domain.PositionFixtures.H2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class BlackPawnTest {

    private final BlackPawn blackPawn = BlackPawn.create();

    @DisplayName("file이 다르면 예외가 발생한다.")
    @Test
    void fail_test() {
        assertThatThrownBy(() -> blackPawn.findPath(H2, A2, BLACK_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    @DisplayName("같은 좌표는 예외가 발생한다.")
    @Test
    void fail_test2() {
        assertThatThrownBy(() -> blackPawn.findPath(A2, A2, BLACK_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    @DisplayName("rank의 차이가 3 이상이면 예외가 발생한다.")
    @Test
    void fail_test3() {
        assertThatThrownBy(() -> blackPawn.findPath(A5, A1, BLACK_PAWN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
    }

    @DisplayName("한 칸 전진이 ")
    @Nested
    class OneStepMove {

        @DisplayName("성공한다.")
        @Test
        void fail_test4() {
            assertThat(blackPawn.findPath(A7, A6, EMPTY)).isEmpty();
        }

        @DisplayName("실패한다 - 도착지에 아군 기물 존재")
        @Test
        void fail_test5() {
            assertThatThrownBy(() -> blackPawn.findPath(A7, A6, BLACK_PAWN))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("실패한다 - 도착지에 적 기물 존재")
        @Test
        void fail_test6() {
            assertThatThrownBy(() -> blackPawn.findPath(A7, A6, WHITE_PAWN))
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
            assertThat(blackPawn.findPath(A7, A5, EMPTY)).containsExactly(A6);
        }

        @DisplayName("실패한다. - 도착지에 아군 기물 존재")
        @Test
        void fail_test8() {
            assertThatThrownBy(() -> blackPawn.findPath(A7, A5, BLACK_PAWN))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("실패한다. - 도착지에 적 기물 존재")
        @Test
        void fail_test9() {
            assertThatThrownBy(() -> blackPawn.findPath(A7, A5, WHITE_PAWN))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("실패 한다. - 폰이 최초 이동이 아닌 경우")
        @Test
        void fail_by_start_position() {
            assertThatThrownBy(() -> blackPawn.findPath(A6, A4, EMPTY))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }
    }
}
