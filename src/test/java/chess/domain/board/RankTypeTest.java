package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankTypeTest {

    @Test
    void SideRank인지_확인할_수_있다() {
        RankType rankType = RankType.SIDE_RANK;

        assertThat(rankType.isSideRank()).isEqualTo(true);
    }

    @Test
    void PawnRank인지_확인할_수_있다() {
        RankType rankType = RankType.PAWN_RANK;

        assertThat(rankType.isPawnRank()).isEqualTo(true);
    }
}
