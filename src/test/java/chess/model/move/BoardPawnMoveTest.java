package chess.model.move;

import static chess.model.board.PositionFixture.A2;
import static chess.model.board.PositionFixture.A3;
import static chess.model.board.PositionFixture.A4;
import static chess.model.board.PositionFixture.A5;
import static chess.model.board.PositionFixture.A6;
import static chess.model.board.PositionFixture.B4;
import static chess.model.board.PositionFixture.B5;
import static chess.model.board.PositionFixture.C4;
import static chess.model.board.PositionFixture.C5;
import static chess.model.board.PositionFixture.H2;
import static chess.model.board.PositionFixture.H3;
import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.board.Board;
import chess.model.board.PawnBoard;
import chess.model.board.Square;
import chess.model.piece.PieceColor;
import chess.model.piece.PieceType;
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

public class BoardPawnMoveTest {

    private Board board;

    @BeforeEach
    void init() {
        board = Board.create();
    }


    @Nested
    @DisplayName("move() Pawn 성공 테스트를 진행한다.")
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
            assertThat(targetSquare.pick().getType()).isEqualTo(PieceType.PAWN);
        }

    }

    @ParameterizedTest(name = "{2}인 폰이 대각선으로 공격을 할 수 있는지 테스트한다.")
    @MethodSource("initialDiagonal")
    void move_whenPawnAttackDiagonal_thenSuccess(
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

    @ParameterizedTest(name = "{2}인 폰이 직진할 때, 도착지에 기물이 있으면 실패한다.")
    @MethodSource("initialStraightPawn")
    void move_whenPawnMoveStraightEnemySquare_thenFail (
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
    @DisplayName("dfad")
    void adsf() {
        board.move(A2, A3, WHITE);

        // when
        assertThatThrownBy(() -> board.move(A3, A5, WHITE))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("asdf")
    void asdf() {
        // 이동 가능
        try {
            board.move(A2, A5, WHITE); // exception, 이동 안함
        } catch (Exception e) {
            System.out.println("can't move");
        }

        board.move(A2, A4, WHITE); // 이동 가능

        assertThatThrownBy(() -> board.move(A4, A6, WHITE))
                .isInstanceOf(IllegalArgumentException.class);// 이동 불가능

    }
}
