package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QueenTest {
    @DisplayName("성공 : 퀸은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Queen testQueen = new Queen(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position diagonalPosition = Position.of(7, 7);

        assertThat(testQueen.canMove(startPosition, diagonalPosition, board)).isTrue();
    }

    @DisplayName("성공 : 퀸은 직선관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsStraight_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Queen testQueen = new Queen(Team.WHITE);

        Position startPosition = Position.of(0, 0);
        Position verticalDestination = Position.of(7, 0);
        Position horizontalDestination = Position.of(0, 7);

        assertAll(
                () -> assertThat(testQueen.canMove(startPosition, verticalDestination, board)).isTrue(),
                () -> assertThat(testQueen.canMove(startPosition, horizontalDestination, board)).isTrue()
        );
    }

    @DisplayName("실패 : 퀸은 대각선 혹은 직선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonalAndIsNotStraight_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Queen testQueen = new Queen(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position wrongDestination1 = Position.of(7, 6);
        Position wrongDestination2 = Position.of(1, 2);

        assertAll(
                () -> assertThat(testQueen.canMove(startPosition, wrongDestination1, board)).isFalse(),
                () -> assertThat(testQueen.canMove(startPosition, wrongDestination2, board)).isFalse()
        );
    }
}
