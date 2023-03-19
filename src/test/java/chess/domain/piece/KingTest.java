package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class KingTest {

    Team team;
    Piece king;

    @BeforeEach
    void setUp() {
        team = Team.WHITE;
        king = new King(team);
    }

    @Test
    @DisplayName("킹은 target 지점에 같은 팀이 있으면 이동할 수 없다.")
    void isUnmovableToSameTeamInTarget() {
        Position source = new Position(5, 1);
        Position target = new Position(4, 1);
        assertThatThrownBy(() -> king.isMovable(source, target, Team.WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 타겟 위치에 같은 팀 기물이 있습니다.");
    }

    @ParameterizedTest
    @CsvSource(value = {"2:2", "1:2", "2:1"}, delimiter = ':')
    @DisplayName("킹은 상하좌우 또는 대각선으로 한 칸 이동할 수 있다.")
    void isMovable(int rank, int file) {
        Position source = new Position(1, 1);
        Position target = new Position(rank, file);
        assertThat(king.isMovable(source, target, team.reverse())).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "8:8", "1:5"}, delimiter = ':')
    @DisplayName("킹은 상하좌우 또는 대각선 한 칸을 제외한 곳으로 이동할 수 없다.")
    void isUnmovable(int rank, int file) {
        Position source = new Position(1, 1);
        Position target = new Position(rank, file);
        assertThat(king.isMovable(source, target, team.reverse())).isFalse();
    }

    @Test
    @DisplayName("타켓 위치로 갈 수 있는 모든 경로를 리스트에 담아서 반환한다.")
    void findPath() {
        Position source = new Position(1, 1);
        Position target = new Position(2, 2);
        assertThat(king.findPath(source, target)).containsExactly(new Position(2, 2));
    }
}
