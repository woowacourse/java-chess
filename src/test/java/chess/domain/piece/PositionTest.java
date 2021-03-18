package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PositionTest {

    @DisplayName("포지션 생성 테스트")
    @Test
    void createPosition() {
        Position position = new Position(0, 0);

        assertThat(position).isEqualTo(new Position(0, 0));
    }

    @DisplayName("두 포지션의 기울기를 반환하는 기능 테스트")
    @Test
    void calculateGradient() {
        //given
        Position firstPosition = new Position(0, 0);
        Position secondPosition = new Position(2, 2);

        //when
        double gradient = firstPosition.calculateGradient(secondPosition);

        //then
        assertThat(gradient).isEqualTo(1.0);
    }

}
