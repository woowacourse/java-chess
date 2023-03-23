package chess.model.position;

import static chess.helper.PositionFixture.A1;
import static chess.model.position.File.*;
import static chess.model.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RankPositionTest {

    @Test
    @DisplayName("create()는 File을 주면 해당 File과 Rank를 조합한 결과를 RankPosition으로 반환한다")
    void create_givenFile_thenReturnTotalRankPosition() {
        final RankPosition rankPosition = assertDoesNotThrow(() -> RankPosition.create(A));

        assertThat(rankPosition).isExactlyInstanceOf(RankPosition.class);
    }

    @Test
    @DisplayName("findByRank()는 Rank를 주면 해당 Rank에 해당하는 Position을 반환한다")
    void findByRank_givenRank_thenReturnPosition() {
        final RankPosition rankPosition = RankPosition.create(A);

        final Position actual = rankPosition.findByRank(FIRST);

        assertThat(actual).isEqualTo(A1);
    }
}
