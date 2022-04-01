package chess.model.piece;

import static chess.model.piece.Fixtures.C2;
import static chess.model.piece.Fixtures.C3;
import static chess.model.piece.Fixtures.D3;
import static chess.model.piece.Fixtures.E1;
import static chess.model.piece.Fixtures.E2;
import static chess.model.piece.Fixtures.E4;
import static chess.model.piece.Fixtures.E5;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.model.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PawnTest {

    private Board board;

    @BeforeEach
    public void setUp() {
        board = new Board();
    }

    @Test
    void createPawn() {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn).isInstanceOf(Pawn.class);
    }


    @Test
    void firstSquareMovable() {
        Pawn pawn = new Pawn(Color.WHITE);
        assertAll(
                () -> assertThat(pawn.movable(board, E2, E4)).isTrue(),
                () -> assertThat(pawn.movable(board, C2, C3)).isTrue());
    }

    @Test
    void firstSquareCannotMovable() {
        Pawn pawn = new Pawn(Color.WHITE);
        assertAll(
                () -> assertThat(pawn.movable(board, E2, E5)).isFalse(),
                () -> assertThat(pawn.movable(board, E2, D3)).isFalse());
    }

    @Test
    void WhitePawnCannotMoveInLastColumn() {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.movable(board, E2, E1)).isFalse();
    }
}
