package chess.domain.piece;

import static chess.domain.piece.Direction.*;
import static org.assertj.core.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;

class DirectionTest {

    @ParameterizedTest
    @DisplayName("주어진 방향으로 이동했을 때의 포지션")
    @MethodSource("createDirection")
    void findDestination(Direction direction, Position expected) {
        File file = direction.findFileDestination(File.D).orElse(null);
        Rank rank = direction.findRankDestination(Rank.FIVE).orElse(null);

        assertThat(Position.of(file, rank)).isEqualTo(expected);
    }

    static Stream<Arguments> createDirection() {
        return Stream.of(
                Arguments.of(NORTH, Position.from("d6")),
                Arguments.of(NORTH_EAST, Position.from("e6")),
                Arguments.of(EAST, Position.from("e5")),
                Arguments.of(SOUTH_EAST, Position.from("e4")),
                Arguments.of(SOUTH, Position.from("d4")),
                Arguments.of(SOUTH_WEST, Position.from("c4")),
                Arguments.of(WEST, Position.from("c5")),
                Arguments.of(NORTH_WEST, Position.from("c6")),
                Arguments.of(NNE, Position.from("e7")),
                Arguments.of(NEE, Position.from("f6")),
                Arguments.of(SEE, Position.from("f4")),
                Arguments.of(SSE, Position.from("e3")),
                Arguments.of(SSW, Position.from("c3")),
                Arguments.of(SWW, Position.from("b4")),
                Arguments.of(NWW, Position.from("b6")),
                Arguments.of(NNW, Position.from("c7"))
        );
    }
}