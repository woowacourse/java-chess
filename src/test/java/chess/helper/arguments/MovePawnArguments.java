package chess.helper.arguments;

import static chess.helper.PositionFixture.C4;
import static chess.helper.PositionFixture.C5;
import static chess.helper.PositionFixture.C6;
import static chess.helper.PositionFixture.D4;
import static chess.helper.PositionFixture.D6;
import static chess.helper.PositionFixture.E4;
import static chess.helper.PositionFixture.E5;
import static chess.helper.PositionFixture.E6;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

final class MovePawnArguments {

    private MovePawnArguments() {
    }

    private static Stream<Arguments> provideInvalidBlackPawnDirection() {
        return Stream.of(
                Arguments.of(D6), Arguments.of(C6), Arguments.of(C5), Arguments.of(C4), Arguments.of(E4),
                Arguments.of(E5), Arguments.of(E6)
        );
    }

    private static Stream<Arguments> provideInvalidBlackPawnAllyTarget() {
        return Stream.of(
                Arguments.of(D4), Arguments.of(C4), Arguments.of(E4)
        );
    }

    private static Stream<Arguments> provideValidBlackPawnAttackTarget() {
        return Stream.of(
                Arguments.of(C4), Arguments.of(E4)
        );
    }

    private static Stream<Arguments> provideInvalidWhitePawnDirection() {
        return Stream.of(
                Arguments.of(C6), Arguments.of(C5), Arguments.of(C4), Arguments.of(D4), Arguments.of(E4),
                Arguments.of(E5), Arguments.of(E6)
        );
    }

    private static Stream<Arguments> provideInvalidWhitePawnAllyTarget() {
        return Stream.of(
                Arguments.of(C6), Arguments.of(E6), Arguments.of(D6)
        );
    }

    private static Stream<Arguments> provideValidWhitePawnAttackTarget() {
        return Stream.of(
                Arguments.of(C6), Arguments.of(E6)
        );
    }
}
