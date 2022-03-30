package chess.model.piece;

import static chess.model.piece.Fixtures.B2;
import static chess.model.piece.Fixtures.B3;
import static chess.model.piece.Fixtures.B5;
import static chess.model.piece.Fixtures.C2;
import static chess.model.piece.Fixtures.C6;
import static chess.model.piece.Fixtures.D4;
import static chess.model.piece.Fixtures.D5;
import static chess.model.piece.Fixtures.E2;
import static chess.model.piece.Fixtures.E6;
import static chess.model.piece.Fixtures.F2;
import static chess.model.piece.Fixtures.F3;
import static chess.model.piece.Fixtures.F5;
import static chess.model.piece.Fixtures.F6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        assertAll(
                () -> assertThat(knight.movable(D4, E6)).isTrue(),
                () -> assertThat(knight.movable(D4, F5)).isTrue(),
                () -> assertThat(knight.movable(D4, F3)).isTrue(),
                () -> assertThat(knight.movable(D4, E2)).isTrue(),
                () -> assertThat(knight.movable(D4, C2)).isTrue(),
                () -> assertThat(knight.movable(D4, B3)).isTrue(),
                () -> assertThat(knight.movable(D4, B5)).isTrue(),
                () -> assertThat(knight.movable(D4, C6)).isTrue(),
                () -> assertThat(knight.movable(C2, D4)).isTrue());

    }

    @Test
    void cannotMovable() {
        Knight knight = new Knight(Color.BLACK);

        assertAll(
                () -> assertThat(knight.movable(D4, D5)).isFalse(),
                () -> assertThat(knight.movable(D4, F2)).isFalse(),
                () -> assertThat(knight.movable(D4, B2)).isFalse(),
                () -> assertThat(knight.movable(D4, F6)).isFalse());
    }

//    @Test
//    void cannotMovableToSameColor() {
//        Knight knight = new Knight(Color.BLACK);
//        Piece linearBlackPiece = new Knight(Color.BLACK);
//        assertThat(knight.movable(linearBlackPiece)).isFalse();
//    }
}
