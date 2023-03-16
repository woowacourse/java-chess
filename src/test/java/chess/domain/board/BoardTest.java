package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.piece.Camp;
import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BoardTest {
    private static Stream<Arguments> boardTestProvider() {
        return Stream.of(
                Arguments.of(
                        new Square(File.A, Rank.ONE),
                        new Rook(Camp.WHITE)
                ),
                Arguments.of(
                        new Square(File.E, Rank.ONE),
                        new King(Camp.WHITE)
                ),
                Arguments.of(
                        new Square(File.C, Rank.SEVEN),
                        new Pawn(Camp.BLACK)

                ),
                Arguments.of(
                        new Square(File.E, Rank.EIGHT),
                        new King(Camp.BLACK)
                )
        );
    }

    @DisplayName("체스 게임을 할 수 있는 체스판을 초기화한다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("boardTestProvider")
    void Should_Create_When_Board(final Square square, final Piece piece) {
        final Board board = new Board();

        assertThat(board.getPiece(square))
                .isEqualTo(piece);
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 없을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_NoPieceOnPath() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.TWO);
        final Square target = new Square(File.A, Rank.THREE);

        final boolean isMovable = board.move(source, target);

        assertThat(isMovable).isTrue();
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 있을 경우 이동할 수 없다.")
    @Test
    void Should_DontMove_When_PieceOnPath() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.ONE);
        final Square target = new Square(File.A, Rank.TWO);

        final boolean isMovable = board.move(source, target);

        assertThat(isMovable).isFalse();
    }

    @DisplayName("Target에 상대 피스가 있을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_OtherCampPieceOnTarget() {
        final Board board = new Board();

        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.FOUR));
        board.move(new Square(File.B, Rank.FOUR), new Square(File.B, Rank.FIVE));

        final boolean isMovable = board.move(new Square(File.B, Rank.FIVE), new Square(File.A, Rank.SIX));

        assertThat(isMovable).isTrue();
    }
}
