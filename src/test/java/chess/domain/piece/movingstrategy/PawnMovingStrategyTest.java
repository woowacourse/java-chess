package chess.domain.piece.movingstrategy;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.domain.position.Position;
import chess.domain.position.XAxis;
import chess.domain.position.YAxis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnMovingStrategyTest {

    @DisplayName("같은 위치로 이동할 수 없다.")
    @Test
    void movePiece_returnsFalseWithSameFromAndTo() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.TWO),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("흰색 진영의 폰이 처음 이동하는 경우 두칸을 전진할 수 있다.")
    @Test
    void movePiece_ifPieceIsPawnMoveForwardTwiceOnFirstMoveInWhiteTeam() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.FOUR),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("흰색 진영의 폰이 처음 이동하는 경우가 아니라면 한칸을 전진할 수 있다.")
    @Test
    void movePiece_ifPieceIsPawnMoveForwardOnceInWhiteTeam() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.THREE), Position.of(XAxis.A, YAxis.FOUR),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("흰색 진영의 폰이 처음 이동하는 경우가 아니라면 두 칸을 전진할 수 없다.")
    @Test
    void movePiece_ifPieceIsPawnMoveForwardTwiceWhenNotInitialInWhiteTeam() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.THREE), Position.of(XAxis.A, YAxis.FIVE),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("흰색 진영의 폰은 아래방향으로 이동할 수 없다.")
    @Test
    void executeCommand_returnsFalseIfWhitePawnTryToMoveDown() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.TWO), Position.of(XAxis.A, YAxis.THREE), PieceColor.WHITE);
        boolean actual = board.executeCommand(
                        Position.of(XAxis.A, YAxis.THREE), Position.of(XAxis.A, YAxis.TWO), PieceColor.WHITE)
                .isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("검정색 진영의 폰이 처음 이동하는 경우 두칸을 전진할 수 있다.")
    @Test
    void movePiece_ifPieceIsPawnMoveForwardTwiceOnFirstMoveInBlackTeam() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.SEVEN), Position.of(XAxis.A, YAxis.FIVE),
                PieceColor.BLACK).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("검정색 진영의 폰이 처음 이동하는 경우가 아니라면 한칸을 전진할 수 있다.")
    @Test
    void movePiece_ifPieceIsPawnMoveForwardOnceInBlackTeam() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.SEVEN), Position.of(XAxis.A, YAxis.SIX), PieceColor.BLACK);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.SIX), Position.of(XAxis.A, YAxis.FIVE),
                PieceColor.BLACK).isMoveSuccess();

        // then
        assertThat(actual).isTrue();
    }

    @DisplayName("검정색 진영의 폰이 처음 이동하는 경우가 아니라면 두 칸을 전진할 수 없다.")
    @Test
    void movePiece_ifPieceIsPawnMoveForwardTwiceWhenNotInitialInBlackTeam() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.SEVEN), Position.of(XAxis.A, YAxis.SIX), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.SIX), Position.of(XAxis.A, YAxis.FOUR),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }

    @DisplayName("검정색 진영의 폰은 위방향으로 이동할 수 없다.")
    @Test
    void executeCommand_returnsFalseIfBlackPawnTryToMoveUp() {
        // given
        Board board = Board.createInitializedBoard();

        // when
        board.executeCommand(Position.of(XAxis.A, YAxis.SEVEN), Position.of(XAxis.A, YAxis.SIX), PieceColor.WHITE);
        boolean actual = board.executeCommand(Position.of(XAxis.A, YAxis.SIX), Position.of(XAxis.A, YAxis.SEVEN),
                PieceColor.WHITE).isMoveSuccess();

        // then
        assertThat(actual).isFalse();
    }
}
