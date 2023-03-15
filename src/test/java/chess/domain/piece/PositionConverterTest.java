package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PositionConverterTest {
    @Test
    @DisplayName("입력 받은 위치를 Position 으로 변환한다.")
    void convert() {
        PositionConverter positionConverter = new PositionConverter();
        assertThat(positionConverter.convert("a7")).isEqualTo(new Position(0, 6));
    }
}
