package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.piece.PawnTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlackPawnTest {


    @Test
    @DisplayName("흑색 폰의 대각선 경로를 계산한다.")
    void computePath_Black() {
        final var pawn = new BlackPawn();

        assertThat(pawn.computePathWithValidate(B5, A4)).containsExactly(A4);
    }

    @Test
    @DisplayName("흑색 폰의 두칸 전진 경로를 계산한다.")
    void computePath_BlackTwo() {
        final var pawn = new BlackPawn();

        assertThat(pawn.computePathWithValidate(B7, B5)).containsExactlyInAnyOrder(B6, B5);
    }

    @Test
    @DisplayName("폰이 초기위치가 아니면 두칸 전진할 수 없다.")
    void computePath_BlackTwoException() {
        final var pawn = new BlackPawn();

        assertThatThrownBy(() -> pawn.computePathWithValidate(B5, B3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효하지 않은 위치를 받으면 예외가 발생한다.")
    void computePath_BlackIllegalTarget_Exception() {
        final var pawn = new BlackPawn();

        assertThatThrownBy(() -> pawn.computePathWithValidate(B5, E4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("흑색 폰은 위로 갈 수 없다.")
    void canMove_up_false() {
        final var pawn = new BlackPawn();

        final var source = C4;
        final var target = C5;

        assertThatThrownBy(() -> pawn.computePathWithValidate(source, target)).isInstanceOf(IllegalArgumentException.class);
    }
}
