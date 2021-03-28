package chess.domain.piece;

import chess.domain.position.MovePosition;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class DirectionGroupTest {
    
    private final DirectionGroup directionGroupOfKing = new DirectionGroup(Direction.everyDirection(), 1);
    
    @Test
    @DisplayName("이동할 수 있는 위치일 경우 방향 반환")
    void findDirection() {
        
        // given
        MovePosition movePosition = MovePosition.from(new String[]{"e1", "e2"});
        
        // when
        final Direction direction = directionGroupOfKing.findDirection(movePosition);
        
        // then
        assertThat(direction).isEqualTo(Direction.NORTH);
    }
    
    @Test
    @DisplayName("이동할 수 없는 위치일 경우 예외 발생")
    void findDirection_CannotMove_ExceptionThrown() {
        
        // given
        MovePosition movePosition = MovePosition.from(new String[]{"e1", "e3"});
        
        // when
        ThrowableAssert.ThrowingCallable callable = () -> directionGroupOfKing.findDirection(movePosition);
        
        // then
        assertThatIllegalArgumentException().isThrownBy(callable)
                                            .withMessage("기물이 이동할 수 없는 위치입니다.");
    }
}
