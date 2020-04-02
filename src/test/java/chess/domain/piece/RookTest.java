package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.RookMoveStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private static final List<Piece> INITIALIZED_POSITIONS = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                INITIALIZED_POSITIONS.add(Piece.createBlank(new Position(col, row)));
            }
        }
    }

    @DisplayName("룩의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForRookMoveByDirection")
    void rookMove(MoveStrategy moveStrategy, Piece piece, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.set(36, Piece.createWhiteRook(new Position(5, 5)));
        assertThat(moveStrategy.getPossiblePositions(INITIALIZED_POSITIONS, piece)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForRookMoveByDirection() {
        return Stream.of(
                Arguments.of(new RookMoveStrategy(), Piece.createWhiteRook(new Position(5, 5)),
                        Arrays.asList(
                                new Position(5, 6), new Position(5, 7),
                                new Position(5, 8), new Position(6, 5),
                                new Position(7, 5), new Position(8, 5),
                                new Position(5, 4), new Position(5, 3),
                                new Position(5, 2), new Position(5, 1),
                                new Position(4, 5), new Position(3, 5),
                                new Position(2, 5), new Position(1, 5)
                        )
                )
        );
    }
}
