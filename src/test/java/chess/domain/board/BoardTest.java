package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Camp;
import chess.domain.piece.Empty;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
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
                        new Rook(Camp.BLACK, new Square(File.EMPTY, Rank.EMPTY))
                ),
                Arguments.of(
                        1,
                        new Knight(Camp.BLACK, new Square(File.EMPTY, Rank.EMPTY))
                ),
                Arguments.of(
                        30,
                        Empty.of()

                ),
                Arguments.of(
                        63,
                        new Rook(Camp.WHITE, new Square(File.EMPTY, Rank.EMPTY))
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
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동할 수 없습니다.");
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

    @DisplayName("White Pawn이 세로로 연속해서 존재한다.")
    @Test
    void Should_True_When_WhitePawnExistVertically() {
        final Board board = new Board();
        board.move(new Square(File.B, Rank.SEVEN), new Square(File.B, Rank.FIVE));
        board.move(new Square(File.B, Rank.FIVE), new Square(File.B, Rank.FOUR));
        board.move(new Square(File.B, Rank.FOUR), new Square(File.B, Rank.THREE));
        board.move(new Square(File.A, Rank.TWO), new Square(File.B, Rank.THREE));

        assertThat(board.countVerticalPawn(Camp.WHITE)).isEqualTo(2);
    }

    @DisplayName("Black Pawn이 세로로 연속해서 존재한다.")
    @Test
    void Should_True_When_BlackPawnExistVertically() {
        final Board board = new Board();
        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.FOUR));
        board.move(new Square(File.B, Rank.FOUR), new Square(File.B, Rank.FIVE));
        board.move(new Square(File.B, Rank.FIVE), new Square(File.B, Rank.SIX));
        board.move(new Square(File.A, Rank.SEVEN), new Square(File.B, Rank.SIX));

        assertThat(board.countVerticalPawn(Camp.BLACK)).isEqualTo(2);
    }
}
