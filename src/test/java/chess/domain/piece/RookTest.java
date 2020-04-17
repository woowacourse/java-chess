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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RookTest {
    private static final Map<Position, Piece> INITIALIZED_POSITIONS = new HashMap<>();

    @BeforeEach
    void setUp() {
        for (int row = 1; row <= 8; row++) {
            for (int col = 1; col <= 8; col++) {
                String position = (char)(col + 96) + String.valueOf(row);
                INITIALIZED_POSITIONS.put(Position.of(position), Piece.of(PieceType.BLANK));
            }
        }
    }

    @DisplayName("룩의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForRookMoveByDirection")
    void rookMove(MoveStrategy moveStrategy, Piece piece, Position position, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.put(Position.of("e5"), Piece.of(PieceType.WHITE_ROOK));
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(moveStrategy.possiblePositions(board, piece, position)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForRookMoveByDirection() {
        return Stream.of(
                Arguments.of(new MultipleMoveStrategy(), Piece.of(PieceType.WHITE_ROOK), Position.of("e5"),
                        Arrays.asList(
                                Position.of("e6"), Position.of("e7"),
                                Position.of("e8"), Position.of("f5"),
                                Position.of("g5"), Position.of("h5"),
                                Position.of("e4"), Position.of("e3"),
                                Position.of("e2"), Position.of("e1"),
                                Position.of("d5"), Position.of("c5"),
                                Position.of("b5"), Position.of("a5")
                        )
                )
        );
    }
}
