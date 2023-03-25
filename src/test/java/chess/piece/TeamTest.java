package chess.piece;

import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TeamTest {
    @ParameterizedTest
    @CsvSource(value = {"WHITE,true","BLACK,false"})
    void 자신이_화이트팀이면_true를_반환하고_블랙팀이면_false를_반환한다(Team team, boolean isWhite) {
        boolean isWhiteTeam = team.isSameTeam(Team.WHITE);
        assertThat(isWhiteTeam).isEqualTo(isWhite);
    }
}
