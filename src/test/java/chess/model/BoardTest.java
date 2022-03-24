package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @Test
    void createTest() {
        Board board = new Board();
        assertThat(board).isInstanceOf(Board.class);
    }

    @Test
    void findPiece() {
        Board board = new Board();
        Piece a2 = board.findPieceBySquare(new Square(File.A, Rank.TWO));
        assertThat(a2).isEqualTo(new Pawn(Color.WHITE, new Square(File.A, Rank.TWO)));
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

    @Test
    void move() {
        Board board = new Board();
        board.move(new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE));
        Piece a3Piece = board.findPieceBySquare(new Square(File.A, Rank.THREE));
        assertThat(a3Piece).isEqualTo(new Pawn(Color.WHITE, new Square(File.A, Rank.THREE)));
    }
}
