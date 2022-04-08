package chess.board;

import chess.board.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @Test
    @DisplayName("position끼리 수평이면 true를 반환한다.")
    void isHorizontal() {
        Position source = Position.of('a', '1');
        Position target = Position.of('d', '1');

        assertThat(source.isHorizontal(target)).isTrue();
    }

    @Test
    @DisplayName("position끼리 수직이면 true를 반환한다.")
    void isVertical() {
        Position source = Position.of('a', '1');
        Position target = Position.of('a', '3');

        assertThat(source.isVertical(target)).isTrue();
    }

    @Test
    @DisplayName("position끼리 대각선이면 true를 반환한다.")
    void isDiagonal() {
        Position source = Position.of('a', '1');
        Position target = Position.of('f', '6');

        assertThat(source.isDiagonal(target)).isTrue();
    }

    @Test
    @DisplayName("체스판을 벗어나는 position이 주어지면 예외가 발생한다.")
    void outOfRangePositionTest() {
        assertThatThrownBy(() -> Position.of('a', '9'))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}
