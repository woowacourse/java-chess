package chess.domain.position;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FileTest {

    @Test
    void 심볼_값으로_File_을_찾을_수_있다() {
        final String symbol = "a";

        final File file = File.findBySymbol(symbol);

        assertThat(file).isEqualTo(File.A);
    }

    @ParameterizedTest
    @ValueSource(strings = {"z", "i", "A"})
    void 잘못된_심볼인_경우_예외를_던진다(final String symbol) {
        assertThatThrownBy(() -> File.findBySymbol(symbol))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }

    @Test
    void 인덱스_값으로_File_을_찾을_수_있다() {
        final int index = 1;

        final File file = File.findByIndex(index);

        assertThat(file).isEqualTo(File.A);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 9})
    void 잘못된_인덱스인_경우_예외를_던진다(final int index) {
        assertThatThrownBy(() -> File.findByIndex(index))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 위치입니다.");
    }
}
