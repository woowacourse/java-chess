package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"a:1", "h:8", "a:3", "c:1", "e:1", "a:5", "c:8", "h:3"}, delimiter = ':')
    void should_true반환_when_움직일_수_있는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(Rank.from(rank), File.from(file));
        Position endPosition = new Position(Rank.C, File.THREE);
        Queen queen = new Queen(Team.WHITE);

        //when
        boolean actual = queen.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"c:3", "d:6", "h:4"}, delimiter = ':')
    void should_false반환_when_움직일_수_없는_위치라면(String rank, String file) {
        //given
        Position startPosition = new Position(Rank.from(rank), File.from(file));
        Position endPosition = new Position(Rank.C, File.THREE);
        Queen queen = new Queen(Team.WHITE);

        //when
        boolean actual = queen.canMove(startPosition, endPosition);

        //then
        assertThat(actual).isFalse();
    }


}