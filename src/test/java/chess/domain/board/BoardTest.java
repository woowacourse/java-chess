package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.exception.NoSuchPermittedChessPieceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @DisplayName("자기말 외에는 움직일 수 없다.")
    @Test
    void move() {
        Board board = new Board(Arrays.asList(Piece.createPawn(Color.BLACK, 0, 0)));

        assertThatThrownBy(() -> {
            board.movePiece(Color.WHITE, new Position(0, 0), new Position(1, 0));
        }).isExactlyInstanceOf(NoSuchPermittedChessPieceException.class);
    }
}
