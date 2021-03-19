package chess.domain.board;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static chess.domain.piece.Fixture.board;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    @DisplayName("포지션을 받아 해당 위치의 Square를 리턴한다.")
    @Test
    void findByPositionTest() {
        Board board = BoardFactory.createBoard();

        assertThat(board.findByPosition(Position.of("a1"))).isInstanceOf(Square.class);
    }

    @DisplayName("이동할 때 해당 위치에 말이 없으면 예외")
    @Test
    void throwExceptionWhenSquareHasNotPiece() {
        assertThatThrownBy(() -> board.move(Position.of("a3"), Position.of("b3")))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("해당 위치엔 말이 없습니다.");
    }
}
