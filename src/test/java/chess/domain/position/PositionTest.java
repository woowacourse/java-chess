package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"2:1", "3:0", "4:-1"}, delimiter = ':')
    void Rank_2개를_입력받으면_Rank간_거리를_반환(String rank, int expected) {
        //given
        Position source = Position.of(File.A, Rank.from(rank));
        Position destination = Position.of(File.A, Rank.THREE);

        //when
        int actual = destination.calculateRankDistance(source);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b:1", "c:0", "d:-1"}, delimiter = ':')
    void File_2개를_입력받으면_File간_거리를_반환(String file, int expected) {
        //given
        Position source = Position.of(File.from(file), Rank.THREE);
        Position destination = Position.of(File.C, Rank.THREE);

        //when
        int actual = destination.calculateFileDistance(source);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Nested
    class isSameFile {

        @Test
        void 같은_세로열에_있는_좌표라면_true를_반환() {
            //given
            Position criteria = Position.of(File.C, Rank.FIVE);

            //when
            boolean actual = criteria.isSameFile(File.C);

            //then
            assertThat(actual).isTrue();
        }

        @Test
        void 같은_세로열에_있는_좌표가_아니라면_false를_반환() {
            //given
            Position criteria = Position.of(File.C, Rank.FIVE);

            //when
            boolean actual = criteria.isSameFile(File.D);

            //then
            assertThat(actual).isFalse();
        }
    }

    //TODO position.move() 테스트
    //TODO position.of, from 테스트
}
