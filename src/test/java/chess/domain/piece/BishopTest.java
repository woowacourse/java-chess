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

class BishopTest {

    private static final char[][] TEST_BOARD_VALUE = {
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', 'N', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', 'b', '.', '.', '.', '.'},
        {'.', '.', '.', '.', 'p', '.', '.', '.'},
        {'.', '.', '.', '.', '.', 'P', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'}
    };

    private Board board;
    private Piece bishop;

    @BeforeEach
    void setUp() {
        board = BoardUtil.convertToBoard(TEST_BOARD_VALUE);
        bishop = board.find(Location.of(4, 4));
    }

    @DisplayName("비숍 이동 능력 테스트 - 대각선으로 이동 가능하다.")
    @Test
    void movable_test() {
        // given, when
        Location diagonalTarget = Location.of(1, 1);

        // then
        assertThatCode(() -> bishop.moveTo(diagonalTarget, board)).doesNotThrowAnyException();
    }

    @DisplayName("비숍 이동 능력 테스트 - 대각선 외에 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(3, 4);

        // then
        assertThatCode(() -> bishop.moveTo(nonMovableTarget, board)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("비숍 이동 능력 테스트 - 목표위치까지의 경로에 기물이 존재할 경우 이동할 수 없다.")
    @Test
    void nonMovable_test_path() {
        // given, when
        Location nonMovableTarget = Location.of(6, 4);

        // then
        assertThatThrownBy(() -> bishop.moveTo(nonMovableTarget, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("비숍 공격 테스트 - 목표위치에 적의 기물이 있는 경우 그 기물을 없애고 해당 위치로 이동한다.")
    @Test
    void moveAndAttack() {
        // given
        Location target = Location.of(6, 6);
        Piece targetPiece = board.find(target);

        // when
        bishop.moveTo(target, board);

        // then
        assertThat(board.find(target)).isEqualTo(bishop);
        assertThat(board.toList()).doesNotContain(targetPiece);
    }
}
