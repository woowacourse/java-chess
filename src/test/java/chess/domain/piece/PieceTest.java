package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    private static final char[][] TEST_BOARD_VALUE = {
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', 'r', '.', '.', '.'},
        {'.', '.', '.', 'q', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'}
    };

    private Board board;
    private Piece queen;

    @BeforeEach
    void setUp() {
        board = BoardUtil.convertToBoard(TEST_BOARD_VALUE);
        queen = board.find(Location.of(4, 4));
    }

    @DisplayName("경로 탐색 - 오른쪽 위 대각선")
    @Test
    void findPath_rightUpDiagonal() {
        // given
        Location target = Location.of(8, 8);

        // when
        List<Location> pathToTarget = queen.findPathTo(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(5, 5),
            Location.of(6, 6),
            Location.of(7, 7)
        );
    }

    @DisplayName("경로 탐색 - 왼쪽 아래 대각선")
    @Test
    void findPath_leftDownDiagonal() {
        // given
        Location target = Location.of(1, 1);

        // when
        List<Location> pathToTarget = queen.findPathTo(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(3, 3),
            Location.of(2, 2)
        );
    }

    @DisplayName("경로 탐색 - 수직")
    @Test
    void findPath_vertical() {
        // given
        Location target = Location.of(4, 8);

        // when
        List<Location> pathToTarget = queen.findPathTo(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(4, 5),
            Location.of(4, 6),
            Location.of(4, 7)
        );
    }

    @DisplayName("경로 탐색 - 수평")
    @Test
    void findPath_horizontal() {
        // given
        Location target = Location.of(1, 4);

        // when
        List<Location> pathToTarget = queen.findPathTo(target);

        // then
        assertThat(pathToTarget).containsExactly(
            Location.of(3, 4),
            Location.of(2, 4)
        );
    }

    @DisplayName("기물 공통 움직임 - 시작위치와 목표위치가 같으면 이동하지 못한다.")
    @Test
    void move_sameLocation() {
        // given, when
        Location source = Location.of(1, 1);
        Location target = Location.of(1, 1);

        // then
        assertThatThrownBy(() -> board.move(source, target, Team.BLACK))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("기물 공통 움직임 - 목표위치에 같은 팀의 기물이 존재하면 이동하지 못한다.")
    @Test
    void move_sameTeamAtTarget() {
        // given, when
        Location source = Location.of(4, 4);
        Location target = Location.of(5, 5);

        // when
        assertThatThrownBy(() -> board.move(source, target, Team.WHITE))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
