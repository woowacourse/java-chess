package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    void createRook() {
        Rook rook = new Rook(Color.BLACK, new Square(File.A, Rank.EIGHT));
        assertThat(rook).isInstanceOf(Rook.class);
    }

    @Test
    void movable() {
        Rook rook = new Rook(Color.BLACK, new Square(File.D, Rank.FOUR));
        Piece south = new Empty(new Square(File.D, Rank.ONE));
        Piece east = new Empty(new Square(File.G, Rank.FOUR));
        Piece west = new Knight(Color.WHITE, new Square(File.A, Rank.FOUR));
        Piece north = new Empty(new Square(File.D, Rank.EIGHT));

        assertAll(
                () -> assertThat(rook.movable(south)).isTrue(),
                () -> assertThat(rook.movable(east)).isTrue(),
                () -> assertThat(rook.movable(west)).isTrue(),
                () -> assertThat(rook.movable(north)).isTrue());
    }

    @Test
    void cannotMovable() {
        Rook rook = new Rook(Color.BLACK, new Square(File.D, Rank.FOUR));
        Piece a1 = new Empty(new Square(File.A, Rank.ONE));
        Piece a8 = new Empty(new Square(File.A, Rank.EIGHT));
        Piece h8 = new Knight(Color.WHITE, new Square(File.H, Rank.EIGHT));
        Piece h1 = new Empty(new Square(File.H, Rank.ONE));

        assertAll(
                () -> assertThat(rook.movable(a1)).isFalse(),
                () -> assertThat(rook.movable(a8)).isFalse(),
                () -> assertThat(rook.movable(h8)).isFalse(),
                () -> assertThat(rook.movable(h1)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        Rook rook = new Rook(Color.BLACK, new Square(File.D, Rank.FOUR));
        Piece allyPawn = new Pawn(Color.BLACK, new Square(File.D, Rank.ONE));
        assertThat(rook.movable(allyPawn)).isFalse();
    }
}
