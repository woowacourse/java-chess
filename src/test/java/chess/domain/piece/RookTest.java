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

class RookTest {

    private static final char[][] TEST_BOARD_VALUE = {
        {'.', '.', '.', 'Q', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', 'r', '.', 'p', 'P', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'}
    };

    private Board board;
    private Piece rook;

    @BeforeEach
    void setUp() {
        board = BoardUtil.convertToBoard(TEST_BOARD_VALUE);
        rook = board.find(Location.of(4, 4));
    }

    @DisplayName("룩 이동 능력 테스트 - 수평으로 이동 가능하다.")
    @Test
    void movable_horizontal() {
        // given, when
        Location horizontalTarget = Location.of(1, 4);

        // then
        assertThatCode(() -> rook.moveTo(horizontalTarget, board)).doesNotThrowAnyException();
        assertThat(board.find(horizontalTarget)).isEqualTo(rook);
    }

    @DisplayName("룩 이동 능력 테스트 - 수직으로 이동 가능하다.")
    @Test
    void movable_vertical() {
        // given, when
        Location verticalTarget = Location.of(4, 7);

        // then
        assertThatCode(() -> rook.moveTo(verticalTarget, board)).doesNotThrowAnyException();
        assertThat(board.find(verticalTarget)).isEqualTo(rook);
    }

    @DisplayName("룩 이동 능력 테스트 - 목표위치까지의 경로에 기물이 존재할 경우 이동할 수 없다.")
    @Test
    void nonMovable_test_path() {
        // given, when
        Location nonMovableTarget = Location.of(7, 4);

        // then
        assertThatThrownBy(() -> rook.moveTo(nonMovableTarget, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("룩 이동 능력 테스트 - 수평, 수직 외에 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(3, 3);

        // then
        assertThatThrownBy(() -> rook.moveTo(nonMovableTarget, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("룩 공격 테스트 - 목표위치에 적의 기물이 있는 경우 그 기물을 없애고 해당 위치로 이동한다.")
    @Test
    void moveAndAttack() {
        // given, when
        Location target = Location.of(4, 8);
        Piece targetPiece = board.find(target);

        // when
        rook.moveTo(target, board);

        // then
        assertThat(board.find(target)).isEqualTo(rook);
        assertThat(board.toList()).doesNotContain(targetPiece);
    }
}
