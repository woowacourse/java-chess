package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.exception.PieceCanNotMoveException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {
    private static Stream<Arguments> boardTestProvider() {
        return Stream.of(
                Arguments.of(
                        0,
                        new Rook(Camp.BLACK)
                ),
                Arguments.of(
                        1,
                        new Knight(Camp.BLACK)
                ),
                Arguments.of(
                        30,
                        new Empty()

                ),
                Arguments.of(
                        63,
                        new Rook(Camp.WHITE)
                )
        );
    }

    @DisplayName("체스 게임을 할 수 있는 체스판을 초기화한다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("boardTestProvider")
    void Should_Create_When_Board(final int index, final Piece piece) {
        final Board board = new Board();

        assertThat(board.getPieces().get(index)).isEqualTo(piece);
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 없을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_NoPieceOnPath() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.TWO);
        final Square target = new Square(File.A, Rank.THREE);

        Assertions.assertDoesNotThrow(() -> board.move(source, target));
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 있을 경우 이동할 수 없다.")
    @Test
    void Should_DontMove_When_PieceOnPath() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.ONE);
        final Square target = new Square(File.A, Rank.TWO);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(PieceCanNotMoveException.class)
                .hasMessage("이동할 수 없는 말입니다.");
    }

    @DisplayName("Target에 상대 피스가 있을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_OtherCampPieceOnTarget() {
        final Board board = new Board();
        final Square source = new Square(File.B, Rank.SIX);
        final Square target = new Square(File.A, Rank.SEVEN);

        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.FOUR));
        board.move(new Square(File.B, Rank.FOUR), new Square(File.B, Rank.FIVE));
        board.move(new Square(File.B, Rank.FIVE), new Square(File.B, Rank.SIX));

        Assertions.assertDoesNotThrow(() -> board.move(source, target));
    }
}
