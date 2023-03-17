package chess.domain;

import chess.domain.position.File;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FileTest {

    @ParameterizedTest
    @ValueSource(strings = {"z", "i", "A"})
    void 잘못된_위치인_경우_예외를_던진다(String symbol) {
        Assertions.assertThatThrownBy(() -> File.getFile(symbol))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }
}
