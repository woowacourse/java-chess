package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @Nested
    class 생성 {
        @Test
        void should_알맞은객체를생성한다_when_문자열이입력됐을때() {
            //given
            String rawPosition = "c2";
            Position expected = Position.of(File.C, Rank.TWO);

            //when
            Position actual = Position.from(rawPosition);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }

    @Nested
    class 거리계산 {
        @ParameterizedTest
        @CsvSource(value = {"2:1", "3:0", "4:-1"}, delimiter = ':')
        void should_File간_거리를_반환_when_File_2개를_입력받으면(int file, int expected) {
            //given
            Position source = Position.of(File.A, Rank.from(file));
            Position destination = Position.of(File.A, Rank.THREE);

            //when
            int actual = destination.calculateFileDistance(source);

            //then
            assertThat(actual).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvSource(value = {"b:1", "c:0", "d:-1"}, delimiter = ':')
        void should_Rank간_거리를_반환_when_Rank_2개를_입력받으면(String rank, int expected) {
            //given
            Position source = Position.of(File.valueOf(rank.toUpperCase()), Rank.THREE);
            Position destination = Position.of(File.C, Rank.THREE);

            //when
            int actual = destination.calculateRankDistance(source);

            //then
            assertThat(actual).isEqualTo(expected);
        }
    }

}