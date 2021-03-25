package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.utils.BoardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    private static final char[][] TEST_BOARD_VALUE = {
        {'.', '.', '.', '.', '.', '.', '.', 'R'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', 'n', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
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

    @DisplayName("퀸 이동 능력 테스트 - 수평으로 이동 가능하다.")
    @Test
    void movable_horizontal() {
        // given, when
        Location horizontalTarget = Location.of(7, 4);

        // then
        assertThatCode(() -> queen.moveTo(horizontalTarget, board)).doesNotThrowAnyException();
    }

    @DisplayName("퀸 이동 능력 테스트 - 수직으로 이동 가능하다.")
    @Test
    void movable_vertical() {
        // given, when
        Location verticalTarget = Location.of(4, 1);

        // then
        assertThatCode(() -> queen.moveTo(verticalTarget, board)).doesNotThrowAnyException();
    }

    @DisplayName("퀸 이동 능력 테스트 - 대각선으로 이동 가능하다.")
    @Test
    void movable_diagonal() {
        // given, when
        Location diagonalTarget = Location.of(1, 1);

        // then
        assertThatCode(() -> queen.moveTo(diagonalTarget, board)).doesNotThrowAnyException();
    }

    @DisplayName("퀸 이동 능력 테스트 - 수평, 수직, 대각선 외에 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(3, 2);

        // then
        assertThatThrownBy(() -> queen.moveTo(nonMovableTarget, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("퀸 이동 능력 테스트 - 목표위치까지의 경로에 기물이 존재할 경우 이동할 수 없다.")
    @Test
    void nonMovable_test_path() {
        // given, when
        Location nonMovableTarget = Location.of(4, 8);

        // then
        assertThatThrownBy(() -> queen.moveTo(nonMovableTarget, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("퀸 공격 테스트 - 목표위치에 적의 기물이 있는 경우 그 기물을 없애고 해당 위치로 이동한다.")
    @Test
    void movaAndAttack() {
        // given
        Location target = Location.of(8, 8);
        Piece targetPiece = board.find(target);

        // when
        queen.moveTo(target, board);

        // then
        assertThat(board.find(target)).isEqualTo(queen);
        assertThat(board.toList()).doesNotContain(targetPiece);
    }
}
