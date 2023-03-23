package chess.helper.arguments;

import static chess.helper.PositionFixture.A1;
import static chess.helper.PositionFixture.B2;
import static chess.helper.PositionFixture.C3;
import static chess.helper.PositionFixture.D4;
import static chess.helper.PositionFixture.E5;
import static chess.helper.PositionFixture.F6;
import static chess.helper.PositionFixture.G7;
import static chess.helper.PositionFixture.H8;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

final class PositionConverterArguments {

    private PositionConverterArguments() {
    }

    private static Stream<Arguments> provideValidConvertArguments() {
        return Stream.of(
                Arguments.of("A1", A1), Arguments.of("B2", B2), Arguments.of("C3", C3), Arguments.of("D4", D4),
                Arguments.of("E5", E5), Arguments.of("F6", F6), Arguments.of("G7", G7), Arguments.of("H8", H8)
        );
    }
}
