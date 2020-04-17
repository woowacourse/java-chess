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

public class KingTest {
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

    @DisplayName("킹의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForKingMoveByDirection")
    void kingMove(MoveStrategy moveStrategy, Piece piece, Position position, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.put(Position.of("e5"), Piece.of(PieceType.WHITE_KING));
        INITIALIZED_POSITIONS.put(Position.of("d4"), Piece.of(PieceType.WHITE_ROOK));
        INITIALIZED_POSITIONS.put(Position.of("f5"), Piece.of(PieceType.BLACK_BISHOP));
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(moveStrategy.possiblePositions(board, piece, position)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForKingMoveByDirection() {
        return Stream.of(
                Arguments.of(new SingleMoveStrategy(), Piece.of(PieceType.WHITE_KING), Position.of("e5"),
                        Arrays.asList(
                                Position.of("e6"),
                                Position.of("f6"),
                                Position.of("f5"),
                                Position.of("f4"),
                                Position.of("e4"),
                                Position.of("d5"),
                                Position.of("d6")
                        )
                )
        );
    }
}
