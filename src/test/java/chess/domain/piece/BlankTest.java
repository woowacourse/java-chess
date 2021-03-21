package chess.domain.piece;

import chess.domain.game.Board;
import chess.domain.game.InitializedChess;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class BlankTest {
    
    @Test
    @DisplayName("블랭크를 다른 칸으로 이동시키려 할 경우 예외 발생")
    void move_TryToMoveBlank_ExceptionThrown() {
        
        // given
        Blank blank = new Blank(Color.WHITE);
        Position sourcePosition = Position.of("a3");
        Position targetPosition = Position.of("a4");
        Board board = InitializedChess.create()
                                      .getBoard();
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> blank.move(sourcePosition, targetPosition, board);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("해당 위치에는 기물이 존재하지 않습니다");
    }
    
    @Test
    @DisplayName("점수 반환 테스트")
    void scoreTest() {
        
        // when
        final double score = new Blank(Color.BLACK).getScore();
        
        // then
        assertThat(score).isEqualTo(0);
    }
}