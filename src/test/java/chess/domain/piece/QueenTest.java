package chess.domain.piece;

import chess.domain.piece.Color;
import chess.domain.piece.Queen;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class QueenTest {

    @ParameterizedTest
    @CsvSource(value = {"1:1", "1:-1", "-1:1", "-1:-1"}, delimiter = ':')
    @DisplayName("queen 기물 대각선 이동 위치 검증 - true")
    void checkQueenPositionDiagonal(int a, int b) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovable(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"0:1", "0:-1", "1:0", "-1:-0"}, delimiter = ':')
    @DisplayName("queen 기물 상하좌우 이동 위치 검증 - true")
    void checkQueenPositionUpDownLeftRight(int a, int b) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovable(new Position(4, 4), new Position(4 + a, 4 + b))).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"2:3", "1:2", "2:-1", "-1:-2"}, delimiter = ':')
    @DisplayName("queen 기물 이동 위치 검증 - false")
    void checkBishopPositionWhenFalse(int a, int b) {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen.isMovable(new Position(4, 4), new Position(4 + a, 4 + b))).isFalse();
    }

    @ParameterizedTest
    @MethodSource("possiblePositionOfQueen")
    @DisplayName("source와 target 사이에 퀸이 이동가능한 위치 리스트 반환")
    void checkAllPositionOfPossible(Position source, Position target, Position coordinate) {
        Queen queen = new Queen(Color.WHITE);
        assertThat(queen.computeBetweenTwoPositionByLine(source, target))
                .isEqualTo(List.of(coordinate));
    }

    static Stream<Arguments> possiblePositionOfQueen() {
        return Stream.of(
                Arguments.of(new Position(7, 2), new Position(5, 4),
                        new Position(6, 3)),
                Arguments.of(new Position(0, 0), new Position(0, 2),
                        new Position(0, 1))
        );
    }
}
