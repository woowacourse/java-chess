package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {
    @Test
    @DisplayName("생성 테스트 ")
    void createTest() {
        for (Xpoint xpoint : Xpoint.values()) {
            for (Ypoint ypoint : Ypoint.values()) {
                assertThat(Position.valueOf(xpoint.getName() + ypoint.getValue())).isInstanceOf(Position.class);
            }
        }
    }

    @Test
    @DisplayName("잘못된 생성 테스트 ")
    void wrongPositionExceptionTest() {
        assertThatThrownBy(() -> Position.valueOf("z1")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("좌표 묶음 생성하기")
    void generateTest() {
        assertThat(Position.generate()).hasSize(8);
    }
}
