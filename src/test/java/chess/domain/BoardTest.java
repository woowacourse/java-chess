package chess.domain;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.PieceType.PAWN;
import static chess.mock.MockPosition.*;
import static org.assertj.core.api.Assertions.*;

class BoardTest {
    @DisplayName("생성한다")
    @Test
    void create() {
        // expect
        assertThatNoException().isThrownBy(() -> Board.create());
    }

    @DisplayName("기물이 움직이다.")
    @ParameterizedTest
    @MethodSource("moveDummy")
    void move(
            final List<Position> sources,
            final List<Position> targets,
            final List<Color> currentPlayers,
            final Position position,
            final PieceType expectedPieceType
    ) {
        // given
        final Board board = Board.create();
        final int moveCount = sources.size();

        // when
        for (int current = 0; current < moveCount; current++) {
            final Position source = sources.get(current);
            final Position target = targets.get(current);
            final Color currentPlayer = currentPlayers.get(current);

            board.move(source, target, currentPlayer);
        }

        final Map<Position, Piece> currentBoard = board.getBoard();
        final Piece findPiece = currentBoard.get(position);

        // then
        assertThat(findPiece.isSamePieceType(expectedPieceType)).isTrue();
    }

    static Stream<Arguments> moveDummy() {
        return Stream.of(
                Arguments.arguments(
                        List.of(POSITION_1_6),
                        List.of(POSITION_1_5),
                        List.of(WHITE),
                        POSITION_1_5,
                        PAWN
                ),
                Arguments.arguments(
                        List.of(POSITION_1_6, POSITION_0_1, POSITION_1_5, POSITION_0_2),
                        List.of(POSITION_1_5, POSITION_0_2, POSITION_1_4, POSITION_0_3),
                        List.of(WHITE, BLACK, WHITE, BLACK),
                        POSITION_0_3,
                        PAWN
                ),
                Arguments.arguments(
                        // Pawn NORTH WEST
                        List.of(POSITION_1_6, POSITION_0_1, POSITION_1_5, POSITION_0_2, POSITION_1_4),
                        List.of(POSITION_1_5, POSITION_0_2, POSITION_1_4, POSITION_0_3, POSITION_0_3),
                        List.of(WHITE, BLACK, WHITE, BLACK, WHITE),
                        POSITION_0_3,
                        PAWN
                ),
                Arguments.arguments(
                        // Pawn SOUTH EAST
                        List.of(POSITION_1_6, POSITION_0_1, POSITION_1_4, POSITION_0_2),
                        List.of(POSITION_1_4, POSITION_0_2, POSITION_1_3, POSITION_1_3),
                        List.of(WHITE, BLACK, WHITE, BLACK),
                        POSITION_1_3,
                        PAWN
                )
        );
    }

    @DisplayName("자신의 기물을 선택하지 않은 경우, 같은 색깔의 기물을 타겟으로 지정할 경우에 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("invalidColorDummy")
    void throwExceptionWhenInvalidColor(final Position source, final Position target, final Color player) {
        // given
        final Board board = Board.create();

        // expect
        assertThatThrownBy(() -> board.move(source, target, player))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> invalidColorDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_1_6, POSITION_2_6, WHITE),
                Arguments.arguments(POSITION_1_1, POSITION_2_1, WHITE),
                Arguments.arguments(POSITION_1_6, POSITION_1_5, BLACK),
                Arguments.arguments(POSITION_1_1, POSITION_1_2, WHITE)
        );
    }

    @DisplayName("source와 target 중간에 기물이 있을 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("targetBlockedDummy")
    void throwExceptionWhenTargetBlocked(final Position source, final Position target, final Color player) {
        // given
        final Board board = Board.create();

        // expect
        assertThatThrownBy(() -> board.move(source, target, player))
                .isInstanceOf(IllegalArgumentException.class);
    }

    static Stream<Arguments> targetBlockedDummy() {
        return Stream.of(
                Arguments.arguments(POSITION_0_7, POSITION_0_1, WHITE),
                Arguments.arguments(POSITION_0_7, POSITION_0_2, WHITE),
                Arguments.arguments(POSITION_0_7, POSITION_0_3, WHITE),
                Arguments.arguments(POSITION_2_7, POSITION_1_6, WHITE),
                Arguments.arguments(POSITION_2_7, POSITION_0_5, WHITE),
                Arguments.arguments(POSITION_2_7, POSITION_3_6, WHITE),
                Arguments.arguments(POSITION_2_7, POSITION_4_5, WHITE),
                Arguments.arguments(POSITION_3_7, POSITION_3_6, WHITE),
                Arguments.arguments(POSITION_3_7, POSITION_2_7, WHITE),
                Arguments.arguments(POSITION_3_7, POSITION_4_7, WHITE),
                Arguments.arguments(POSITION_4_7, POSITION_4_6, WHITE),
                Arguments.arguments(POSITION_4_7, POSITION_3_7, WHITE),
                Arguments.arguments(POSITION_4_7, POSITION_5_7, WHITE),
                Arguments.arguments(POSITION_4_7, POSITION_3_6, WHITE),
                Arguments.arguments(POSITION_4_7, POSITION_5_6, WHITE)
        );
    }
}
