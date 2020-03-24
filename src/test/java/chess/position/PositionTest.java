package chess.position;

import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @DisplayName("행, 열을 통해 위치 객체가 정상적으로 생성되었는지 테스트")
    @Test
    void constructPositionTest() {
        assertThat(new Position(A, EIGHT)).isInstanceOf(Position.class);
    }

    @DisplayName("행, 열을 입력받아 생성한 Position객체가 올바르게 생성되는지 테스트")
    @Test
    void ofTest() {
        assertThat(Position.of(A, ONE)).isEqualTo(new Position(A, ONE));
    }
}