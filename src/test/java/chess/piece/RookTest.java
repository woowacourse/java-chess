package chess.piece;

import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static chess.piece.Team.BLACK;
import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RookTest {
    @ParameterizedTest
    @MethodSource("startDestinationTraceProvider")
    void rookPathTest(Position start, Position destination, List<Position> trace) {
        Rook rook = new Rook(BLACK);
        List<Position> actual = rook.findReachablePositions(start, destination);
        assertThat(actual).isEqualTo(trace);
    }

    private static Stream<Arguments> startDestinationTraceProvider() {
        return Stream.of(
                Arguments.of(Position.of(B, FOUR), Position.of(B, SIX), Arrays.asList(Position.of(B, FIVE), Position.of(B, SIX))),
                Arguments.of(Position.of(B, FOUR), Position.of(E, FOUR), Arrays.asList(Position.of(C, FOUR), Position.of(D, FOUR),
                        Position.of(E, FOUR)))
        );
    }

    @DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
    @Test
    void invalidMovementTest() {
        Rook rook = new Rook(BLACK);
        assertThatThrownBy(() -> rook.findReachablePositions(Position.of(A, ONE), Position.of(B, TWO)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }
}
