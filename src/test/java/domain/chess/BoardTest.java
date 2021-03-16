package domain.chess;

import domain.chess.piece.Pawn;
import domain.chess.piece.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    void name() {
        Board board = new Board(PieceFactory.createPieces());
        board.move(Position.Of(1, 0), Position.Of(3, 0));
        assertThat(board.getPiece(Position.Of(3, 0))).isInstanceOf(Pawn.class);
    }
}