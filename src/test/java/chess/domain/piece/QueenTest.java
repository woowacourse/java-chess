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

public class QueenTest {
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

    @DisplayName("퀸의 이동 가능 위치")
    @ParameterizedTest
    @MethodSource("getCasesForQueenMoveByDirection")
    void bishopMove(MoveStrategy moveStrategy, Piece piece, Position position, List<Position> expectedToPositions) {
        INITIALIZED_POSITIONS.put(Position.of("e5"), Piece.of(PieceType.WHITE_QUEEN));
        INITIALIZED_POSITIONS.put(Position.of("e3"), Piece.of(PieceType.WHITE_PAWN));
        INITIALIZED_POSITIONS.put(Position.of("b5"), Piece.of(PieceType.BLACK_KNIGHT));
        INITIALIZED_POSITIONS.put(Position.of("g7"), Piece.of(PieceType.BLACK_ROOK));
        INITIALIZED_POSITIONS.put(Position.of("a1"), Piece.of(PieceType.WHITE_BISHOP));
        Board board = new Board(INITIALIZED_POSITIONS);
        assertThat(moveStrategy.possiblePositions(board, piece, position)).isEqualTo(expectedToPositions);
    }

    private static Stream<Arguments> getCasesForQueenMoveByDirection() {
        return Stream.of(
                Arguments.of(new MultipleMoveStrategy(), Piece.of(PieceType.WHITE_QUEEN), Position.of("e5"),
                        Arrays.asList(
                                Position.of("e6"), Position.of("e7"),
                                Position.of("e8"), Position.of("f6"),
                                Position.of("g7"), Position.of("f5"),
                                Position.of("g5"), Position.of("h5"),
                                Position.of("f4"), Position.of("g3"),
                                Position.of("h2"), Position.of("e4"),
                                Position.of("d4"), Position.of("c3"),
                                Position.of("b2"), Position.of("d5"),
                                Position.of("c5"), Position.of("b5"),
                                Position.of("d6"), Position.of("c7"),
                                Position.of("b8")
                        )
                )
        );
    }
}
