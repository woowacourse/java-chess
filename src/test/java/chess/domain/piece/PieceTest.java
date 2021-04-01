package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.location.Location;
import chess.domain.team.Team;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    private Queen queen;

    @BeforeEach
    void setUp() {
        queen = Queen.of(Location.of(4, 4), Team.WHITE);
    }

    @DisplayName("경로 계산 - 오른쪽 위 대각선")
    @Test
    void findPath_rightUpDiagonal() {
        // given
        Location target = Location.of(8, 8);

        // when
        List<Location> pathToTarget = queen.findPath(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(5, 5),
            Location.of(6, 6),
            Location.of(7, 7)
        );
    }

    @DisplayName("경로 계산 - 왼쪽 아래 대각선")
    @Test
    void findPath_leftUpDiagonal() {
        // given
        Location target = Location.of(1, 1);

        // when
        List<Location> pathToTarget = queen.findPath(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(3, 3),
            Location.of(2, 2)
        );
    }

    @DisplayName("경로 계산 - 수직")
    @Test
    void findPath_vertical() {
        // given
        Location target = Location.of(4, 8);

        // when
        List<Location> pathToTarget = queen.findPath(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(4, 5),
            Location.of(4, 6),
            Location.of(4, 7)
        );
    }

    @DisplayName("경로 계산 - 수평")
    @Test
    void findPath_horizontal() {
        // given
        Location target = Location.of(1, 4);

        // when
        List<Location> pathToTarget = queen.findPath(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(3, 4),
            Location.of(2, 4)
        );
    }

    @DisplayName("경로 계산 - 킹은 경로가 없습니다.")
    @Test
    void findPath_king() {
        // given
        King king = King.of(Location.of(4, 1), Team.WHITE);
        Location target = Location.of(5, 1);

        // when
        List<Location> pathToTarget = king.findPath(target);

        // then
        assertThat(pathToTarget).isEmpty();
    }

    @DisplayName("기물 이둥 테스트")
    @Test
    void move() {
        King king = King.of(Location.of(5, 1), Team.WHITE);
        king.move(Location.of(4, 2));
        assertThat(king).isEqualTo(King.of(Location.of(4, 2), Team.WHITE));
    }

    @DisplayName("기물 위치 확인 테스트")
    @Test
    void isHere() {
        King king = King.of(Location.of(5, 1), Team.WHITE);
        assertThat(king.isHere(Location.of(5, 1))).isTrue();
    }

    @DisplayName("팀 확인 테스트")
    @Test
    void isSameTeam() {
        King king = King.of(Location.of(5, 1), Team.WHITE);
        Queen whiteQueen = Queen.of(Location.of(4, 1), Team.WHITE);
        Queen blackQueen = Queen.of(Location.of(4, 8), Team.BLACK);

        assertThat(king.isSameTeam(Team.WHITE)).isTrue();
        assertThat(king.isSameTeam(Team.BLACK)).isFalse();
        assertThat(king.isSameTeam(whiteQueen)).isTrue();
        assertThat(king.isSameTeam(blackQueen)).isFalse();
    }
}
