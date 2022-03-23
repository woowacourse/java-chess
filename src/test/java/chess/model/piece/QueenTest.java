package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    void createQueen() {
        Queen queen = new Queen(Color.BLACK, new Square(File.D, Rank.EIGHT));
        assertThat(queen).isInstanceOf(Queen.class);
    }

    @Test
    void movable() {
        Queen queen = new Queen(Color.BLACK, new Square(File.D, Rank.EIGHT));
        Empty linearSquare = new Empty(new Square(File.D, Rank.FIVE));
        Empty diagSquare = new Empty(new Square(File.A, Rank.FIVE));
        assertAll(
                () -> assertThat(queen.movable(linearSquare)).isTrue(),
                () -> assertThat(queen.movable(diagSquare)).isTrue());
    }

    @Test
    void cannotMovable() {
        Queen queen = new Queen(Color.BLACK, new Square(File.D, Rank.EIGHT));
        Knight knight = new Knight(Color.WHITE, new Square(File.C, Rank.SIX));
        assertThat(queen.movable(knight)).isFalse();
    }

    @Test
    void cannotMovableToSameColor() {
        Queen queen = new Queen(Color.BLACK, new Square(File.D, Rank.EIGHT));
        Piece linearBlackPiece = new Knight(Color.BLACK, new Square(File.D, Rank.FIVE));
        assertThat(queen.movable(linearBlackPiece)).isFalse();
    }
}
