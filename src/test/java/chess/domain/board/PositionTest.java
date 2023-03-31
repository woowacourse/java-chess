package chess.domain.board;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings({"NonAsciiCharacters","SpellCheckingInspection"})
class PositionTest {

    @Test
    void 입력한_정보값과_일치하는_캐싱된_Position객체를_반환한다() {
        for(Rank rank : Rank.getOrderedRanks()) {
            for(Column column : Column.getOrderedColumns()) {
                assertDoesNotThrow(()-> Position.of(column, rank));
            }
        }
    }

    @ParameterizedTest
    @CsvSource(value = {"EIGHT,0","SEVEN,-1","SIX,-2","FIVE,-3","FOUR,-4","THREE,-5", "TWO,-6" ,"ONE,-7"})
    void 입력된_Position의_Rank인덱스값에서_현재_Position의_Rank인덱스값을_뺀_값을_반환한다(Rank otherRank, int expectedGap) {
        Position otherPosition = Position.of(Column.A, otherRank);
        Position currentPosition = Position.of(Column.A, Rank.EIGHT);

        assertThat(currentPosition.findGapOfRank(otherPosition))
                .isEqualTo(expectedGap);
    }

    @ParameterizedTest
    @CsvSource(value = {"A,0","B,1","C,2","D,3","E,4","F,5", "G,6" ,"H,7"})
    void 입력된_Position의_Column인덱스값에서_현재_Position의_Column인덱스값을_뺀_값을_반환한다(Column otherColumn, int expectedGap) {
        Position otherPosition = Position.of(otherColumn, Rank.EIGHT);
        Position currentPosition = Position.of(Column.A, Rank.EIGHT);

        assertThat(currentPosition.findGapOfColumn(otherPosition))
                .isEqualTo(expectedGap);
    }
}