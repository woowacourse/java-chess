package domain.position;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import domain.piece.KnightMovementDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.board.File.*;
import static domain.piece.KnightMovementDirection.*;
import static domain.board.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class KnightMovementDirectionTest {

    @DisplayName("입력된 출발지 & 목적지를 계산해서 나이트의 이동 방향을 반환한다.")
    @MethodSource("calculateDirectionTestCase")
    @ParameterizedTest
    void calculateDirectionTest(final Position source, final Position destination, final KnightMovementDirection expect) {
        // When
        KnightMovementDirection knightMovementDirection = calculateDirection(source, destination);

        // Then
        assertThat(knightMovementDirection).isEqualTo(expect);
    }

    private static Stream<Arguments> calculateDirectionTestCase() {
        return Stream.of(
                Arguments.of(position(C, FOUR), position(D, SIX), UP_RIGHT),
                Arguments.of(position(C, FOUR), position(B, SIX), UP_LEFT),
                Arguments.of(position(D, FIVE), position(E, THREE), DOWN_RIGHT),
                Arguments.of(position(D, FIVE), position(C, THREE), DOWN_LEFT),
                Arguments.of(position(C, FOUR), position(E, FIVE), RIGHT_UP),
                Arguments.of(position(C, FOUR), position(E, THREE), RIGHT_DOWN),
                Arguments.of(position(E, FOUR), position(C, FIVE), LEFT_UP),
                Arguments.of(position(E, FOUR), position(C, THREE), LEFT_DOWN)
        );
    }

    @DisplayName("나이트가 이동할 수 없는 출발지/도착지 위치 정보가 입력되면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenInvalidLocationTestCase")
    @ParameterizedTest
    void throwExceptionWhenInvalidLocationTest(final Position source, final Position destination) {
        // When & Then
        assertThatThrownBy(() -> KnightMovementDirection.calculateDirection(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("나이트가 이동할 수 없는 방향입니다.");
    }

    private static Stream<Arguments> throwExceptionWhenInvalidLocationTestCase() {
        return Stream.of(
                Arguments.of(position(B, TWO), position(B, SIX)),
                Arguments.of(position(B, FIVE), position(B, TWO)),
                Arguments.of(position(B, TWO), position(G, TWO)),
                Arguments.of(position(E, FIVE), position(B, FIVE)),
                Arguments.of(position(C, TWO), position(E, FOUR)),
                Arguments.of(position(F, TWO), position(C, FIVE)),
                Arguments.of(position(D, FOUR), position(F, TWO)),
                Arguments.of(position(E, FOUR), position(C, TWO)),
                Arguments.of(position(B, TWO), position(B, TWO)),
                Arguments.of(position(B, ONE), position(C, FOUR)),
                Arguments.of(position(A, EIGHT), position(G, ONE))
        );
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
