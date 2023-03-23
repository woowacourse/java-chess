package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"2:1", "3:0", "4:-1"}, delimiter = ':')
    void File_2개를_입력받으면_File간_거리를_반환(String file, int expected) {
        //given
        Position startPosition = Position.of(File.A, Rank.from(file));
        Position endPosition = Position.of(File.A, Rank.THREE);

        //when
        int actual = endPosition.calculateFileDistance(startPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b:1", "c:0", "d:-1"}, delimiter = ':')
    void Rank_2개를_입력받으면_Rank간_거리를_반환(String rank, int expected) {
        //given
        Position startPosition = Position.of(File.from(rank), Rank.THREE);
        Position endPosition = Position.of(File.C, Rank.THREE);

        //when
        int actual = endPosition.calculateRankDistance(startPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    //TODO position.move() 테스트
    //TODO position.of, from 테스트

}
