package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    void createKing() {
        King king = new King(Color.BLACK, Square.of(File.E, Rank.EIGHT));
        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    void movable() {
        King king = new King(Color.BLACK, Square.of(File.E, Rank.FOUR));
        Empty ne = new Empty(Square.of(File.F, Rank.FIVE));
        Empty se = new Empty(Square.of(File.F, Rank.THREE));
        Empty sw = new Empty(Square.of(File.D, Rank.THREE));
        Empty nw = new Empty(Square.of(File.D, Rank.FIVE));
        Piece south = new Empty(Square.of(File.D, Rank.THREE));
        Piece east = new Empty(Square.of(File.F, Rank.FOUR));
        Piece west = new Knight(Color.WHITE, Square.of(File.D, Rank.FOUR));
        Piece north = new Empty(Square.of(File.E, Rank.FIVE));
        assertAll(
                () -> assertThat(king.movable(ne)).isTrue(),
                () -> assertThat(king.movable(se)).isTrue(),
                () -> assertThat(king.movable(sw)).isTrue(),
                () -> assertThat(king.movable(nw)).isTrue(),
                () -> assertThat(king.movable(south)).isTrue(),
                () -> assertThat(king.movable(east)).isTrue(),
                () -> assertThat(king.movable(west)).isTrue(),
                () -> assertThat(king.movable(north)).isTrue());
    }

    @Test
    void cannotMovable() {
        King king = new King(Color.BLACK, Square.of(File.D, Rank.FOUR));
        Piece d6 = new Empty(Square.of(File.D, Rank.SIX));
        Piece a4 = new Empty(Square.of(File.A, Rank.FOUR));
        Piece f6 = new Knight(Color.WHITE, Square.of(File.F, Rank.SIX));
        Piece a1 = new Empty(Square.of(File.A, Rank.ONE));

        assertAll(
                () -> assertThat(king.movable(d6)).isFalse(),
                () -> assertThat(king.movable(a4)).isFalse(),
                () -> assertThat(king.movable(f6)).isFalse(),
                () -> assertThat(king.movable(a1)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        King king = new King(Color.BLACK, Square.of(File.D, Rank.FOUR));
        Piece linearBlackPiece = new Knight(Color.BLACK, Square.of(File.D, Rank.FIVE));
        assertThat(king.movable(linearBlackPiece)).isFalse();
    }
}
