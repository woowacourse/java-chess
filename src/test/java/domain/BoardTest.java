package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("체스판을 생성한다")
    public void testCreate() {
        //given
        //when
        final Board board = new Board();

        //then
        assertThat(board).extracting("lines")
            .asList()
            .containsExactly(
                Line.blackBack(),
                Line.blackFront(),
                Line.empty(),
                Line.empty(),
                Line.empty(),
                Line.empty(),
                Line.whiteFront(),
                Line.whiteBack()
            );
    }
}
