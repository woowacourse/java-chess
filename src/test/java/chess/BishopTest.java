package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {


    @ParameterizedTest
    @CsvSource(value = {"a:1", "a:5", "e:1", "h:8"}, delimiter = ':')
    void should_true반환_when_움직일_수_있는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(Rank.from(rank), File.from(file));
        Position endPosition = new Position(Rank.C, File.THREE);
        Bishop bishop = new Bishop(Team.WHITE);

        //when
        boolean actual = bishop.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:1", "a:3", "c:8", "h:3", "c:3"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(Rank.from(rank), File.from(file));
        Position endPosition = new Position(Rank.C, File.THREE);
        Bishop bishop = new Bishop(Team.WHITE);

        //when
        boolean actual = bishop.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }


}