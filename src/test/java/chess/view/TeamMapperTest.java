package chess.view;

import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class TeamMapperTest {

    @Test
    void 팀을_전달하면_이에_맞는_객체를_반환한다() {
        TeamMapper teamMapper = TeamMapper.from(Team.WHITE);

        assertThat(teamMapper).isEqualTo(TeamMapper.WHITE);
    }

    @ParameterizedTest
    @CsvSource(value = {"흰색팀:WHITE", "검은팀:BLACK", "없음:EMPTY"}, delimiter = ':')
    void 팀에_대한_메세지를_입력하면_이에_해당하는_객체를_반환한다(String teamView, TeamMapper expected) {
        TeamMapper teamMapper = TeamMapper.from(teamView);

        assertThat(teamMapper).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"흰색 팀", " ", "a", "흰색팀1"})
    void 체스_말에_대한_메세지를_잘못_입력하면_예외가_발생한다(String teamView) {
        assertThatThrownBy(() -> TeamMapper.from(teamView))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 팀을 입력했습니다.");
    }
}
