package chess.model.move;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static chess.model.board.PositionFixture.A4;
import static chess.model.board.PositionFixture.A5;
import static chess.model.board.PositionFixture.A6;
import static chess.model.board.PositionFixture.A7;
import static chess.model.board.PositionFixture.B2;
import static chess.model.board.PositionFixture.B3;
import static chess.model.board.PositionFixture.B4;
import static chess.model.board.PositionFixture.B5;
import static chess.model.board.PositionFixture.C1;
import static chess.model.board.PositionFixture.C4;
import static chess.model.board.PositionFixture.C5;
import static chess.model.board.PositionFixture.C7;
import static chess.model.board.PositionFixture.D2;
import static chess.model.board.PositionFixture.D4;
import static chess.model.board.PositionFixture.H2;
import static chess.model.board.PositionFixture.H3;
import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static chess.model.piece.PieceType.PAWN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.board.Board;
import chess.model.board.PawnBoard;
import chess.model.board.Square;
import chess.model.piece.PieceColor;
import chess.model.position.Position;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardPawnMoveTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }


    @Nested
    @DisplayName("move() Pawn 성공 이후 테스트를 진행한다.")
    class MovePawnSuccessTest {
        private List<Square> squares;

        @BeforeEach
        void init() {
            board.move(H2, H3, WHITE);
            squares = board.getSquares();
        }

        @Test
        @DisplayName("source 위치의 Square는 비어 있게 된다.")
        void sourceSquare_isEmpty() {
            final int emptyIndex = H2.convertToIndex();
            final Square emptySquare = squares.get(emptyIndex);

            assertThat(emptySquare.isEmpty()).isTrue();
        }

        @Test
        @DisplayName("target 위치의 Square는 움직인 기물이 차지한다.")
        void targetSquare_hasPiece() {
            final int targetIndex = H3.convertToIndex();
            final Square targetSquare = squares.get(targetIndex);

            assertThat(targetSquare.isEmpty()).isFalse();
            assertThat(targetSquare.pick().getType()).isEqualTo(PAWN);
        }

    }

    @ParameterizedTest(name = "{2}인 폰이 적의 기물이 있는 대각선으로 공격을 할 수 있다.")
    @MethodSource("initialDiagonal")
    void move_pawn_givenAttackDiagonalTarget_thenSuccess(
            final Position source, final Position target, final PieceColor color
    ) {
        // given
        final Board pawnBoard = PawnBoard.create();

        // when, then
        assertAll(
                () -> assertThatCode(
                        () -> pawnBoard.move(source, target, color)).doesNotThrowAnyException(),
                () -> {
                    final List<Square> squares = pawnBoard.getSquares();
                    assertThat(squares.get(source.convertToIndex()).isEmpty()).isTrue();
                    assertThat(squares.get(target.convertToIndex()).hasPawn()).isTrue();
                }
        );
    }

    private static Stream<Arguments> initialDiagonal() {
        return Stream.of(
                Arguments.of(B4, A5, WHITE),
                Arguments.of(B4, C5, WHITE),
                Arguments.of(B5, A4, BLACK),
                Arguments.of(B5, C4, BLACK)
        );
    }

    @ParameterizedTest(name = "{2}인 폰이 직진할 때, 도착지에 적의 기물이 있으면 실패한다.")
    @MethodSource("initialStraightPawn")
    void move_pawn_givenMoveStraightEnemySquare_thenFail (
            final Position source,
            final Position target,
            final PieceColor color
    ) {
        // given
        final Board pawnBoard = PawnBoard.create();

        // when, then
        assertThatThrownBy(() -> pawnBoard.move(source, target, color))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다");
    }

    private static Stream<Arguments> initialStraightPawn() {
        return Stream.of(
                Arguments.of(B4, B5, WHITE),
                Arguments.of(B5, B4, BLACK)
        );
    }

    @Test
    @DisplayName("폰이 직진할 때, 도착지에 아군의 기물이 있으면 예외가 발생한다.")
    void move_pawn_givenMoveStraightAllySquare_thenFail () {
        // given
        final Board pawnBoard = PawnBoard.create();

        // when
        pawnBoard.move(B4, A5, WHITE);

        // then
        assertThatThrownBy(() -> pawnBoard.move(A4, A5, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다");
    }

    @Test
    @DisplayName("폰은 첫 이동 시 2칸 전진할 수 있다.")
    void move_pawn_whenCallNotMoved_thenStraightDoubleSquareSuccess() {
        // given
        assertThatCode(() -> board.move(A2, A4, WHITE)).doesNotThrowAnyException();

        final List<Square> squares = board.getSquares();

        final int emptySquareIndex = A2.convertToIndex();
        final int targetSquareIndex = A4.convertToIndex();

        assertAll(
                () -> assertThat(squares.get(emptySquareIndex).isEmpty()).isTrue(),
                () -> assertThat(squares.get(targetSquareIndex).hasPawn()).isTrue()
        );
    }

    @Test
    @DisplayName("폰은 첫 이동 시가 아니라면 2칸 전진할 수 없다.")
    void move_pawn_whenCallMoved_thenStraightDoubleSquareFail() {
        board.move(A2, A3, WHITE);

        // when
        assertThatThrownBy(() -> board.move(A3, A5, WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("폰은 첫 이동 시 예외가 발생한 이후에도, 이동하지 않았을 때만 2칸 전진할 수 있다.")
    void move_pawn_whenCallAfterFailAndStateNotMoved_thenStraightDoubleSquareSuccess() {
        assertAll(
                () -> assertThatThrownBy(() -> board.move(A2, A5, WHITE))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> {
                    assertThatCode(() -> board.move(A2, A4, WHITE))
                            .doesNotThrowAnyException();
                    assertThatThrownBy(() -> board.move(A4, A6, WHITE))
                            .isInstanceOf(IllegalArgumentException.class);
                }
        );
    }

    @Test
    @DisplayName("폰은 첫 이동 시 2칸 전진할 때 도착지에 적의 기물이 있다면 예외가 발생한다.")
    void move_pawn_givenInvalidEmptyTarget_whenCallStateNotMoved_thenFail() {
        // given
        board.move(A7, A5, BLACK);
        board.move(A5, A4, BLACK);

        // when, then
        assertThatThrownBy(() -> board.move(A2, A4, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다");
    }

    @Test
    @DisplayName("폰은 첫 이동 시 2칸 전진할 때 도착지에 자신의 기물이 있다면 예외가 발생한다.")
    void move_pawn_givenInvalidMyTarget_whenCallStateNotMoved_thenFail() {
        // given
        board.move(B2, B3, WHITE);
        board.move(C1, B2, WHITE);
        board.move(B2, D4, WHITE);

        // when, then
        assertThatThrownBy(() -> board.move(D2, D4, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 경로로 이동할 수 없습니다");
    }

    @Test
    @DisplayName("폰은 첫 이동 시에 적 기물이 있다고 하더라도 대각선으로 2칸 움직일 수 없다.")
    void move_pawn_givenInvalidDiagonalEnemyTarget_thenFail() {
        board.move(C7, C5, BLACK);
        board.move(C5, C4, BLACK);

        assertThatThrownBy(() -> board.move(A2, C4, WHITE))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 기물은 지정한 방향으로 움직일 수 없습니다.");
    }
}
