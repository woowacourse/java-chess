package chess.piece;

import static chess.piece.Team.*;
import static chess.position.File.*;
import static chess.position.Rank.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import chess.position.Position;

public class BishopTest {
    @ParameterizedTest
    @MethodSource("startDestinationTraceProvider")
    void rookPathTest(Position start, Position destination, List<Position> trace) {
        Bishop bishop = new Bishop(BLACK);
        List<Position> actual = bishop.findReachablePositions(start, destination);
        assertThat(actual).isEqualTo(trace);
    }

    private static Stream<Arguments> startDestinationTraceProvider() {
        return Stream.of(
                Arguments.of(Position.of(C, SIX), Position.of(F, THREE),
                    Arrays.asList(Position.of(D, FIVE), Position.of(E, FOUR), Position.of(F, THREE))),
                Arguments.of(Position.of(F, THREE), Position.of(C, SIX),
                    Arrays.asList(Position.of(E, FOUR), Position.of(D, FIVE), Position.of(C, SIX))),
                Arguments.of(Position.of(C, THREE), Position.of(F, SIX),
                    Arrays.asList(Position.of(D, FOUR), Position.of(E, FIVE), Position.of(F, SIX))),
                Arguments.of(Position.of(F, SIX), Position.of(C, THREE),
                    Arrays.asList(Position.of(E, FIVE), Position.of(D, FOUR), Position.of(C, THREE)))
        );
    }

    @DisplayName("허용되지 않은 출발위치와 도착위치인 경우 예외가 발생하는지 테스트")
    @Test
    void invalidMovementTest() {
        Bishop bishop = new Bishop(BLACK);
        assertThatThrownBy(() -> bishop.findReachablePositions(Position.of(A, ONE), Position.of(B, ONE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @DisplayName("체스말의 팀과 종류에 따라 심볼이 반환된다.")
    @ParameterizedTest
    @CsvSource(value = {"BLACK,B", "WHITE,b"})
    void getSymbolTest(Team team, String expected) {
        Piece piece = new Bishop(team);
        assertThat(piece.getSymbol()).isEqualTo(expected);
    }
}
