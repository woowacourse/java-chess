package point;

import model.GameBoard;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MovingTest {

    @Test
    @DisplayName("기물이 없는 위치가 주어졌을 때 예외가 발생한다.")
    void blankPosition() {
        //given
        GameBoard gameBoard = new GameBoard();
        gameBoard.setting();

        Moving moving = new Moving(new Position(Row.FOURTH, Column.FIFTH), new Position(Row.FIFTH, Column.FIFTH));

        //when & then
        Assertions.assertThatThrownBy(() -> moving.move(gameBoard))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
