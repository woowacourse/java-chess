package chess.domain.piece;

import chess.domain.team.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.piece.Direction.*;
import static chess.domain.piece.PieceConstants.BLACK_PAWN;
import static chess.domain.piece.PieceConstants.EMPTY;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceTest {

    Piece piece;

    @BeforeEach
    void setUp() {
        piece = new Piece(Team.WHITE) {
            final List<Direction> moves = List.of(RIGHT, LEFT);

            @Override
            public boolean movable(final Direction direction, final Piece piece) {
                return moves.contains(direction);
            }

            @Override
            public boolean movableByCount(final int count) {
                return count <= 1;
            }
        };
    }

    @ParameterizedTest
    @MethodSource("createHorizontalDirection")
    @DisplayName("오른쪽, 왼쪽으로 이동할 수 있는지 확인하다")
    void movable_true_horizontal(Direction direction) {
        assertTrue(piece.movable(direction, EMPTY));
    }

    private static Stream<Arguments> createHorizontalDirection() {
        return Stream.of(
                Arguments.of(LEFT),
                Arguments.of(RIGHT)
        );
    }

    @ParameterizedTest
    @MethodSource("createDirectionWithoutHorizontal")
    @DisplayName("오른쪽, 왼쪽이외의 방향으로는 이동할 수 없는지 확인하다")
    void movable_false_withoutHorizontal(Direction direction) {
        assertFalse(piece.movable(direction, EMPTY));
    }

    private static Stream<Arguments> createDirectionWithoutHorizontal() {
        return Stream.of(
                Arguments.of(UP),
                Arguments.of(DOWN),

                Arguments.of(RIGHT_UP),
                Arguments.of(RIGHT_DOWN),
                Arguments.of(LEFT_UP),
                Arguments.of(LEFT_DOWN),

                Arguments.of(RIGHT_RIGHT_UP),
                Arguments.of(RIGHT_RIGHT_DOWN),
                Arguments.of(RIGHT_UP_UP),
                Arguments.of(RIGHT_DOWN_DOWN),
                Arguments.of(LEFT_LEFT_UP),
                Arguments.of(LEFT_LEFT_DOWN),
                Arguments.of(LEFT_UP_UP),
                Arguments.of(LEFT_DOWN_DOWN)
        );
    }

    @ParameterizedTest
    @MethodSource("createHorizontalDirection")
    @DisplayName("오른쪽, 왼쪽으로 공격할 수 있는지 확인하다")
    void isAttack_true_horizontal(Direction direction) {
        assertTrue(piece.isAttack(direction, BLACK_PAWN));
    }

    @ParameterizedTest
    @MethodSource("createDirectionWithoutHorizontal")
    @DisplayName("움직일 수 없는 방향으로 이동한다면 공격할 수 없는지 확인하다")
    void isAttack_false_withoutHorizontal(Direction direction) {
        assertFalse(piece.isAttack(direction, BLACK_PAWN));
    }
}
