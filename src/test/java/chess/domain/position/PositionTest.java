package chess.domain.position;

import chess.exception.OutOfBoardRangeException;
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
    void constructFail(int x, int y) {
        assertThatThrownBy(() -> {
            new Position(x, y);
        }).isInstanceOf(OutOfBoardRangeException.class)
                .hasMessage("체스 보드 판의 범위를 넘어섰습니다.");
    }

    private static Stream<Arguments> getCasesForPositionsOutOfBoardRange() {
        return Stream.of(
                Arguments.of(8, 9),
                Arguments.of(9, 8),
                Arguments.of(0, 1),
                Arguments.of(1, 0)
        );
    }
}
