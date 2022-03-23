package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("보드를 생성할 수 있다.")
    void createBoard() {
        Board board = new Board();
        assertThat(board.findByPosition(new Position(File.A,Rank.ONE)))
                .isInstanceOf(EmptyPiece.class);
    }
}
