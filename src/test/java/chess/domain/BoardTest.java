package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
                        new Piece(Role.ROCK, Camp.WHITE)
                ),
                Arguments.of(
                        new Square(File.E, Rank.ONE),
                        new Piece(Role.KING, Camp.WHITE)
                ),
                Arguments.of(
                        new Square(File.C, Rank.SEVEN),
                        new Piece(Role.PAWN, Camp.BLACK)

                ),
                Arguments.of(
                        new Square(File.E, Rank.EIGHT),
                        new Piece(Role.KING, Camp.BLACK)
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

        final boolean isMovable = board.isTherePieceFromSourceToTarget(source, target);

        assertThat(isMovable).isTrue();
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 있을 경우 이동할 수 없다.")
    @Test
    void Should_DontMove_When_PieceOnPath() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.ONE);
        final Square target = new Square(File.A, Rank.TWO);

        final boolean isMovable = board.isTherePieceFromSourceToTarget(source, target);

        assertThat(isMovable).isFalse();
    }

    @DisplayName("Target에 상대 피스가 있을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_OtherCampPieceOnTarget() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.TWO);
        final Square target = new Square(File.A, Rank.SEVEN);

        final boolean isMovable = board.isTherePieceFromSourceToTarget(source, target);

        assertThat(isMovable).isTrue();
    }
}
