package chess.domain.position;

import static chess.domain.position.RankCoordinate.FIVE;
import static chess.domain.position.RankCoordinate.FOUR;
import static chess.domain.position.RankCoordinate.ONE;
import static chess.domain.position.RankCoordinate.SIX;
import static chess.domain.position.RankCoordinate.THREE;
import static chess.domain.position.RankCoordinate.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankCoordinateTest {

    @Test
    void 랭크와_랭크_사이의_좌표들을_반환한다() {
        assertThat(ONE.betweenRanks(SIX)).containsExactly(TWO, THREE, FOUR, FIVE);
    }
}
