package domain.position;

import domain.board.File;
import domain.board.Position;
import domain.board.Rank;
import domain.piece.PawnMovementDirection;
import domain.piece.PieceColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static domain.piece.PieceColor.*;
import static domain.board.File.*;
import static domain.piece.PawnMovementDirection.*;
import static domain.board.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PawnMovementDirectionTest {

    @DisplayName("입력된 출발지 & 목적지를 계산해서 폰의 이동 방향을 반환한다.")
    @MethodSource("calculateDirectionTestCase")
    @ParameterizedTest
    void calculateDirectionTest(final PieceColor pieceColor, final Position source, final Position destination, final PawnMovementDirection expect) {
        // When
        PawnMovementDirection movementDirection = calculateDirection(pieceColor, source, destination);

        // Then
        assertThat(movementDirection).isEqualTo(expect);
    }

    private static Stream<Arguments> calculateDirectionTestCase() {
        return Stream.of(
                Arguments.of(WHITE, position(C, TWO), position(C, FOUR), UP_TWO_STEP),
                Arguments.of(WHITE, position(C, FOUR), position(C, FIVE), UP_ONE_STEP),
                Arguments.of(WHITE, position(D, FIVE), position(E, SIX), UP_RIGHT),
                Arguments.of(WHITE, position(D, FIVE), position(C, SIX), UP_LEFT),
                Arguments.of(BLACK, position(C, SEVEN), position(C, FIVE), DOWN_TWO_STEP),
                Arguments.of(BLACK, position(C, FOUR), position(C, THREE), DOWN_ONE_STEP),
                Arguments.of(BLACK, position(D, FIVE), position(E, FOUR), DOWN_RIGHT),
                Arguments.of(BLACK, position(D, FIVE), position(C, FOUR), DOWN_LEFT)
        );
    }

    @DisplayName("색상별 방향을 계산할 수 없는 춮발지 & 목적지가 입력되면 예외를 발생시킨다.")
    @MethodSource("throwExceptionWhenInputInvalidPositionTestCase")
    @ParameterizedTest
    void throwExceptionWhenInputInvalidPositionTest(final PieceColor pieceColor, final Position source, final Position destination) {
        // When & Then
        assertThatThrownBy(() -> PawnMovementDirection.calculateDirection(pieceColor, source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(pieceColor.name() + "색상의 폰이 이동할 수 없는 방향입니다.");
    }

    private static Stream<Arguments> throwExceptionWhenInputInvalidPositionTestCase() {
        return Stream.of(
                Arguments.of(BLACK, position(C, TWO), position(C, FOUR)),
                Arguments.of(BLACK, position(C, FOUR), position(C, FIVE)),
                Arguments.of(BLACK, position(D, FIVE), position(E, SIX)),
                Arguments.of(BLACK, position(D, FIVE), position(C, SIX)),
                Arguments.of(WHITE, position(C, SEVEN), position(C, FIVE)),
                Arguments.of(WHITE, position(C, FOUR), position(C, THREE)),
                Arguments.of(WHITE, position(D, FIVE), position(E, FOUR)),
                Arguments.of(WHITE, position(D, FIVE), position(C, FOUR)),
                Arguments.of(WHITE, position(B, TWO), position(B, SIX)),
                Arguments.of(WHITE, position(B, TWO), position(B, ONE)),
                Arguments.of(WHITE, position(B, TWO), position(A, TWO)),
                Arguments.of(WHITE, position(B, TWO), position(C, ONE)),
                Arguments.of(BLACK, position(B, TWO), position(D, TWO)),
                Arguments.of(BLACK, position(B, TWO), position(E, FIVE)),
                Arguments.of(BLACK, position(B, TWO), position(A, THREE))
        );
    }

    private static Position position(final File file, final Rank rank) {
        return new Position(file, rank);
    }
}
