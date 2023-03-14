package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {
    @ParameterizedTest
    @CsvSource(value = {"2:1", "3:0", "4:-1"}, delimiter = ':')
    void should_File간_거리를_반환_when_File_2개를_입력받으면(String file, int expected) {
        //given
        Position startPosition = new Position("a", file);
        Position endPosition = new Position("a", "3");

        //when
        int actual = endPosition.calculateFileDistance(startPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b:1", "c:0", "d:-1"}, delimiter = ':')
    void should_Rank간_거리를_반환_when_Rank_2개를_입력받으면(String rank, int expected) {
        //given
        Position startPosition = new Position(rank, "3");
        Position endPosition = new Position("c", "3");

        //when
        int actual = endPosition.calculateRankDistance(startPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

}