package chess.domain;

import chess.domain.position.ChessDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ChessDirectionTest {

    static Stream<Arguments> findDirectionArguments() {
        return Stream.of(
                Arguments.arguments(0, 1, ChessDirection.UP),
                Arguments.arguments(0, -1, ChessDirection.DOWN),
                Arguments.arguments(-1, 0, ChessDirection.LEFT),
                Arguments.arguments(1, 0, ChessDirection.RIGHT),
                Arguments.arguments(1, 1, ChessDirection.UP_RIGHT),
                Arguments.arguments(-1, 1, ChessDirection.UP_LEFT),
                Arguments.arguments(1, -1, ChessDirection.DOWN_RIGHT),
                Arguments.arguments(-1, -1, ChessDirection.DOWN_LEFT),
                Arguments.arguments(-1, 2, ChessDirection.UP_UP_LEFT),
                Arguments.arguments(1, 2, ChessDirection.UP_UP_RIGHT),
                Arguments.arguments(1, -2, ChessDirection.DOWN_DOWN_RIGHT),
                Arguments.arguments(-1, -2, ChessDirection.DOWN_DOWN_LEFT),
                Arguments.arguments(2, 1, ChessDirection.RIGHT_RIGHT_UP),
                Arguments.arguments(2, -1, ChessDirection.RIGHT_RIGHT_DOWN),
                Arguments.arguments(-2, 1, ChessDirection.LEFT_LEFT_UP),
                Arguments.arguments(-2, -1, ChessDirection.LEFT_LEFT_DOWN)
        );
    }

    @DisplayName("주어진 file 차이와 rank 차이를 이용하여 방향을 찾는다.")
    @ParameterizedTest
    @MethodSource("findDirectionArguments")
    void findDirection(int fileDiff, int rankDiff, ChessDirection expected) {
        //when
        ChessDirection chessDirection = ChessDirection.findDirection(fileDiff, rankDiff);

        //then
        assertThat(chessDirection).isEqualTo(expected);
    }

    @DisplayName("주어진 fileDiff와 rankDiff로 방향 정보를 찾을 수 없을 경우, 예외를 발생한다.")
    @Test
    void findWrongDirection() {
        // when & then
        assertThatThrownBy(() -> ChessDirection.findDirection(-2, -5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 방향입니다.");
    }
}
