package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class BishopTest {

    @DisplayName("성공 : 비숍은 대각선 관계에 있는 포지션으로 이동할 수 있다")
    @Test
    void should_CanMove_When_DestinationIsDiagonal_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position diagonalPosition = Position.of(7, 7);

        assertThat(testBishop.canMove(startPosition, diagonalPosition, board)).isTrue();
    }

    @DisplayName("실패 : 비숍은 대각선 관계에 있는 포지션이 아니면 이동할 수 없다")
    @Test
    void should_CanNotMove_When_DestinationIsNotDiagonal_From_StartPosition() {
        ChessBoard board = new ChessBoard(new HashMap<>());
        Bishop testBishop = new Bishop(Team.WHITE);
        Position startPosition = Position.of(0, 0);
        Position notDiagonalPosition = Position.of(7, 6);

        assertThat(testBishop.canMove(startPosition, notDiagonalPosition, board)).isFalse();
    }
}
