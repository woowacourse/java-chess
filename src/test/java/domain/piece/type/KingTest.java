package domain.piece.type;

import domain.piece.Piece;
import domain.piece.PieceColor;
import domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    static Stream<Arguments> canKingMoveAllDirectionOneStepArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("d3")),
                Arguments.arguments(new Position("d4"), new Position("e4")),
                Arguments.arguments(new Position("d4"), new Position("d5")),
                Arguments.arguments(new Position("d4"), new Position("c4")),
                Arguments.arguments(new Position("d4"), new Position("e5")),
                Arguments.arguments(new Position("d4"), new Position("c5")),
                Arguments.arguments(new Position("d4"), new Position("e3")),
                Arguments.arguments(new Position("d4"), new Position("c3"))
        );
    }

    static Stream<Arguments> cannotKingMoveAllDirectionMoreThanTwoStepArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("d2")),
                Arguments.arguments(new Position("d4"), new Position("f4")),
                Arguments.arguments(new Position("d4"), new Position("d6")),
                Arguments.arguments(new Position("d4"), new Position("b4")),
                Arguments.arguments(new Position("d4"), new Position("f6")),
                Arguments.arguments(new Position("d4"), new Position("b6")),
                Arguments.arguments(new Position("d4"), new Position("f2")),
                Arguments.arguments(new Position("d4"), new Position("b2"))
        );
    }

    @DisplayName("킹은 모든 방향으로 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("canKingMoveAllDirectionOneStepArguments")
    void canKingMoveAllDirectionOneStep(Position source, Position target) {
        // given
        Piece king = new King(PieceColor.BLACK);

        // when
        boolean result = king.isInMovableRange(source, target);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("킹은 모든 방향으로 두 칸 이상 움직일 수 없다.")
    @ParameterizedTest
    @MethodSource("cannotKingMoveAllDirectionMoreThanTwoStepArguments")
    void cannotKingMoveAllDirectionMoreThanTwoStep(Position source, Position target) {
        // given
        Piece king = new King(PieceColor.BLACK);

        // when
        boolean result = king.isInMovableRange(source, target);

        // then
        assertThat(result).isFalse();
    }
}