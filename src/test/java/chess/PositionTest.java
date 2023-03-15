package chess;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PositionTest {
    @ParameterizedTest
    @CsvSource(value = {"2:1", "3:0", "4:-1"}, delimiter = ':')
    void should_File간_거리를_반환_when_File_2개를_입력받으면(String file, int expected) {
        //given
        Position startPosition = new Position(Rank.A, File.from(file));
        Position endPosition = new Position(Rank.A, File.THREE);

        //when
        int actual = endPosition.calculateFileDistance(startPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"b:1", "c:0", "d:-1"}, delimiter = ':')
    void should_Rank간_거리를_반환_when_Rank_2개를_입력받으면(String rank, int expected) {
        //given
        Position startPosition = new Position(Rank.from(rank), File.THREE);
        Position endPosition = new Position(Rank.C, File.THREE);

        //when
        int actual = endPosition.calculateRankDistance(startPosition);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_true를_반환_when_입렵받은_위치가_폰의_초기_위치라면() {
        //given
        Position position = new Position(Rank.C, File.TWO);
        final Team team = Team.WHITE;

        //when
        boolean actual = position.isInitialPawnPosition(team);

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void should_false를_반환_when_입렵받은_위치가_폰의_초기_위치가_아니라면() {
        //given
        Position position = new Position(Rank.C, File.THREE);
        final Team team = Team.WHITE;

        //when
        boolean actual = position.isInitialPawnPosition(team);

        //then
        assertThat(actual).isFalse();
    }
}