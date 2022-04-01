package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Board;
import chess.model.piece.Color;
import chess.model.piece.Pawn;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import chess.model.util.ScoreResult;
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

        Piece a2 = board.get(Square.of(File.A, Rank.TWO));

        assertThat(a2.isPawn()).isTrue();
    }

    @Test
    void getTest() {
        Board board = new Board();

        Piece a1Piece = board.get(Square.of(File.A, Rank.ONE));
        Piece h8Piece = board.get(Square.of(File.H, Rank.EIGHT));

        assertAll(
                () -> assertThat(a1Piece.name()).isEqualTo("r"),
                () -> assertThat(h8Piece.name()).isEqualTo("r")
        );
    }

    @Test
    void move() {
        Board board = new Board();

        board.move("a2", "a3");
        Piece a3Piece = board.get(Square.of(File.A, Rank.THREE));

        assertThat(a3Piece.isPawn()).isTrue();
    }

    @Test
    void point() {
        Board board = new Board();

        ScoreResult scoreResult = board.calculateScore();

        assertThat(scoreResult.get(Color.WHITE)).isEqualTo(38);
    }

    @Test
    void pointWithPawn() {
        Board board = new Board();

        board.move("b8", ("c6"));
        board.move(("c6"), ("b4"));
        board.move(("b4"), ("d3"));
        board.move(("c2"), ("d3"));
        ScoreResult score = board.calculateScore();

        assertThat(score.get(Color.WHITE)).isEqualTo(37);
        assertThat(score.get(Color.BLACK)).isEqualTo(35.5);
    }
}
