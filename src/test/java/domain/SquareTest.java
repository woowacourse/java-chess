package domain;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.EmptyPiece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {

    @Test
    @DisplayName("빈 칸 생성을 테스트")
    public void testEmpty() {
        //given
        //when
        final Square square = Square.empty();

        //then
        assertThat(square).extracting("piece").isEqualTo(EmptyPiece.create());
    }
}
