package chess.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.piece.Color;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import org.junit.jupiter.api.Test;

public class ConsoleBoardTest {

    @Test
    void createTest() {
        ConsoleBoard consoleBoard = new ConsoleBoard();

        assertThat(consoleBoard).isInstanceOf(ConsoleBoard.class);
    }

    @Test
    void findPiece() {
        ConsoleBoard consoleBoard = new ConsoleBoard();

        Piece a2 = consoleBoard.get(Square.of(File.A, Rank.TWO));

        assertThat(a2.isPawn()).isTrue();
    }

    @Test
    void getTest() {
        ConsoleBoard consoleBoard = new ConsoleBoard();

        Piece a1Piece = consoleBoard.get(Square.of(File.A, Rank.ONE));
        Piece h8Piece = consoleBoard.get(Square.of(File.H, Rank.EIGHT));

        assertAll(
                () -> assertThat(a1Piece.name()).isEqualTo("r"),
                () -> assertThat(h8Piece.name()).isEqualTo("r")
        );
    }

    @Test
    void move() {
        ConsoleBoard consoleBoard = new ConsoleBoard();

        consoleBoard.move("a2", "a3");
        Piece a3Piece = consoleBoard.get(Square.of(File.A, Rank.THREE));

        assertThat(a3Piece.isPawn()).isTrue();
    }

    @Test
    void point() {
        ConsoleBoard consoleBoard = new ConsoleBoard();

        ScoreResult scoreResult = consoleBoard.calculateScore();

        assertThat(scoreResult.get(Color.WHITE)).isEqualTo(38);
    }

    @Test
    void pointWithPawn() {
        ConsoleBoard consoleBoard = new ConsoleBoard();

        consoleBoard.move("b8", ("c6"));
        consoleBoard.move(("c6"), ("b4"));
        consoleBoard.move(("b4"), ("d3"));
        consoleBoard.move(("c2"), ("d3"));
        ScoreResult score = consoleBoard.calculateScore();

        assertThat(score.get(Color.WHITE)).isEqualTo(37);
        assertThat(score.get(Color.BLACK)).isEqualTo(35.5);
    }
}
