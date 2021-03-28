package chess.domain.position;

import chess.domain.piece.Direction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    
    private final int movableLength = 1;
    private final Position position = Position.from("a1");
    private final Direction direction = Direction.NORTH;
    
    @Test
    @DisplayName("방향이 맞고, 이동 가능 거리가 충분할 경우 이동 가능")
    void canMoveTest() {
        
        // given
        Position targetPosition = Position.from("a2");
        MovePosition movePosition = new MovePosition(position, targetPosition);
        
        // when
        final boolean canMove = movePosition.canMove(direction, movableLength);
        
        // then
        assertThat(canMove).isTrue();
    }
    
    @Test
    @DisplayName("방향은 맞지만 이동 가능 거리가 부족한 경우 이동 불가")
    void canMove_NotEnoughLengthToMove_CannotMove() {
        
        // given
        Position targetPosition = Position.from("a3");
        MovePosition movePosition = new MovePosition(position, targetPosition);
        
        // when
        final boolean canMove = movePosition.canMove(direction, movableLength);
        
        // then
        assertThat(canMove).isFalse();
    }
    
    @Test
    @DisplayName("방향이 틀린 경우 이동 불가")
    void canMove_IncorrectDirections_CannotMove() {
        
        // given
        Position targetPosition = Position.from("a3");
        MovePosition movePosition = new MovePosition(position, targetPosition);
        
        // when
        final boolean canMove = movePosition.canMove(direction, movableLength);
        
        // then
        assertThat(canMove).isFalse();
    }
    
    @Test
    @DisplayName("문자열로 위치생성 테스트")
    void createTest() {
        assertThat(Position.from("b2")).isEqualTo(Position.of(1, 1));
        assertThat(Position.from("b3")).isEqualTo(Position.of(1, 2));
    }
}
