package chess.domain.board;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import chess.domain.piece.PieceType;
import chess.exception.PathBlockedException;
import chess.exception.PawnMoveDiagonalException;
import chess.exception.PawnMoveForwardException;
import chess.exception.TargetSameTeamException;
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
                        PieceType.ROOK
                ),
                Arguments.of(
                        1,
                        PieceType.KNIGHT
                ),
                Arguments.of(
                        30,
                        PieceType.EMPTY

                ),
                Arguments.of(
                        63,
                        PieceType.ROOK
                )
        );
    }

    @DisplayName("체스 게임을 할 수 있는 체스판을 초기화한다.")
    @ParameterizedTest(name = "{displayName} [{index}]")
    @MethodSource("boardTestProvider")
    void Should_Create_When_Board(int index, PieceType pieceType) {
        Board board = new Board();

        assertThat(board.getPieces().get(index).getPieceType()).isEqualTo(pieceType);
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 없을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_NoPieceOnPath() {
        Board board = new Board();
        Square source = new Square(File.A, Rank.TWO);
        Square target = new Square(File.A, Rank.THREE);

        Assertions.assertDoesNotThrow(() -> board.move(source, target));
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 없을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_NoPieceOnPath2() {
        Board board = new Board();
        Square source = new Square(File.B, Rank.ONE);
        Square target = new Square(File.A, Rank.THREE);

        Assertions.assertDoesNotThrow(() -> board.move(source, target));
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 있을 경우 이동할 수 없다.")
    @Test
    void Should_DontMove_When_PieceOnPath() {
        Board board = new Board();
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.TWO);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(TargetSameTeamException.class)
                .hasMessage("타겟 지점의 말이 같은 진영의 말입니다.");
    }

    @DisplayName("Source부터 Target까지의 경로 상에 피스가 있을 경우 이동할 수 없다.")
    @Test
    void Should_DontMove_When_PieceOnPath2() {
        Board board = new Board();
        Square source = new Square(File.A, Rank.ONE);
        Square target = new Square(File.A, Rank.FIVE);

        board.move(new Square(File.A, Rank.TWO), new Square(File.A, Rank.FOUR));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(PathBlockedException.class)
                .hasMessage("이동 경로에 말이 존재합니다.");
    }

    @DisplayName("Target에 상대 피스가 있을 경우 이동할 수 있다.")
    @Test
    void Should_Move_When_OtherCampPieceOnTarget() {
        Board board = new Board();
        Square source = new Square(File.B, Rank.SIX);
        Square target = new Square(File.A, Rank.SEVEN);

        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.FOUR));
        board.move(new Square(File.B, Rank.FOUR), new Square(File.B, Rank.FIVE));
        board.move(new Square(File.B, Rank.FIVE), new Square(File.B, Rank.SIX));

        Assertions.assertDoesNotThrow(() -> board.move(source, target));
    }

    @DisplayName("폰이 전진 방향 이동을 할 때 Target 위치에 말이 있을 경우 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_PawnForwardWithTargetNotEmpty() {
        Board board = new Board();
        Square source = new Square(File.B, Rank.SIX);
        Square target = new Square(File.B, Rank.SEVEN);

        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.FOUR));
        board.move(new Square(File.B, Rank.FOUR), new Square(File.B, Rank.FIVE));
        board.move(new Square(File.B, Rank.FIVE), new Square(File.B, Rank.SIX));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(PawnMoveForwardException.class)
                .hasMessage("폰은 대각선으로만 상대 진영말을 잡을 수 있습니다.");
    }

    @DisplayName("폰이 대각선 방향 이동을 할 때 Target 위치에 같은 팀의 말이면 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_PawnDiagonalWithTargetSameTeam() {
        Board board = new Board();
        Square source = new Square(File.C, Rank.TWO);
        Square target = new Square(File.B, Rank.THREE);

        board.move(new Square(File.B, Rank.TWO), new Square(File.B, Rank.THREE));

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(TargetSameTeamException.class)
                .hasMessage("타겟 지점의 말이 같은 진영의 말입니다.");
    }

    @DisplayName("폰이 대각선 방향 이동을 할 때 Target 위치에 빈 말이 있으면 움직일 수 없다.")
    @Test
    void Should_ThrowException_When_PawnDiagonalWithTargetEmpty() {
        Board board = new Board();
        Square source = new Square(File.B, Rank.TWO);
        Square target = new Square(File.C, Rank.THREE);

        assertThatThrownBy(() -> board.move(source, target))
                .isInstanceOf(PawnMoveDiagonalException.class)
                .hasMessage("폰은 상대 팀이 있을 때만 대각선으로 움직일 수 있습니다.");
    }
}
