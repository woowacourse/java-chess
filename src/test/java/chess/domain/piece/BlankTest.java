package chess.domain.piece;

import chess.domain.Chess;
import chess.domain.board.Board;
import chess.domain.position.MovePosition;
import chess.domain.position.Position;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BlankTest {
    
    @Test
    @DisplayName("블랭크를 다른 칸으로 이동시키려 할 경우 예외 발생")
    void move_TryToMoveBlank_ExceptionThrown() {
        
        // given
        Blank blank = Blank.INSTANCE;
        Position sourcePosition = Position.from("a3");
        Position targetPosition = Position.from("a4");
        Board board = Chess.createWithEmptyBoard()
                           .start()
                           .getBoard();
        
        final MovePosition movePosition = new MovePosition(sourcePosition, targetPosition);
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> blank.checkToMoveToTargetPosition(movePosition, board);
        
        // then
        assertThatThrownBy(callable).isInstanceOf(UnsupportedOperationException.class)
                                    .hasMessage("선택한 위치는 빈 칸입니다.");
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {
        
        // when
        final double score = Blank.INSTANCE.getScore();
        
        // then
        assertThat(score).isEqualTo(0);
    }
}
