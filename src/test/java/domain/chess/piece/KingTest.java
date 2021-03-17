package domain.chess.piece;

import domain.chess.Board;
import domain.chess.PieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("King은 상하좌우 모든 대각선 방향으로 1칸 이동할 수 있다.")
    @Test
    void king_move() {
        Board board = new Board(PieceFactory.createPieces());
        King king = King.Of("K", Position.Of(4,4), true);
        assertThat(king.canMove(board.getBoard(), Position.Of(4,5))).isEqualTo(true); //우
        assertThat(king.canMove(board.getBoard(), Position.Of(5,4))).isEqualTo(true); //하
        assertThat(king.canMove(board.getBoard(), Position.Of(4,3))).isEqualTo(true); //좌
        assertThat(king.canMove(board.getBoard(), Position.Of(3,4))).isEqualTo(true); //상

        assertThat(king.canMove(board.getBoard(), Position.Of(3,5))).isEqualTo(true); //우상
        assertThat(king.canMove(board.getBoard(), Position.Of(5,5))).isEqualTo(true); //우하
        assertThat(king.canMove(board.getBoard(), Position.Of(3,3))).isEqualTo(true); //좌상
        assertThat(king.canMove(board.getBoard(), Position.Of(5,3))).isEqualTo(true); //좌하
    }
}