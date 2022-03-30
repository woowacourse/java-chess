package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Color;
import chess.piece.Empty;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Piece;
import org.junit.jupiter.api.Test;

public class KingTest {

    @Test
    void createKing() {
        King king = new King(Color.BLACK);
        assertThat(king).isInstanceOf(King.class);
    }

    @Test
    void movable() {
        King king = new King(Color.BLACK);
        Empty ne = new Empty();
        Empty se = new Empty();
        Empty sw = new Empty();
        Empty nw = new Empty();
        Piece south = new Empty();
        Piece east = new Empty();
        Piece west = new Knight(Color.WHITE);
        Piece north = new Empty();
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
        King king = new King(Color.BLACK);
        Piece d6 = new Empty();
        Piece a4 = new Empty();
        Piece f6 = new Knight(Color.WHITE);
        Piece a1 = new Empty();

        assertAll(
                () -> assertThat(king.movable(d6)).isFalse(),
                () -> assertThat(king.movable(a4)).isFalse(),
                () -> assertThat(king.movable(f6)).isFalse(),
                () -> assertThat(king.movable(a1)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        King king = new King(Color.BLACK);
        Piece linearBlackPiece = new Knight(Color.BLACK);
        assertThat(king.movable(linearBlackPiece)).isFalse();
    }
}
