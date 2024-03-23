package domain.board;

import domain.piece.info.File;
import domain.piece.info.Position;
import domain.piece.info.Rank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
    @Test
    @DisplayName("기물을 이동 불가능한 자리로 움직이면 예외가 발생한다")
    void move2() {
        final Board board = new Board(BoardInitiator.init());
        final Position source = new Position(File.of(0), Rank.of(0));
        final Position next = new Position(File.of(0), Rank.of(1));

        Assertions.assertThatThrownBy(() -> board.move(source, next)).isInstanceOf(IllegalArgumentException.class);
    }
}
