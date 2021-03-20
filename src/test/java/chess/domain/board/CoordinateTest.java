package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.player.TeamType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CoordinateTest {

    @DisplayName("Coordinate는 File과 Rank를 필드로 가지는 VO다")
    @Test
    void compareCoordinateEquality() {
        Coordinate coordinate = new Coordinate(File.A, Rank.EIGHT);
        Coordinate targetCoordinate = new Coordinate(File.A, Rank.EIGHT);

        assertThat(coordinate).isEqualTo(targetCoordinate);
    }

    @DisplayName("블랙 팀일 때 전진하는 경우, Rank가 1 감소한다.")
    @Test
    void moveForward_BlackTeam() {
        Coordinate coordinate = Coordinate.from("d7");
        Coordinate afterCoordinate = coordinate.moveForward(TeamType.BLACK);

        assertThat(afterCoordinate).isEqualTo(Coordinate.from("d6"));
    }
}