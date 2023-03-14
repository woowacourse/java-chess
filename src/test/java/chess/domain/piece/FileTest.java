package chess.domain.piece;

import chess.domain.piece.position.File;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("File 은")
class FileTest {

    @ParameterizedTest(name = "생성 시 a 부터 h 사이에 포함되지 않는 문자(ex: {0})가 들어오면 예외")
    @ValueSource(chars = {'i', 'j', 'z', ' '})
    void 생성_시_a_부터_h_사이에_포함되지_않는_문자가_들어오면_예외(final char file) {
        // when & then
        assertThatThrownBy(() -> File.from(file))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "생성 시 a 부터 h 사이에 포함되는 문자(ex: {0})가 들어오면 정상 생성")
    @ValueSource(chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'})
    void a_부터_h_사이에_포함되는_문자인_경우_정상_생성(final char file) {
        // when & then
        assertDoesNotThrow(() -> File.from(file));
    }
}
