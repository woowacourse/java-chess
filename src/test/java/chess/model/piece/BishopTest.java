package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.piece.Bishop;
import chess.piece.Color;
import chess.piece.Empty;
import chess.piece.Knight;
import chess.piece.Piece;
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
        Empty diagLeftUpSquare = new Empty();
        Empty diagRightUpSquare = new Empty();
        Empty diagLeftDownSquare = new Empty();
        Empty diagRightDownSquare = new Empty();
        assertAll(
                () -> assertThat(bishop.movable(diagLeftUpSquare)).isTrue(),
                () -> assertThat(bishop.movable(diagRightUpSquare)).isTrue(),
                () -> assertThat(bishop.movable(diagLeftDownSquare)).isTrue(),
                () -> assertThat(bishop.movable(diagRightDownSquare)).isTrue());
    }

    @Test
    void cannotMovable() {
        Bishop bishop = new Bishop(Color.BLACK);
        Knight knight = new Knight(Color.WHITE);
        assertThat(bishop.movable(knight)).isFalse();
    }

    @Test
    void cannotMovableToSameColor() {
        Bishop bishop = new Bishop(Color.BLACK);
        Piece blackPiece = new Knight(Color.BLACK);
        assertThat(bishop.movable(blackPiece)).isFalse();
    }
}
