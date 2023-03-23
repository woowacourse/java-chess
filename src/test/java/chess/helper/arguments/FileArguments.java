package chess.helper.arguments;

import static chess.model.position.File.A;
import static chess.model.position.File.B;
import static chess.model.position.File.C;
import static chess.model.position.File.D;
import static chess.model.position.File.E;
import static chess.model.position.File.F;
import static chess.model.position.File.G;
import static chess.model.position.File.H;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

final class FileArguments {

    private FileArguments() {
    }

    private static Stream<Arguments> provideValidFindFileArguments() {
        return Stream.of(
                Arguments.of("a", A), Arguments.of("b", B), Arguments.of("c", C), Arguments.of("d", D),
                Arguments.of("e", E), Arguments.of("f", F), Arguments.of("g", G), Arguments.of("h", H)
        );
    }

    private static Stream<Arguments> provideValidFindNextFileArguments() {
        return Stream.of(
                Arguments.of(1, F),Arguments.of(0, E),Arguments.of(-1, D),Arguments.of(2, G),Arguments.of(-2, C)
        );
    }

    private static Stream<Arguments> provideInvalidFindNextFileArguments() {
        return Stream.of(
                Arguments.of(A, -1), Arguments.of(H, 1)
        );
    }
}
