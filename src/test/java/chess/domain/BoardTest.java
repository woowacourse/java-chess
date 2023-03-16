package chess.domain;

import static chess.domain.File.A;
import static chess.domain.Rank.THREE;
import static chess.domain.Rank.TWO;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("체스판을 생성한다.")
    void createBoard() {
        assertDoesNotThrow(Board::new);
    }

    @Test
    @DisplayName("보드가 생성되면 32개의 Piece를 가진다.")
    void containsPieces() {
        Board board = new Board();
        assertThat(board.getPieces()).hasSize(32);
    }

    @Test
    void move() {
        Board board = new Board();
        Square src = Square.of(A, TWO);
        Square dst = Square.of(A, THREE);

        Piece expectedPiece = board.getPieces().get(src);
        board.move(src, dst);

        assertThat(board.getPieces().get(dst)).isEqualTo(expectedPiece);
    }

}
