package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Color;
import chess.piece.Empty;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Rook;
import org.junit.jupiter.api.Test;

public class RookTest {

    @Test
    void createRook() {
        Rook rook = new Rook(Color.BLACK);
        assertThat(rook).isInstanceOf(Rook.class);
    }

    @Test
    void movable() {
        Rook rook = new Rook(Color.BLACK);
        Piece south = new Empty();
        Piece east = new Empty();
        Piece west = new Knight(Color.WHITE);
        Piece north = new Empty();

        assertAll(
                () -> assertThat(rook.movable(south)).isTrue(),
                () -> assertThat(rook.movable(east)).isTrue(),
                () -> assertThat(rook.movable(west)).isTrue(),
                () -> assertThat(rook.movable(north)).isTrue());
    }

    @Test
    void cannotMovable() {
        Rook rook = new Rook(Color.BLACK);
        Piece a1 = new Empty();
        Piece a8 = new Empty();
        Piece h8 = new Knight(Color.WHITE);
        Piece h1 = new Empty();

        assertAll(
                () -> assertThat(rook.movable(a1)).isFalse(),
                () -> assertThat(rook.movable(a8)).isFalse(),
                () -> assertThat(rook.movable(h8)).isFalse(),
                () -> assertThat(rook.movable(h1)).isFalse());
    }

    @Test
    void cannotMovableToSameColor() {
        Rook rook = new Rook(Color.BLACK);
        Piece allyPawn = new Pawn(Color.BLACK);
        assertThat(rook.movable(allyPawn)).isFalse();
    }
}
