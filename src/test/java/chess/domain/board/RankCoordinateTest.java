package chess.domain.board;

import static chess.domain.board.RankCoordinate.FIVE;
import static chess.domain.board.RankCoordinate.FOUR;
import static chess.domain.board.RankCoordinate.ONE;
import static chess.domain.board.RankCoordinate.SIX;
import static chess.domain.board.RankCoordinate.THREE;
import static chess.domain.board.RankCoordinate.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankCoordinateTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE:WHITE", "TWO:WHITE", "THREE:EMPTY", "EIGHT:BLACK"}, delimiter = ':')
    void 랭크로_어느_팀인지_확인할_수_있다(RankCoordinate rankCoordinate, Color expect) {
        assertThat(rankCoordinate.getInitColor()).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:SIDE_RANK", "TWO:PAWN_RANK", "THREE:EMPTY_RANK", "EIGHT:SIDE_RANK"}, delimiter = ':')
    void Side_랭크인지_확인할_수_있다(RankCoordinate rankCoordinate, RankType expect) {
        assertThat(rankCoordinate.getRankType()).isEqualTo(expect);
    }

    @Test
    void 랭크와_랭크_사이의_좌표들을_반환한다() {
        assertThat(ONE.betweenRanks(SIX)).containsExactly(TWO, THREE, FOUR, FIVE);
    }
}
