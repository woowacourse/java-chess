package chess.domain.piece.coordinate;

import chess.domain.piece.coordinate.Column;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ColumnTest {
    @ParameterizedTest(name = "column : {0}")
    @ValueSource(chars = {'a', 'h'})
    void column가_a부터_h까지_들어오면_정상_작동(char column) {
        assertThatNoException()
                .isThrownBy(() -> new Column(column));
    }
    
    @ParameterizedTest(name = "column : {0}")
    @ValueSource(chars = {'`', 'i', 'z'})
    void column가_a부터_h까지의_범위를_벗어나면_예외_처리(char column) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Column(column))
                .withMessage("column는 a~h까지의 문자만 가능합니다.");
    }
}
