package chess.domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ChessFileTest {

    @DisplayName("파일과 파일 사이의 파일들을 조회한다.")
    @ParameterizedTest
    @MethodSource("findFileBetweenArguments")
    void findFileBetween(ChessFile start, ChessFile end, List<ChessFile> expected) {
        // when
        List<ChessFile> fileBetween = ChessFile.findBetween(start, end);

        // then
        assertThat(expected).containsAll(fileBetween);
    }

    private static Stream<Arguments> findFileBetweenArguments() {
        return Stream.of(
                Arguments.of(ChessFile.A, ChessFile.H,
                        List.of(ChessFile.B,
                                ChessFile.C,
                                ChessFile.D,
                                ChessFile.E,
                                ChessFile.F,
                                ChessFile.G)),
                Arguments.of(ChessFile.H, ChessFile.A,
                        List.of(ChessFile.B,
                                ChessFile.C,
                                ChessFile.D,
                                ChessFile.E,
                                ChessFile.F,
                                ChessFile.G))
        );
    }
}
