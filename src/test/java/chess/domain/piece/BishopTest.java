package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
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

public class BishopTest {
    private static final List<Piece> INITIALIZED_POSITIONS = new ArrayList<>();

    @BeforeEach
    void setUp() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                INITIALIZED_POSITIONS.add(new Blank('.', Team.BLANK, new Position(col, row)));
            }
        }
    }

    @DisplayName("비숍의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForBishopMoveByDirection")
    void bishopMove(Piece piece, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.set(36, new Bishop('b', Team.WHITE, new Position(5, 5)));
        INITIALIZED_POSITIONS.set(54, new Rook('R', Team.BLACK, new Position(7, 7)));
        INITIALIZED_POSITIONS.set(0, new Queen('q', Team.WHITE, new Position(1,1)));
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(piece.getPossiblePositions(board)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForBishopMoveByDirection() {
        return Stream.of(
                Arguments.of(new Bishop('b', Team.WHITE, new Position(5, 5)),
                        Arrays.asList(
                                new Position(6, 6), new Position(7, 7),
                                new Position(6, 4), new Position(7, 3),
                                new Position(8, 2), new Position(4, 4),
                                new Position(3, 3), new Position(2, 2),
                                new Position(4, 6), new Position(3, 7),
                                new Position(2, 8)
                        )
                )
        );
    }
}
