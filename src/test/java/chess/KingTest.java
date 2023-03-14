package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    void should_true반환_when_움직일_수_있는_위치라면() {
        //given
        Position startPosition = new Position("a", "1");
        Position endPosition = new Position("a", "2");
        King king = new King();

        //when
        boolean actual = king.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

}