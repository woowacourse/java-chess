package chess.domain.position;

import chess.exception.InvalidPositionException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PositionTest {
    @DisplayName("체스판 범위 벗어난 포지션 생성")
    @ParameterizedTest
    @MethodSource("getCasesForPositionsOutOfBoardRange")
    void constructFail(String position) {
        assertThatThrownBy(() -> {
            Position.of(position);
        }).isInstanceOf(InvalidPositionException.class);
    }

    private static Stream<Arguments> getCasesForPositionsOutOfBoardRange() {
        return Stream.of(
                Arguments.of("k6"),
                Arguments.of("t1"),
                Arguments.of("z4"),
                Arguments.of("d10")
        );
    }
}
