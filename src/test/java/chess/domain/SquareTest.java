package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SquareTest {
    @Test
    @DisplayName("Rank와 File을 받아서 생성한다.")
    void getAllSquares() {
        // expected
        List<Square> squares = Square.getAllSquares();

        assertThat(squares).hasSize(64);
    }

    @Test
    @DisplayName("Rank와 File이 동일하면 같은 Square를 반환한다.")
    void of() {
        // given
        Rank rank = Rank.ONE;
        File file = File.A;

        // when
        Square square = Square.of(file, rank);
        Square sameSquare = Square.of(file, rank);


        // expected
        assertThat(square).isSameAs(sameSquare);
    }

    @Test
    @DisplayName("대상 Square가 직선 위치인지 확인한다.")
    void isStraight() {
        // given
        Square sourceSquare = Square.of(File.A, Rank.ONE);
        Square targetSquare1 = Square.of(File.A, Rank.EIGHT);
        Square targetSquare2 = Square.of(File.H, Rank.ONE);

        // expected
        assertThat(sourceSquare.isStraight(targetSquare1)).isTrue();
        assertThat(sourceSquare.isStraight(targetSquare2)).isTrue();
    }

    @Test
    @DisplayName("대상 Square가 대각선 위치인지 확인한다.")
    void isDiagonal() {
        // given
        Square sourceSquare = Square.of(File.C, Rank.THREE);
        Square targetSquare1 = Square.of(File.H, Rank.EIGHT);
        Square targetSquare2 = Square.of(File.A, Rank.FIVE);

        // expected
        assertThat(sourceSquare.isDiagonal(targetSquare1)).isTrue();
        assertThat(sourceSquare.isDiagonal(targetSquare2)).isTrue();
    }

    @Test
    @DisplayName("대상 Square가 나이트로 갈 수 있는 위치인지 확인한다.")
    void isKnightMovable() {
        // given
        Square sourceSquare = Square.of(File.C, Rank.THREE);
        Square targetSquare1 = Square.of(File.A, Rank.FOUR);
        Square targetSquare2 = Square.of(File.D, Rank.FIVE);

        // expected
        assertThat(sourceSquare.isKnightMovable(targetSquare1)).isTrue();
        assertThat(sourceSquare.isKnightMovable(targetSquare2)).isTrue();
    }

    @Test
    @DisplayName("대상 Square가 같은 File인지 확인한다.")
    void isSameFile() {
        // given
        Square sourceSquare = Square.of(File.A, Rank.ONE);
        Square targetSquare1 = Square.of(File.A, Rank.TWO);
        Square targetSquare2 = Square.of(File.A, Rank.EIGHT);

        // expected
        assertThat(sourceSquare.isSameFile(targetSquare1)).isTrue();
        assertThat(sourceSquare.isSameFile(targetSquare2)).isTrue();
    }
}
