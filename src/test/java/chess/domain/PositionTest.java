package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {
    @Test
    @DisplayName("Position 객체 정상 생성 테스트")
    void createPosition(){
        // given
        Position position1 = Position.of("b1");
        Position position2 = Position.of("B1");

        // when & then
        assertThat(position1).isEqualTo(position2);
    }

    @Test
    @DisplayName("범위 밖 Position 생성 시도 시, UOE 발생")
    void creatingPositionWithOutOfRangeShouldFail() {
        assertThatThrownBy(() -> Position.of("A10"))
            .isInstanceOf(NoSuchElementException.class)
            .hasMessage("유효하지 않은 범위입니다.");
    }
}