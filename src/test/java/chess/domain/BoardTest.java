package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class BoardTest {

    @Test
    @DisplayName("해당 위치에 기물이 존재하는지 확인한다.")
    void existPiece() {
        Position position = Position.from("b3");
        Board board = new Board(Map.of(position, new Rook(PieceColor.BLACK)));

        assertThat(board.existPiece(position)).isTrue();
    }
}
