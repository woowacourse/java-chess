package chess.domain.piece.position;

import java.util.stream.Stream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.File;
import chess.domain.game.state.position.Position;
import chess.domain.game.state.position.Rank;

class PositionTest {

    @ParameterizedTest(name = "{index}: {1}")
    @MethodSource("invalidParameters")
    @DisplayName("막혀있는 곳으로 이동")
    void isBlocked(Position position, Direction direction) {
        Assertions.assertThat(position.isBlocked(direction)).isTrue();
    }

    private static Stream<Arguments> invalidParameters() {
        return Stream.of(
            Arguments.of(Position.of(File.a, Rank.One), Direction.UpUpLeft, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.UpLeftLeft, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.UpLeft, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.Left, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.DownLeftLeft, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.DownLeft, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.Down, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.DownRight, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.DownRightRight, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.DownDownLeft, ""),
            Arguments.of(Position.of(File.a, Rank.One), Direction.DownDownRight, ""),

            Arguments.of(Position.of(File.h, Rank.Eight), Direction.UpUpLeft, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.UpUpRight, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.UpLeftLeft, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.UpLeft, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.Up, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.UpRight, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.UpRightRight, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.Right, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.DownRight, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.DownRightRight, ""),
            Arguments.of(Position.of(File.h, Rank.Eight), Direction.DownDownRight, "")
        );
    }
}