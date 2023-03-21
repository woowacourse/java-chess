package chess.model.position;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RankTest {

    @ParameterizedTest(name = "value={0} 가 주어지면 rank={1} 반환된다.")
    @CsvSource({
            "1,FIRST", "2,SECOND", "3,THIRD", "4,FOURTH",
            "5,FIFTH", "6,SIXTH", "7,SEVENTH", " 8,EIGHTH"
    })
    void findRank_givenIntValue_thenReturnFile(final int value, final Rank rank) {
        // when, then
        assertThat(Rank.findRank(value)).isSameAs(rank);
    }
}
