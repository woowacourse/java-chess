package domain.chess;

import domain.chess.piece.Pawn;
import domain.chess.piece.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @DisplayName("보드 생성 시 모든 말들이 세팅된다.")
    @Test
    void board_test() {
        Board board = new Board(PieceFactory.createPieces());
        board.move(Position.Of(1, 0), Position.Of(3, 0));
        assertThat(board.getPiece(Position.Of(3, 0))).isInstanceOf(Pawn.class);
    }

    @DisplayName("빈 보드를 만든다.")
    @Test
    void make_empty_board_test() {
        Board board = new Board();
        assertThat(board.getBoard()[0][0] == null).isTrue();
    }
}