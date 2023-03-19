package chess.domain.board;

import org.junit.jupiter.api.Test;

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

}