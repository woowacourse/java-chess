package chess.model.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import chess.model.board.Board;
import chess.model.board.ChessInitializer;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.piece.Rook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board(new ChessInitializer());
    }

    @Test
    void createTest() {
        assertThat(board).isInstanceOf(Board.class);
    }

    @Test
    void findPiece() {
        Piece a2 = board.findPieceBySquare(new Square(File.A, Rank.TWO));
        assertThat(a2).isEqualTo(new Pawn(Color.WHITE, new Square(File.A, Rank.TWO)));
    }

    @Test
    void getTest() {
        Piece a1Piece = board.findPieceBySquare(new Square(File.A, Rank.ONE));
        Piece h8Piece = board.findPieceBySquare(new Square(File.H, Rank.EIGHT));

        assertAll(
                () -> assertThat(a1Piece.getClass()).isEqualTo(Rook.class),
                () -> assertThat(h8Piece.getClass()).isEqualTo(Rook.class)
        );
    }

    @Test
    void move() {
        board.move(new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE));
        Piece a3Piece = board.findPieceBySquare(new Square(File.A, Rank.THREE));
        board.move(Square.of("e2"), Square.of("e3"));
        Piece e3 = board.findPieceBySquare(Square.of("e3"));
        assertThat(a3Piece).isEqualTo(new Pawn(Color.WHITE, new Square(File.A, Rank.THREE)));
        assertThat(e3).isEqualTo(new Pawn(Color.WHITE, Square.of("e3")));
    }

    @Test
    void point() {
        double score = board.calculatePoint(Color.WHITE);
        assertThat(score).isEqualTo(38);
    }

    @Test
    void pointWithPawn() {
        board.move(Square.of("b8"), Square.of("c6"));
        board.move(Square.of("c6"), Square.of("b4"));
        board.move(Square.of("b4"), Square.of("d3"));
        board.move(Square.of("c2"), Square.of("d3"));
        double score = board.calculatePoint(Color.WHITE);
        double blackScore = board.calculatePoint(Color.BLACK);
        assertThat(score).isEqualTo(37);
        assertThat(blackScore).isEqualTo(35.5);
    }
}
