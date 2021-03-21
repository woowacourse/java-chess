package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class TeamTypeTest {

    private static Stream<Arguments> getOppositeTeam() {
        return Stream.of(Arguments.of(TeamType.BLACK, TeamType.WHITE),
                Arguments.of(TeamType.WHITE, TeamType.BLACK));
    }

    @DisplayName("현재 팀과 반대되는 팀을 반환한다.")
    @ParameterizedTest
    @MethodSource("getOppositeTeam")
    void findOppositeTeam(TeamType currentTeamType, TeamType expectedTeamType) {
        TeamType oppositeTeamType = currentTeamType.findOppositeTeam();

        assertThat(oppositeTeamType).isEqualTo(expectedTeamType);
    }
}
