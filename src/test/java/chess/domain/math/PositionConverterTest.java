package chess.domain.math;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionConverterTest {

    @Test
    @DisplayName("사용자의 입력을 2차원 리스트의 좌표값으로 변환해준다.")
    void toPositionTest_row() {
        String input = "b2";
        Position position = PositionConverter.toPosition(input);

        assertAll(
                () -> assertThat(position.getRow()).isEqualTo(6),
                () -> assertThat(position.getColumn()).isEqualTo(1)
        );
    }
}
