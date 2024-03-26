package domain.position;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import domain.piece.CommonMovementDirection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.board.File.*;
import static domain.piece.CommonMovementDirection.*;
import static domain.board.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CommonMovementDirectionTest {

    @DisplayName("입력받은 출발지/목적지의 거리를 바탕으로 이동방향을 반환한다.")
    @MethodSource("findMovementDirectionTestCase")
    @ParameterizedTest
    void findMovementDirection(final Position source, final Position destination, final CommonMovementDirection expect) {
        // When
        CommonMovementDirection commonMovementDirection = CommonMovementDirection.calculateDirection(source, destination);

        // Then
        assertThat(commonMovementDirection).isEqualTo(expect);
    }

    private static Stream<Arguments> findMovementDirectionTestCase() {
        return Stream.of(
                Arguments.of(position(B, TWO), position(B, SIX), UP),
                Arguments.of(position(B, FIVE), position(B, TWO), DOWN),
                Arguments.of(position(B, TWO), position(G, TWO), RIGHT),
                Arguments.of(position(E, FIVE), position(B, FIVE), LEFT),
                Arguments.of(position(C, TWO), position(E, FOUR), UP_RIGHT),
                Arguments.of(position(F, TWO), position(C, FIVE), UP_LEFT),
                Arguments.of(position(D, FOUR), position(F, TWO), DOWN_RIGHT),
                Arguments.of(position(E, FOUR), position(C, TWO), DOWN_LEFT)
        );
    }

    @DisplayName("방향 계산이 불가능한 출발지/목적지 위치 정보가 입력되면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenInvalidSourceAndDestinationTestCase")
    @ParameterizedTest
    void throwExceptionWhenInvalidSourceAndDestination(final Position source, final Position destination) {
        // When & THen
        assertThatThrownBy(() -> CommonMovementDirection.calculateDirection(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상/하/좌/우 혹은 대각선으로 이동할 수 없는 칸입니다.");
    }

    private static Stream<Arguments> throwExceptionWhenInvalidSourceAndDestinationTestCase() {
        return Stream.of(
                Arguments.of(position(B, TWO), position(B, TWO)),
                Arguments.of(position(B, ONE), position(C, FOUR)),
                Arguments.of(position(A, EIGHT), position(G, ONE))
        );
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
