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

class KnightTest {

    private static final char[][] TEST_BOARD_VALUE = {
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', 'N', '.', '.', '.'},
        {'.', '.', '.', '.', 'P', '.', '.', '.'},
        {'.', '.', '.', 'n', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'}
    };

    private Board board;
    private Piece knight;

    @BeforeEach
    void setUp() {
        board = BoardUtil.convertToBoard(TEST_BOARD_VALUE);
        knight = board.find(Location.of(4, 4));
    }

    @DisplayName("나이트 이동 능력 테스트 - 대각선으로 한 칸 이동 후 수평으로 한 칸 이동할 수 있다.")
    @Test
    void movable_vertical() {
        // given, when
        Location diagonalVerticalTarget = Location.of(3, 2);


        // then
        assertThatCode(() -> knight.moveTo(diagonalVerticalTarget, board)).doesNotThrowAnyException();
    }

    @DisplayName("나이트 이동 능력 테스트 - 대각선으로 한 칸 이동 후 수직으로 한 칸 이동할 수 있다.")
    @Test
    void movable_horizontal() {
        // given, when
        Location diagonalHorizontalTarget = Location.of(2, 3);

        // then
        assertThatCode(() -> knight.moveTo(diagonalHorizontalTarget, board)).doesNotThrowAnyException();
    }

    @DisplayName("나이트 이동 능력 테스트 - 목표위치까지의 경로에 기물이 존재할 경우에도 이동할 수 있다.")
    @Test
    void movable_test_path() {
        // given, when
        Location target = Location.of(6, 5);

        // then
        assertThatCode(() -> knight.moveTo(target, board)).doesNotThrowAnyException();
    }

    @DisplayName("나이트 이동 능력 테스트 - 대각선, 수평으로 한 칸씩 움직이는 경로가 아니면 이동할 수 없다.")
    @Test
    void nonMovable_test() {
        // given, when
        Location nonMovableTarget = Location.of(2, 2);

        // then
        assertThatThrownBy(() -> knight.moveTo(nonMovableTarget, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("나이트 공격 테스트 - 목표위치에 적의 기물이 있는 경우 그 기물을 없애고 해당 위치로 이동한다.")
    @Test
    void moveAndAttack() {
        // given
        Location target = Location.of(5,6);
        Piece targetPiece = board.find(target);

        // when
        knight.moveTo(target, board);

        // then
        assertThat(board.find(target)).isEqualTo(knight);
        assertThat(board.toList()).doesNotContain(targetPiece);
    }
}
