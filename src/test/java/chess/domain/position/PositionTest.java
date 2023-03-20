package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {
    @ParameterizedTest
    @CsvSource(value = {"2:1", "3:0", "4:-1"}, delimiter = ':')
    void should_File간_거리를_반환_when_File_2개를_입력받으면(int file, int expected) {
        //given
        Position source = Position.of(Rank.A, File.from(file));
        Position destination = Position.of(Rank.A, File.THREE);

        //when
        int actual = destination.calculateFileDistance(source);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b:1", "c:0", "d:-1"}, delimiter = ':')
    void should_Rank간_거리를_반환_when_Rank_2개를_입력받으면(String rank, int expected) {
        //given
        Position source = Position.of(Rank.from(rank), File.THREE);
        Position destination = Position.of(Rank.C, File.THREE);

        //when
        int actual = destination.calculateRankDistance(source);

        //then
        assertThat(actual).isEqualTo(expected);
    }

}