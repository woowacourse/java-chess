package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @Test
    void createKnight() {
        Knight knight = new Knight(Color.BLACK, Square.of(File.E, Rank.EIGHT));
        assertThat(knight).isInstanceOf(Knight.class);
    }

    @Test
    void movable() {
        Knight knight = new Knight(Color.BLACK, Square.of(File.E, Rank.FOUR));
        Empty NNE = new Empty(Square.of(File.F, Rank.SIX));
        Empty NNW = new Empty(Square.of(File.D, Rank.SIX));
        Empty SSE = new Empty(Square.of(File.F, Rank.TWO));
        Empty SSW = new Empty(Square.of(File.D, Rank.TWO));
        Piece EEN = new Empty(Square.of(File.G, Rank.FIVE));
        Piece EES = new Empty(Square.of(File.G, Rank.THREE));
        Piece WWN = new Knight(Color.WHITE, Square.of(File.C, Rank.FIVE));
        Piece WWS = new Empty(Square.of(File.C, Rank.THREE));
        assertAll(
                () -> assertThat(knight.movable(NNE)).isTrue(),
                () -> assertThat(knight.movable(NNW)).isTrue(),
                () -> assertThat(knight.movable(SSE)).isTrue(),
                () -> assertThat(knight.movable(SSW)).isTrue(),
                () -> assertThat(knight.movable(EEN)).isTrue(),
                () -> assertThat(knight.movable(EES)).isTrue(),
                () -> assertThat(knight.movable(WWN)).isTrue(),
                () -> assertThat(knight.movable(WWS)).isTrue());
    }

    @Test
    void cannotMovable() {
        Knight knight = new Knight(Color.BLACK, Square.of(File.D, Rank.FOUR));
        Piece d6 = new Empty(Square.of(File.D, Rank.SIX));
        Piece a4 = new Empty(Square.of(File.A, Rank.FOUR));
        Piece f6 = new Knight(Color.WHITE, Square.of(File.F, Rank.SIX));
        Piece a1 = new Empty(Square.of(File.A, Rank.ONE));

        assertAll(
                () -> assertThat(knight.movable(d6)).isFalse(),
                () -> assertThat(knight.movable(a4)).isFalse(),
                () -> assertThat(knight.movable(f6)).isFalse(),
                () -> assertThat(knight.movable(a1)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        Knight knight = new Knight(Color.BLACK, Square.of(File.D, Rank.FOUR));
        Piece linearBlackPiece = new Knight(Color.BLACK, Square.of(File.F, Rank.FIVE));
        assertThat(knight.movable(linearBlackPiece)).isFalse();
    }
}
