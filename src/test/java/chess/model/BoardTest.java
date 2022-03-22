package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void createTest() {
        Board board = new Board();
        assertThat(board).isInstanceOf(Board.class);
    }

    @Test
    void getTest() {
        Board board = new Board();
        Piece a1Piece = board.get(new Square(File.A, Rank.ONE));
        Piece h8Piece = board.get(new Square(File.H, Rank.EIGHT));

        assertAll(
                () -> assertThat(a1Piece.getName()).isEqualTo(Name.ROOK),
                () -> assertThat(h8Piece.getName()).isEqualTo(Name.ROOK)
        );
    }
}
