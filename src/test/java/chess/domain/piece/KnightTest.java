package chess.domain.piece;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import chess.domain.board.Board;
import chess.domain.chess.Chess;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;

class KnightTest {
    private final Knight knight = Knight.WHITE_INSTANCE;
    private final Position sourcePosition = Position.from("b1");
    private final Board board = Chess.createWithEmptyBoard()
                                     .start()
                                     .getBoard();

    @Test
    @DisplayName("이동 검사")
    void moveTest() {

        // given
        final Position targetPosition = Position.from("a3");
        MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> knight.checkToMoveToTargetPosition(movePosition, board);

        // then
        assertThatCode(callable).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("현재 위치에서 갈 수 없는 칸으로 이동하려 할 경우 예외 발생")
    void move_TryToMoveWhereCannotMove_ExceptionThrown() {

        // given
        final Position targetPosition = Position.from("a2");
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);

        // when
        ThrowableAssert.ThrowingCallable callable =
                () -> knight.checkToMoveToTargetPosition(movePosition, board);


        // then
        assertThatIllegalArgumentException().isThrownBy(callable);
    }

    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {

        // when
        final double score = knight.getScore();

        // then
        assertThat(score).isEqualTo(2.5);
    }
}
