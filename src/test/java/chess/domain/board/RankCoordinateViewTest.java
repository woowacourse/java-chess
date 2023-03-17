package chess.domain.board;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class RankCoordinateViewTest {

    @ParameterizedTest
    @CsvSource(value = {"ONE:true", "TWO:true", "THREE:false", "EIGHT:false"}, delimiter = ':')
    void White팀의_랭크인지_확인할_수_있다(RankCoordinate rankCoordinate, boolean expect) {
        assertThat(rankCoordinate.isWhiteRank()).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN:true", "EIGHT:true", "TWO:false", "SIX:false"}, delimiter = ':')
    void Black팀의_랭크인지_확인할_수_있다(RankCoordinate rankCoordinate, boolean expect) {
        assertThat(rankCoordinate.isBlackRank()).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"ONE:true", "TWO:false", "THREE:false", "EIGHT:true"}, delimiter = ':')
    void Side_랭크인지_확인할_수_있다(RankCoordinate rankCoordinate, boolean expect) {
        assertThat(rankCoordinate.isSideRank()).isEqualTo(expect);
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN:true", "EIGHT:false", "TWO:true", "SIX:false"}, delimiter = ':')
    void Pawn_랭크인지_확인할_수_있다(RankCoordinate rankCoordinate, boolean expect) {
        assertThat(rankCoordinate.isPawnRank()).isEqualTo(expect);
    }
}
