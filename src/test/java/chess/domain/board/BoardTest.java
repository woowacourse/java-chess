package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.Role;
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
                        Role.ROOK
                ),
                Arguments.of(
                        1,
                        Role.KNIGHT
                ),
                Arguments.of(
                        30,
                        Role.EMPTY

                ),
                Arguments.of(
                        63,
                        Role.ROOK
                )
        );
    }

    @DisplayName("체스 게임을 할 수 있는 체스판을 초기화한다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("boardTestProvider")
    void Should_Create_When_Board(final int index, final Role role) {
        final Board board = new Board();

        assertThat(board.getPieces().get(index).getRole()).isEqualTo(role);
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 없을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_NoPieceOnPath() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.TWO);
        final Square target = new Square(File.A, Rank.THREE);

        Assertions.assertDoesNotThrow(() -> board.move(source, target));
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 없을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_NoPieceOnPath2() {
        final Board board = new Board();
        final Square source = new Square(File.B, Rank.ONE);
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

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 있을 경우 이동할 수 없다.")
    @Test
    void Should_DontMove_When_PieceOnPath2() {
        final Board board = new Board();
        final Square source = new Square(File.A, Rank.ONE);
        final Square target = new Square(File.A, Rank.FIVE);

        board.move(new Square(File.A, Rank.TWO), new Square(File.A, Rank.FOUR));

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

    @DisplayName("폰이 전진 방향 이동을 할 때 Target 위치에 말이 있을 경우 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_PawnForwardWithTargetNotEmpty() {
        final Board board = new Board();
        final Square source = new Square(File.B, Rank.SIX);
        final Square target = new Square(File.B, Rank.SEVEN);

        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.FOUR));
        board.move(new Square(File.B, Rank.FOUR), new Square(File.B, Rank.FIVE));
        board.move(new Square(File.B, Rank.FIVE), new Square(File.B, Rank.SIX));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(PieceCanNotMoveException.class)
                .hasMessage("이동할 수 없는 말입니다.");
    }

    @DisplayName("폰이 대각선 방향 이동을 할 때 Target 위치에 같은 팀의 말이면 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_PawnDiagonalWithTargetSameTeam() {
        final Board board = new Board();
        final Square source = new Square(File.C, Rank.TWO);
        final Square target = new Square(File.B, Rank.THREE);

        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.THREE));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(PieceCanNotMoveException.class)
                .hasMessage("이동할 수 없는 말입니다.");
    }

    @DisplayName("폰이 대각선 방향 이동을 할 때 Target 위치에 빈 말이 있으면 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_PawnDiagonalWithTargetEmpty() {
        final Board board = new Board();
        final Square source = new Square(File.B, Rank.TWO);
        final Square target = new Square(File.C, Rank.THREE);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(PieceCanNotMoveException.class)
                .hasMessage("이동할 수 없는 말입니다.");
    }
}
