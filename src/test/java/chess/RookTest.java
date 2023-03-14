package chess;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RookTest {


    @ParameterizedTest
    @CsvSource(value = {"c:1", "a:3", "c:8", "h:3"}, delimiter = ':')
    void should_true반환_when_움직일_수_있는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(rank, file);
        Position endPosition = new Position("c", "3");
        Rook rook = new Rook();

        //when
        boolean actual = rook.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"a:1", "a:5", "h:4", "c:3"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(rank, file);
        Position endPosition = new Position("c", "3");
        Rook rook = new Rook();

        //when
        boolean actual = rook.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }

}