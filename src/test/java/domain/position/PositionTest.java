package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PositionTest {

    @Test
    @DisplayName("Position 캐싱을 확인한다.")
    void givenCoordinate_thenReturnCachingPosition() {
        Position position = Position.of(0, 0);

        assertThat(position == Position.of(0, 0)).isTrue();
    }
    
    @Test
    @DisplayName("두 개의 Position y 값 차이를 반환한다.")
    void givenTwoPosition_thenReturnDiffY() {
        //given
        Position startPoint = Position.of(0, 0);
        Position endPoint = Position.of(0, 2);
        
        //when&then
        assertThat(endPoint.diffY(startPoint)).isEqualTo(2);
    }

    @Test
    @DisplayName("두 개의 Position x 값 차이를 반환한다.")
    void givenTwoPosition_thenReturnDiffX() {
        //given
        Position startPoint = Position.of(0, 0);
        Position endPoint = Position.of(2, 0);

        //when&then
        assertThat(endPoint.diffX(startPoint)).isEqualTo(2);
    }
}
