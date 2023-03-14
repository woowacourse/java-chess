package chess.domain;

import static chess.domain.RankType.of;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE:SIDE_RANK", "EIGHT:SIDE_RANK", "TWO:PAWN_RANK", "SEVEN:PAWN_RANK",
            "THREE:EMPTY_RANK"}, delimiter = ':')
    void 행의_위치에_따라_RankType을_생성한다(RankCoordinate rankCoordinate, RankType expect) {
        assertThat(of(rankCoordinate)).isEqualTo(expect);
    }

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
