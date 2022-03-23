package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.piece.Piece;
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
        Piece a1Piece = board.findPieceBySquare(new Square(File.A, Rank.ONE));
        Piece h8Piece = board.findPieceBySquare(new Square(File.H, Rank.EIGHT));

        assertAll(
                () -> assertThat(a1Piece.getLetter()).isEqualTo("r"),
                () -> assertThat(h8Piece.getLetter()).isEqualTo("r")
        );
    }
}
