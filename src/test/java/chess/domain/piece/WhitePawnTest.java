package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static chess.domain.piece.PawnTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WhitePawnTest {

    @Test
    @DisplayName("백색 폰의 한칸 전진 경로를 계산한다.")
    void computePath_White() {
        final var pawn = new WhitePawn();

        assertThat(pawn.computePathWithValidate(B5, B6)).containsExactly(B6);
    }

    @Test
    @DisplayName("백색 폰의 두칸 전진 경로를 계산한다.")
    void computePath_WhiteTwo() {
        final var pawn = new WhitePawn();

        assertThat(pawn.computePathWithValidate(B2, B4)).containsExactlyInAnyOrder(B3, B4);
    }

    @Test
    @DisplayName("폰이 시작위치가 아니면 두칸 전진할 수 없다.")
    void computePath_WhiteTwoException() {
        final var pawn = new WhitePawn();

        assertThatThrownBy(() -> pawn.computePathWithValidate(B5, B7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효하지 않은 위치를 받으면 예외가 발생한다.")
    void computePath_WhiteIllegalTarget_Exception() {
        final var pawn = new WhitePawn();

        assertThatThrownBy(() -> pawn.computePathWithValidate(B5, E4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 대각선 위치에 기물이 있으면 이동할 수 있다.")
    void canMove_diagonalExist_true() {
        final var pawn = new WhitePawn();

        final var source = C4;
        final var target = D5;

        assertThat(pawn.canMoveWithValidate(Map.of(target, false), source, target)).isTrue();
    }

    @Test
    @DisplayName("백색 폰은 아래로 갈 수 없다.")
    void canMove_down_false() {
        final var pawn = new WhitePawn();

        final var source = C5;
        final var target = C4;

        assertThatThrownBy(() -> pawn.computePathWithValidate(source, target)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("백색 폰은 점수가 1점이다.")
    @Test
    void getScore_blackPawnOne() {
        final var pawn = new WhitePawn();

        assertThat(pawn.getScore(Color.WHITE)).isEqualTo(1);
    }

    @DisplayName("다른색의 폰은 점수가 0점이다.")
    @Test
    void getScore_otherPawnZero() {
        final var pawn = new WhitePawn();

        assertThat(pawn.getScore(Color.BLACK)).isEqualTo(0);
    }
}
