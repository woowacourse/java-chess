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

class QueenTest {

    static Stream<Arguments> cannotQueenMoveAllDirectionArguments() {
        return Stream.of(
                Arguments.arguments(new Position("d4"), new Position("d1")),
                Arguments.arguments(new Position("d4"), new Position("g4")),
                Arguments.arguments(new Position("d4"), new Position("d7")),
                Arguments.arguments(new Position("d4"), new Position("a4")),
                Arguments.arguments(new Position("d4"), new Position("g7")),
                Arguments.arguments(new Position("d4"), new Position("a7")),
                Arguments.arguments(new Position("d4"), new Position("g1")),
                Arguments.arguments(new Position("d4"), new Position("a1"))
        );
    }

    @DisplayName("킹은 모든 방향으로 한 칸 움직일 수 있다.")
    @ParameterizedTest
    @MethodSource("cannotQueenMoveAllDirectionArguments")
    void canQueenMoveAllDirection(Position source, Position target) {
        // given
        Piece queen = new Queen(PieceColor.BLACK);

        // when
        boolean result = queen.isMovable(source, target);

        // then
        assertThat(result).isTrue();
    }
}