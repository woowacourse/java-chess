package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.strategy.MoveStrategy;
import chess.domain.strategy.SingleMoveStrategy;
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

public class KnightTest {
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

    @DisplayName("나이트의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForKnightMoveByDirection")
    void knightMove(MoveStrategy moveStrategy,  Piece piece, Position position, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.put(Position.of("e5"), Piece.of(PieceType.WHITE_KNIGHT));
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(moveStrategy.possiblePositions(board, piece, position)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForKnightMoveByDirection() {
        return Stream.of(
                Arguments.of(new SingleMoveStrategy(), Piece.of(PieceType.WHITE_KNIGHT), Position.of("e5"),
                        Arrays.asList(
                                Position.of("f7"), Position.of("g6"),
                                Position.of("g4"), Position.of("f3"),
                                Position.of("d3"), Position.of("c4"),
                                Position.of("c6"), Position.of("d7")
                        )
                )
        );
    }
}
