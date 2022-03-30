package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Color;
import chess.piece.Empty;
import chess.piece.Knight;
import chess.piece.Piece;
import org.junit.jupiter.api.Test;

public class KnightTest {

    @Test
    void createKnight() {
        Knight knight = new Knight(Color.BLACK);
        assertThat(knight).isInstanceOf(Knight.class);
    }

    @Test
    void movable() {
        Knight knight = new Knight(Color.BLACK);
        Empty NNE = new Empty();
        Empty NNW = new Empty();
        Empty SSE = new Empty();
        Empty SSW = new Empty();
        Piece EEN = new Empty();
        Piece EES = new Empty();
        Piece WWN = new Knight(Color.WHITE);
        Piece WWS = new Empty();
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
        Knight knight = new Knight(Color.BLACK);
        Piece d6 = new Empty();
        Piece a4 = new Empty();
        Piece f6 = new Knight(Color.WHITE);
        Piece a1 = new Empty();

        assertAll(
                () -> assertThat(knight.movable(d6)).isFalse(),
                () -> assertThat(knight.movable(a4)).isFalse(),
                () -> assertThat(knight.movable(f6)).isFalse(),
                () -> assertThat(knight.movable(a1)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        Knight knight = new Knight(Color.BLACK);
        Piece linearBlackPiece = new Knight(Color.BLACK);
        assertThat(knight.movable(linearBlackPiece)).isFalse();
    }
}
