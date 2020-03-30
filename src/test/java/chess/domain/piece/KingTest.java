package chess.domain.piece;

import chess.domain.position.Position;
import chess.domain.strategy.*;
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

public class KingTest {
    private static final List<Piece> INITIALIZED_POSITIONS = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                INITIALIZED_POSITIONS.add(new Blank(new BlankMoveStrategy(),  '.', Team.BLANK, new Position(col, row)));
            }
        }
    }

    @DisplayName("킹의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForKingMoveByDirection")
    void kingMove(MoveStrategy moveStrategy, Piece piece, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.set(36, new King(new KingMoveStrategy(), 'k', Team.WHITE, new Position(5, 5)));
        INITIALIZED_POSITIONS.set(27, new Rook(new RookMoveStrategy(), 'r', Team.WHITE, new Position(4, 4)));
        INITIALIZED_POSITIONS.set(37, new Bishop(new BishopMoveStrategy(), 'B', Team.BLACK, new Position(6, 5)));
        assertThat(moveStrategy.getPossiblePositions(INITIALIZED_POSITIONS, piece)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForKingMoveByDirection() {
        return Stream.of(
                Arguments.of(new KingMoveStrategy(), new King(new KingMoveStrategy(), 'k', Team.WHITE, new Position(5, 5)),
                        Arrays.asList(
                                new Position(5, 6),
                                new Position(6, 6),
                                new Position(6, 5),
                                new Position(6, 4),
                                new Position(5, 4),
                                new Position(4, 5),
                                new Position(4, 6)
                        )
                )
        );
    }
}
