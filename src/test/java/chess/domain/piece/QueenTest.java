package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.MultipleMoveStrategy;
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

public class QueenTest {
    private static final List<Piece> INITIALIZED_POSITIONS = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                INITIALIZED_POSITIONS.add(Blank.create(new Position(col, row)));
            }
        }
    }

    @DisplayName("퀸의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForQueenMoveByDirection")
    void bishopMove(MoveStrategy moveStrategy, Piece piece, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.set(36, Queen.createWhite(new Position(5, 5)));
        INITIALIZED_POSITIONS.set(20, Pawn.createWhite(new Position(5, 3)));
        INITIALIZED_POSITIONS.set(33, Knight.createBlack(new Position(2, 5)));
        INITIALIZED_POSITIONS.set(54, Rook.createBlack(new Position(7, 7)));
        INITIALIZED_POSITIONS.set(0, Bishop.createWhite(new Position(1, 1)));
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(moveStrategy.possiblePositions(board, piece)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForQueenMoveByDirection() {
        return Stream.of(
                Arguments.of(new MultipleMoveStrategy(), Queen.createWhite(new Position(5, 5)),
                        Arrays.asList(
                                new Position(5, 6), new Position(5, 7),
                                new Position(5, 8), new Position(6, 6),
                                new Position(7, 7), new Position(6, 5),
                                new Position(7, 5), new Position(8, 5),
                                new Position(6, 4), new Position(7, 3),
                                new Position(8, 2), new Position(5, 4),
                                new Position(4, 4), new Position(3, 3),
                                new Position(2, 2), new Position(4, 5),
                                new Position(3, 5), new Position(2, 5),
                                new Position(4, 6), new Position(3, 7),
                                new Position(2, 8)
                        )
                )
        );
    }
}
