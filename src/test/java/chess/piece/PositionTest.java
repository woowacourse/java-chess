package chess.piece;

import static chess.piece.detail.Color.WHITE;
import static chess.piece.detail.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.detail.Direction;
import chess.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

public class PositionTest {

    @ParameterizedTest
    @CsvSource(value = {"a,1", "b,5", "h,8"})
    @DisplayName("행과 열이 같으면 동일하다.")
    void cachedPosition(final String column, final int row) {
        assertThat(Position.of(column, row)).isSameAs(Position.of(column, row));
    }

    @ParameterizedTest
    @MethodSource("provideForPositionAndDirection")
    @DisplayName("출발지와 도착지의 방향을 구한다.")
    void getDirectionFromTo(final Position from, final Position to, final Direction direction, final Piece piece) {
        assertThat(piece.getDirection(from, to)).isEqualTo(direction);
    }

    static Stream<Arguments> provideForPositionAndDirection() {
        return Stream.of(
                Arguments.of("d4", "d6", N, new Queen(WHITE)),
                Arguments.of("d4", "f6", NE, new Queen(WHITE)),
                Arguments.of("d4", "f4", E, new Queen(WHITE)),
                Arguments.of("d4", "f2", SE, new Queen(WHITE)),
                Arguments.of("d4", "d2", S, new Queen(WHITE)),
                Arguments.of("d4", "b2", SW, new Queen(WHITE)),
                Arguments.of("d4", "a4", W, new Queen(WHITE)),
                Arguments.of("d4", "b6", NW, new Queen(WHITE))
        );
    }


}
