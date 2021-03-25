package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.utils.BoardUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    private static final char[][] TEST_BOARD_VALUE = {
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', 'P', 'k', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', 'B', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'}
    };

    private Board board;
    private Piece king;

    @BeforeEach
    void setUp() {
        board = BoardUtil.convertToBoard(TEST_BOARD_VALUE);
        king = board.find(Location.of(4, 4));
    }

    @DisplayName("킹 이동 능력 테스트 - 인접한 위치로 이동할 수 있다.")
    @Test
    void movable_test() {
        // given, when
        Location horizontalTarget = Location.of(5, 4);
        Location verticalTarget = Location.of(4, 5);
        Location diagonalTarget = Location.of(5, 5);

        // then
        assertAll(
            () -> assertThatCode(() -> king.moveTo(horizontalTarget, board)).doesNotThrowAnyException(),
            () -> assertThatCode(() -> king.moveTo(verticalTarget, board)).doesNotThrowAnyException(),
            () -> assertThatCode(() -> king.moveTo(diagonalTarget, board)).doesNotThrowAnyException()
        );
    }

    @DisplayName("킹 이동 능력 테스트 - 인접하지 않은 위치로 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(7, 4);

        // then
        assertThatThrownBy(() -> king.moveTo(nonMovableTarget, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("킹 공격 테스트 - 목표위치에 적의 기물이 있는 경우 그 기물을 없애고 해당 위치로 이동한다.")
    @Test
    void moveAndAttack() {
        // given
        Location target = Location.of(3, 4);
        Piece targetPiece = board.find(target);

        // when
        king.moveTo(target, board);

        // then
        assertThat(board.find(target)).isEqualTo(king);
        assertThat(board.toList()).doesNotContain(targetPiece);
    }
}
