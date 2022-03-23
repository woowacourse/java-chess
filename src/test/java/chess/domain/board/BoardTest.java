package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("기물이 없는 위치에서 기물을 찾으려하면 예외를 발생 시킨다.")
    void findPieceInPositionException() {
        //given
        final Board board = new Board();
        final Position invalidPosition = Position.of(File.A, Rank.FOUR);
        //when, then
        assertThatThrownBy(() -> board.findPieceInPosition(invalidPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }
}