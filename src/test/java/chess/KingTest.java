package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    @ParameterizedTest
    @CsvSource(value = {"b:2", "b:3", "b:4", "d:2", "d:3", "d:4", "c:4", "c:2"}, delimiter = ':')
    void should_true반환_when_움직일_수_있는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position("c", "3");
        Position endPosition = new Position(rank, file);
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:3", "c:5", "a:3"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(rank, file);
        Position endPosition = new Position("c", "3");
        King king = new King(Team.WHITE);

        //when
        boolean actual = king.isMovable(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }
}