package chess.domain.board;

import static chess.domain.board.File.A;
import static chess.domain.board.Rank.ONE;
import static chess.domain.board.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    @DisplayName("기물이 없는 위치에서 기물을 찾으려하면 예외를 발생 시킨다.")
    void findPieceInPositionException() {
        //given
        final Board board = new Board();
        final Position invalidPosition = Position.of(A, Rank.FOUR);
        //when, then
        assertThatThrownBy(() -> board.findPieceInPosition(invalidPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 없습니다.");
    }

    @Test
    @DisplayName("같은 팀 기물이 있는 위치로 이동하려고 하면 예외를 발생시킨다.")
    void moveExceptionBySameTeamPosition() {
        //given
        final Board board = new Board();
        final Position sourcePosition = Position.of(A, ONE);
        final Position targetPosition = Position.of(A, TWO);
        //when, then
        assertThatThrownBy(() -> board.movePiece(sourcePosition, targetPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동하려는 위치에 같은 팀 기물이 있습니다.");
    }
}
