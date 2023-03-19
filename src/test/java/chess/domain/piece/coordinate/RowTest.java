package chess.domain.piece.coordinate;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RowTest {
    @ParameterizedTest(name = "row : {0}")
    @ValueSource(ints = {1, 8})
    void row가_1부터_8까지_들어오면_정상_작동(int row) {
        assertThatNoException()
                .isThrownBy(() -> new Row(row));
    }
    
    @ParameterizedTest(name = "row : {0}")
    @ValueSource(ints = {0, 9})
    void row가_1부터_8까지의_범위를_벗어나면_예외_처리(int row) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Row(row))
                .withMessage("row는 1~8까지의 숫자만 가능합니다.");
    }
}
