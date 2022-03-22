package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TeamColorTest {

    @ParameterizedTest
    @DisplayName("팀 컬러가 블랙이면 대문자 이름반환ㅎ하고, 화이트면 소문자 이름을 반환한다.")
    @CsvSource({"BLACK, P", "WHITE, p"})
    void convertSymbolByTeamColor(final TeamColor teamColor, final String expected) {
        //given
        String symbol = "p";
        //when
        final String actual = teamColor.convertSymbolByTeamColor(symbol);
        //then
        assertThat(actual).isEqualTo(expected);
    }

}