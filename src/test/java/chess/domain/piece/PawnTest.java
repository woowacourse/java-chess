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

class PawnTest {

    private static final char[][] TEST_BOARD_VALUE = {
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'},
        {'.', '.', 'R', 'B', 'q', '.', 'P', '.'},
        {'.', '.', '.', 'p', '.', '.', '.', '.'},
        {'n', '.', '.', '.', '.', '.', '.', '.'},
        {'p', '.', '.', '.', '.', 'p', '.', '.'},
        {'.', '.', '.', '.', '.', '.', '.', '.'}
    };

    private Board board;

    @BeforeEach
    void setUp() {
        board = BoardUtil.convertToBoard(TEST_BOARD_VALUE);
    }

    @DisplayName("폰 이동 능력 테스트 - 전방으로 1칸씩 전진할 수 있다.")
    @Test
    void movable_oneStep() {
        // given
        Location source = Location.of(6, 2);
        Location target = Location.of(6, 3);
        Piece pawn = board.find(source);

        // then
        assertThatCode(() -> pawn.moveTo(target, board)).doesNotThrowAnyException();
        assertThat(board.find(target)).isEqualTo(pawn);
    }

    @DisplayName("폰 이동 능력 테스트 - 초기 위치인 경우 전방으로 2칸 전진할 수 있다.")
    @Test
    void movable_twoStep() {
        // given, when
        Location source = Location.of(6, 2);
        Location target = Location.of(6, 4);
        Piece pawn = board.find(source);

        // then
        assertThatCode(() -> pawn.moveTo(target, board)).doesNotThrowAnyException();
        assertThat(board.find(target)).isEqualTo(pawn);
    }

    @DisplayName("폰 이동 능력 테스트 - 초기 위치가 아닌 경우 전방으로 2칸 움직일 수 없다.")
    @Test
    void nonMovable_twoStep() {
        // given
        Location source = Location.of(7, 5);
        Location target = Location.of(7, 3);
        Piece pawn = board.find(source);

        // then
        assertThatThrownBy(() -> pawn.moveTo(target, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 이동 능력 테스트 - 전진만 가능하며 뒤로 움직일 수 없다.")
    @Test
    void nonMovable_back() {
        // given
        Location source = Location.of(7, 5);
        Location target = Location.of(7, 6);
        Piece pawn = board.find(source);

        // then
        assertThatThrownBy(() -> pawn.moveTo(target, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 이동 능력 테스트 - 전진만 가능하며 옆으로 움직일 수 없다.")
    @Test
    void nonMovable_side() {
        // given
        Location source = Location.of(7, 5);
        Location target = Location.of(6, 5);
        Piece pawn = board.find(source);

        // then
        assertThatThrownBy(() -> pawn.moveTo(target, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 이동 능력 테스트 - 목표위치까지의 경로에 기물이 존재할 경우 이동할 수 없다.")
    @Test
    void nonMovable_path() {
        // given
        Location source = Location.of(1, 2);
        Location target = Location.of(1, 4);
        Piece pawn = board.find(source);

        // then
        assertThatThrownBy(() -> pawn.moveTo(target, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 이동 능력 테스트 - 앞에 기물(아군, 적 포함)있으면 이동할 수 없다.")
    @Test
    void nonMovable_exist() {
        // given
        Location source = Location.of(4, 4);
        Location target = Location.of(4, 5);
        Piece pawn = board.find(source);

        // then
        assertThatThrownBy(() -> pawn.moveTo(target, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 이동 능력 테스트 - 적이 없는 경우엔 대각선으로 1칸 이동할 수 없다.")
    @Test
    void nonMovable_diagonal_1() {
        // given
        Location source = Location.of(1, 2);
        Location target = Location.of(2, 3);
        Piece pawn = board.find(source);

        // then
        assertThatThrownBy(() -> pawn.moveTo(target, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 인동 능력 테스트 - 대각선에 아군이 위치한 경우 이동할 수 없다.")
    @Test
    void nonMovable_diagonal_2() {
        // given
        Location source = Location.of( 4, 4);
        Location target = Location.of(5, 5);
        Piece pawn = board.find(source);

        // then
        assertThatThrownBy(() -> pawn.moveTo(target, board))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("폰 공격 테스트 - 대각선에 적이 위치한 경우 대각선으로 1칸 이동할 수 있다.")
    @Test
    void moveAndAttack() {
        // given
        Location source = Location.of(4, 4);
        Location target = Location.of(3, 5);
        Piece pawn = board.find(source);
        Piece targetPiece = board.find(target);

        // when
        pawn.moveTo(target, board);

        // then
        assertThat(board.find(target)).isEqualTo(pawn);
        assertThat(board.toList()).doesNotContain(targetPiece);
    }
}
