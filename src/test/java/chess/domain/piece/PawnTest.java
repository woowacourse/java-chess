package chess.domain.piece;

import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnTest {

    public static final Position B5 = new Position(File.B, Rank.FIVE);
    public static final Position A4 = new Position(File.A, Rank.FOUR);
    public static final Position B6 = new Position(File.B, Rank.SIX);
    public static final Position B2 = new Position(File.B, Rank.TWO);
    public static final Position B4 = new Position(File.B, Rank.FOUR);
    public static final Position B7 = new Position(File.B, Rank.SEVEN);
    public static final Position B3 = new Position(File.B, Rank.THREE);
    public static final Position E4 = new Position(File.E, Rank.FOUR);
    public static final Position C4 = new Position(File.C, Rank.FOUR);
    public static final Position C5 = new Position(File.C, Rank.FIVE);
    public static final Position C6 = new Position(File.C, Rank.SIX);
    public static final Position D5 = new Position(File.D, Rank.FIVE);

    @Test
    @DisplayName("흑색 폰의 대각선 경로를 계산한다.")
    void computePath_Black() {
        final var pawn = new Pawn(Color.BLACK);

        assertThat(pawn.computePath(B5, A4)).containsExactly(A4);
    }

    @Test
    @DisplayName("백색 폰의 한칸 전진 경로를 계산한다.")
    void computePath_White() {
        final var pawn = new Pawn(Color.WHITE);

        assertThat(pawn.computePath(B5, B6)).containsExactly(B6);
    }

    @Test
    @DisplayName("백색 폰의 두칸 전진 경로를 계산한다.")
    void computePath_WhiteTwo() {
        final var pawn = new Pawn(Color.WHITE);

        assertThat(pawn.computePath(B2, B4)).containsExactlyInAnyOrder(B3, B4);
    }

    @Test
    @DisplayName("폰이 시작위치가 아니면 두칸 전진할 수 없다.")
    void computePath_WhiteTwoException() {
        final var pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.computePath(B5, B7))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("흑색 폰의 두칸 전진 경로를 계산한다.")
    void computePath_BlackTwo() {
        final var pawn = new Pawn(Color.BLACK);

        assertThat(pawn.computePath(B7, B5)).containsExactlyInAnyOrder(B6, B5);
    }

    @Test
    @DisplayName("폰이 초기위치가 아니면 두칸 전진할 수 없다.")
    void computePath_BlackTwoException() {
        final var pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.computePath(B5, B3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효하지 않은 위치를 받으면 예외가 발생한다.")
    void computePath_BlackIllegalTarget_Exception() {
        final var pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.computePath(B5, E4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("흑색 폰은 뒤로 갈 수 없다.")
    void computePath_blackBackward_exception() {
        final var pawn = new Pawn(Color.BLACK);

        assertThatThrownBy(() -> pawn.computePath(B5, B6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("백색 폰은 뒤로 갈 수 없다.")
    void computePath_whiteBackward_exception() {
        final var pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.computePath(B5, B4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효하지 않은 위치를 받으면 예외가 발생한다.")
    void computePath_WhiteIllegalTarget_Exception() {
        final var pawn = new Pawn(Color.WHITE);

        assertThatThrownBy(() -> pawn.computePath(B5, E4))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 대각선 위치에 기물이 있으면 이동할 수 있다.")
    void canMove_diagonalExist_true() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = D5;

        assertThat(pawn.canMove(Map.of(target, false), source, target)).isTrue();
    }

    @Test
    @DisplayName("폰은 전진할 경로에 기물이 없으면 이동할 수 있다.")
    void canMove_forwardEmpty_true() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = C5;

        assertThat(pawn.canMove(Map.of(target, true), source, target)).isTrue();
    }

    @Test
    @DisplayName("폰은 대각선위치에 기물이 없으면 이동할 수 없다.")
    void canMove_diagonalEmpty_false() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = D5;

        assertThat(pawn.canMove(Map.of(target, true), source, target)).isFalse();
    }

    @Test
    @DisplayName("폰은 대각선위치에 기물이 있으면 이동할 수 있다.")
    void canMove_diagonalExist_true2() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = D5;

        assertThat(pawn.canMove(Map.of(target, false), source, target)).isTrue();
    }

    @Test
    @DisplayName("폰은 두칸 전진 경로에 기물이 있으면 이동할 수 없다.")
    void canMove_forwardTwoExist_false() {
        final var pawn = new Pawn(Color.WHITE);

        final var source = C4;
        final var target = C6;

        assertThat(pawn.canMove(Map.of(C5, true, C6, false), source, target)).isFalse();
    }
}
