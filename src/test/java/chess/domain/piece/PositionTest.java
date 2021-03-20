package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("위치 테스트")
class PositionTest {

    @Test
    @DisplayName("생성자 테스트")
    void create() {
        assertThat(Position.of(0,0)).isEqualTo(Position.of("a1"));

        assertThat(Position.of(1,1)).isSameAs(Position.of("b2"));
    }

    @Test
    @DisplayName("이동 가능한지 테스트")
    void canMoveTest() {
        Position position = Position.of(1, 2);
        Direction direction = Direction.NORTH;
        int ableLength = 1;
        
        Position targetPosition = Position.of(2, 2);

        final boolean canMove = position.canMove(targetPosition, direction, ableLength);

        assertThat(canMove).isTrue();
    }
    
    @Test
    @DisplayName("이동 불가능한지 테스트")
    void cannotMoveTest() {

        Position position = Position.of(1, 2);
        Direction direction = Direction.NORTH;
        int ableLength = 1;
        
        Position targetPosition = Position.of(1, 3);
        
        final boolean canMove = position.canMove(targetPosition, direction, ableLength);
        
        assertThat(canMove).isFalse();
    }
}