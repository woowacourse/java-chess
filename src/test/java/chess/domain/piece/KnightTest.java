package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class KnightTest {
    @DisplayName("나이트의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForKnightMoveByDirection")
    void knightMove(Piece piece, List<Position> expectedToPositions) {
        assertThat(piece.getPossiblePositions()).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForKnightMoveByDirection() {
        return Stream.of(
                Arguments.of(new Knight('n', new Position(5, 5)),
                        Arrays.asList(
                                new Position(6, 7), new Position(7, 6),
                                new Position(7, 4), new Position(6, 3),
                                new Position(4, 3), new Position(3, 4),
                                new Position(3, 6), new Position(4, 7)
                        )
                )
        );
    }
}
