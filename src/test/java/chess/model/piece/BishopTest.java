package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import org.junit.jupiter.api.Test;

public class BishopTest {

    @Test
    void createBishop() {
        Bishop bishop = new Bishop(Color.BLACK, Square.of(File.D, Rank.EIGHT));
        assertThat(bishop).isInstanceOf(Bishop.class);
    }

    @Test
    void movable() {
        Bishop bishop = new Bishop(Color.BLACK, Square.of(File.D, Rank.FOUR));
        Empty diagLeftUpSquare = new Empty(Square.of(File.C, Rank.FIVE));
        Empty diagRightUpSquare = new Empty(Square.of(File.F, Rank.SIX));
        Empty diagLeftDownSquare = new Empty(Square.of(File.B, Rank.TWO));
        Empty diagRightDownSquare = new Empty(Square.of(File.G, Rank.ONE));
        assertAll(
                () -> assertThat(bishop.movable(diagLeftUpSquare)).isTrue(),
                () -> assertThat(bishop.movable(diagRightUpSquare)).isTrue(),
                () -> assertThat(bishop.movable(diagLeftDownSquare)).isTrue(),
                () -> assertThat(bishop.movable(diagRightDownSquare)).isTrue());
    }

    @Test
    void cannotMovable() {
        Bishop bishop = new Bishop(Color.BLACK, Square.of(File.B, Rank.EIGHT));
        Knight knight = new Knight(Color.WHITE, Square.of(File.C, Rank.SIX));
        assertThat(bishop.movable(knight)).isFalse();
    }

    @Test
    void cannotMovableToSameColor() {
        Bishop bishop = new Bishop(Color.BLACK, Square.of(File.B, Rank.EIGHT));
        Piece blackPiece = new Knight(Color.BLACK, Square.of(File.D, Rank.SIX));
        assertThat(bishop.movable(blackPiece)).isFalse();
    }
}
