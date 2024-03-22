package domain.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.position.CommonMovementDirection.DOWN_LEFT;
import static domain.position.CommonMovementDirection.DOWN_RIGHT;
import static domain.position.CommonMovementDirection.UP_LEFT;
import static domain.position.CommonMovementDirection.UP_RIGHT;
import static domain.position.File.*;
import static domain.position.KnightMovementDirection.*;
import static domain.position.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;

class KnightMovementDirectionTest {

    @DisplayName("나이트가 이동 가능한 방향의 출발지/목적지 정보가 입력되면 true를 반환한다.")
    @MethodSource("isMovableDirectionTestCase")
    @ParameterizedTest
    void isMovableDirectionTest(final Position source, final Position destination) {
        // When
        boolean isMovable = isMovableDirection(source, destination);

        // Then
        assertThat(isMovable).isTrue();
    }

    private static Stream<Arguments> isMovableDirectionTestCase() {
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

    @DisplayName("나이트가 이동할 수 없는 출발지/도착지 위치 정보가 입력되면 false를 반환한다.")
    @MethodSource("isNotMovableDirectionTestCase")
    @ParameterizedTest
    void isNotMovableDirectionTest(final Position source, final Position destination) {
        // When
        boolean isMovable = isMovableDirection(source, destination);

        // Then
        assertThat(isMovable).isFalse();
    }

    private static Stream<Arguments> isNotMovableDirectionTestCase() {
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
