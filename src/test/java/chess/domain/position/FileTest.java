package chess.domain.position;

import static chess.domain.position.File.A;
import static chess.domain.position.File.B;
import static chess.domain.position.File.C;
import static chess.domain.position.File.D;
import static chess.domain.position.File.E;
import static chess.domain.position.File.F;
import static chess.domain.position.File.G;
import static chess.domain.position.File.H;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.File;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
public class FileTest {


    @ParameterizedTest(name = "두 파일 사이의 파일들을 반환한다. 시작: {0}, 도착: {1}, 결과: {2}")
    @MethodSource("betweenSource")
    void 두_파일_사이의_파일을_반환한다(final File source, final File target, final List<File> result) {
        // expect
        assertThat(source.between(target)).containsExactlyElementsOf(result);
    }

    static Stream<Arguments> betweenSource() {
        return Stream.of(
                Arguments.of(A, D, List.of(B, C)),
                Arguments.of(A, B, Collections.emptyList()),
                Arguments.of(A, A, Collections.emptyList()),
                Arguments.of(B, A, Collections.emptyList()),
                Arguments.of(H, E, List.of(G, F))
        );
    }

    @ParameterizedTest(name = "입력받은 파일과의 차이를 반환한다. 시작: {0}, 도착: {1}, 결과: {2}")
    @CsvSource({"A, D, -3", "B, B, 0", "H, G, 1"})
    void 입력받은_파일과의_차이를_반환한다(final File source, final File target, final int result) {
        // expect
        assertThat(source.calculateGap(target)).isEqualTo(result);
    }
}
