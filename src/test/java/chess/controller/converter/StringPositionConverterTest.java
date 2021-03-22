package chess.controller.converter;

import chess.domain.Position;
import chess.exception.InvalidPositionException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class StringPositionConverterTest {

    @Test
    void convertToPosition() {
        Position position = StringPositionConverter.convert("a1");
        Assertions.assertThat(position).isEqualTo(Position.of(0, 1));
    }


    @Test
    void convertToPosition_invalidPosition() {
        Assertions.assertThatThrownBy(() -> StringPositionConverter.convert("z9123"))
            .isInstanceOf(InvalidPositionException.class);
    }
}