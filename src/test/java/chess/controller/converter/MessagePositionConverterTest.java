package chess.controller.converter;

import chess.domain.Position;
import chess.domain.Positions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class MessagePositionConverterTest {

    @Test
    void convertToPositions() {
        Positions positions = MessagePositionConverter.convert("move a2 a4");
        Assertions.assertThat(positions.currentPosition()).isEqualTo(Position.of(0, 2));
        Assertions.assertThat(positions.targetPosition()).isEqualTo(Position.of(0, 4));
    }
}