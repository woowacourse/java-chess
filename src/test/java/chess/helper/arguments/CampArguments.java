package chess.helper.arguments;

import chess.model.piece.Camp;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

final class CampArguments {

    private CampArguments() {
    }

    private static Stream<Arguments> provideIsSameTeamByBlack() {
        return Stream.of(
                Arguments.of(Camp.BLACK, true), Arguments.of(Camp.WHITE, false)
        );
    }
}
