package chess.domain.game;

import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.piece.Color;

class StatusTest {

    @ParameterizedTest
    @MethodSource("provideScore")
    @DisplayName("점수에 따른 결과 확인")
    void winner(Score whiteScore, Score blackScore, Color expected) {
        assertThat(new Status(whiteScore, blackScore).winner()).isEqualTo(expected);
    }

    private static Stream<Arguments> provideScore() {
        return Stream.of(
            Arguments.of(new Score(38), new Score(37), Color.WHITE),
            Arguments.of(new Score(38), new Score(38), Color.NONE),
            Arguments.of(new Score(37), new Score(38), Color.BLACK)
        );
    }

}
