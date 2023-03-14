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
}
