package chess.model.piece;

import static chess.model.piece.Fixtures.B4;
import static chess.model.piece.Fixtures.B6;
import static chess.model.piece.Fixtures.D4;
import static chess.model.piece.Fixtures.D5;
import static chess.model.piece.Fixtures.F4;
import static chess.model.piece.Fixtures.F6;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.square.Square;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    void createBishop() {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @Test
    void movable() {
        Bishop bishop = new Bishop(Color.BLACK);
        Square b6 = B6;
        Square d4 = D4;
        assertThat(bishop.movable(d4, b6)).isTrue();
//        assertAll(
//                () -> assertThat(bishop.movable(D4, B6)).isTrue(),
//                () -> assertThat(bishop.movable(D4, B4)).isTrue(),
//                () -> assertThat(bishop.movable(D4, F6)).isTrue(),
//                () -> assertThat(bishop.movable(D4, F4)).isTrue());
    }

    @Test
    void cannotMovable() {
        Bishop bishop = new Bishop(Color.BLACK);
        assertThat(bishop.movable(D4, D5)).isFalse();
    }
//
//    @Test
//    void cannotMovableToSameColor() {
//        Bishop bishop = new Bishop(Color.BLACK);
//        Piece blackPiece = new Knight(Color.BLACK);
//        assertThat(bishop.movable(blackPiece)).isFalse();
//    }
}
