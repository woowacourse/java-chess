package chess.domain.board;

import chess.domain.piece.Rook;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @DisplayName("포지션을 받아 해당 위치의 말을 리턴한다.")
    @Test
    void findByPositionTest() {
        Board board = BoardFactory.createBoard();

        assertThat(board.findByPosition(Position.of("a1"))).isInstanceOf(Rook.class);
    }
}
