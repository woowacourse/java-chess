package chess.domain.board;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static chess.domain.board.RankType.of;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE:SIDE_RANK", "EIGHT:SIDE_RANK", "TWO:PAWN_RANK", "SEVEN:PAWN_RANK",
            "THREE:EMPTY_RANK"}, delimiter = ':')
    void 행의_위치에_따라_RankType을_생성한다(RankCoordinate rankCoordinate, RankType expect) {
        assertThat(of(rankCoordinate)).isEqualTo(expect);
    }
}
