package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {
    
    @Test
    @DisplayName("이동 가능한지 테스트")
    void canMoveTest() {
        
        // given
        Position position = Position.of(1, 2);
        Direction direction = Direction.NORTH;
        int ableLength = 1;
        
        Position targetPosition = Position.of(2, 2);
        
        // when
        final boolean canMove = position.canMove(targetPosition, direction, ableLength);
    
        // then
        assertThat(canMove).isTrue();
    }
    
    @Test
    @DisplayName("이동 불가능한지 테스트")
    void cannotMoveTest() {
        
        // given
        Position position = Position.of(1, 2);
        Direction direction = Direction.NORTH;
        int ableLength = 1;
        
        Position targetPosition = Position.of(1, 3);
        
        // when
        final boolean canMove = position.canMove(targetPosition, direction, ableLength);
        
        // then
        assertThat(canMove).isFalse();
    }
    
    @Test
    @DisplayName("문자열로 위치생성 테스트")
    void createTest() {
        assertThat(Position.of("b2")).isEqualTo(Position.of(1, 1));
        assertThat(Position.of("b3")).isEqualTo(Position.of(2, 1));
    }
}