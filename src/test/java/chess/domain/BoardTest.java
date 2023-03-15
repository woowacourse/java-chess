package chess.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

public class BoardTest {

    private final Board board = BoardFactory.generateBoard();

    @Test
    void 흰색_폰을_찾을_수_있다() {
        assertThat(board.getPiece(7, 1)).isInstanceOf(Pawn.class);
    }

    @Test
    void 검정색_폰을_찾을_수_있다() {
        assertThat(board.getPiece(2, 1)).isInstanceOf(Pawn.class);
    }

    @Test
    void 흰색_룩을_찾을_수_있다() {
        assertThat(board.getPiece(8, 1)).isInstanceOf(Rook.class);
    }

    @Test
    void 검정색_룩을_찾을_수_있다() {
        assertThat(board.getPiece(1, 1)).isInstanceOf(Rook.class);
    }

    @Test
    void 흰색_나이트를_찾을_수_있다() {
        assertThat(board.getPiece(8, 2)).isInstanceOf(Knight.class);
    }

    @Test
    void 검정색_나이트를_찾을_수_있다() {
        assertThat(board.getPiece(1, 2)).isInstanceOf(Knight.class);
    }

    @Test
    void 흰색_비숍을_찾을_수_있다() {
        assertThat(board.getPiece(8, 3)).isInstanceOf(Bishop.class);
    }

    @Test
    void 검정색_비숍을_찾을_수_있다() {
        assertThat(board.getPiece(1, 3)).isInstanceOf(Bishop.class);
    }

    @Test
    void 흰색_퀸을_찾을_수_있다() {
        assertThat(board.getPiece(8, 4)).isInstanceOf(Queen.class);
    }

    @Test
    void 검정색_퀸을_찾을_수_있다() {
        assertThat(board.getPiece(1, 4)).isInstanceOf(Queen.class);
    }

    @Test
    void 흰색_킹을_찾을_수_있다() {
        assertThat(board.getPiece(8, 5)).isInstanceOf(King.class);
    }

    @Test
    void 검정색_킹을_찾을_수_있다() {
        assertThat(board.getPiece(1, 5)).isInstanceOf(King.class);
    }
}
