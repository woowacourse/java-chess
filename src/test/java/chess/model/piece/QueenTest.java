package chess.model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Color;
import chess.model.File;
import chess.model.Rank;
import chess.model.Square;
import chess.piece.Color;
import chess.piece.Empty;
import chess.piece.Knight;
import chess.piece.Piece;
import chess.piece.Queen;
import org.junit.jupiter.api.Test;

public class QueenTest {

    @Test
    void createQueen() {
        Queen queen = new Queen(Color.BLACK);
        assertThat(queen).isInstanceOf(Queen.class);
    }

    @Test
    void movable() {
        Queen queen = new Queen(Color.BLACK);
        Empty linearSquare = new Empty();
        Empty diagSquare = new Empty();
        assertAll(
                () -> assertThat(queen.movable(linearSquare)).isTrue(),
                () -> assertThat(queen.movable(diagSquare)).isTrue());
    }

    @Test
    void cannotMovable() {
        Queen queen = new Queen(Color.BLACK);
        Knight knight = new Knight(Color.WHITE);
        assertThat(queen.movable(knight)).isFalse();
    }

    @Test
    void cannotMovableToSameColor() {
        Queen queen = new Queen(Color.BLACK);
        Piece linearBlackPiece = new Knight(Color.BLACK);
        assertThat(queen.movable(linearBlackPiece)).isFalse();
    }
}
