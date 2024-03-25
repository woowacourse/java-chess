package chess.domain.piece;

import static chess.fixture.PositionFixtures.A2;
import static chess.fixture.PositionFixtures.A7;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.position.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TeamTest {
    @DisplayName("방향이 주어질 때 팀의 전진 방향에 포함된 방향인지 알 수 있다")
    @Test
    void should_CheckDirectionMatchWithTeamForwardDirection_When_DirectionIsGiven() {
        assertAll(
                () -> assertThat(Team.WHITE.isTeamForwardDirectionsContains(Direction.N)).isTrue(),
                () -> assertThat(Team.WHITE.isTeamForwardDirectionsContains(Direction.NW)).isTrue(),
                () -> assertThat(Team.WHITE.isTeamForwardDirectionsContains(Direction.NE)).isTrue(),
                () -> assertThat(Team.BLACK.isTeamForwardDirectionsContains(Direction.S)).isTrue(),
                () -> assertThat(Team.BLACK.isTeamForwardDirectionsContains(Direction.SW)).isTrue(),
                () -> assertThat(Team.BLACK.isTeamForwardDirectionsContains(Direction.SE)).isTrue()
        );
    }

    @DisplayName("위치가 주어질 때 팀의 초기 폰 랭크와 같은 랭크인지 확인할 수 있다")
    @Test
    void should_CheckPositionRankIsEqual_When_PositionIsGiven() {
        assertAll(
                () -> assertThat(Team.WHITE.isPositionOnTeamInitialPawnRank(A2)),
                () -> assertThat(Team.BLACK.isPositionOnTeamInitialPawnRank(A7))
        );
    }

    @DisplayName("팀이 주어진 경우 상태 팀을 반환할 수 있다")
    @Test
    void should_CheckOtherTeam_When_TeamIsGiven() {
        assertAll(
                () -> assertThat(Team.WHITE.otherTeam()).isEqualTo(Team.BLACK),
                () -> assertThat(Team.BLACK.otherTeam()).isEqualTo(Team.WHITE)
        );
    }
}
