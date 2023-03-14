package chess.domain.pieces;

import chess.domain.board.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlaceTest {

    @Test
    @DisplayName("빈공간은 움직이면 에러를 발생시킨다.")
    void throws_exceptions_when_place_move() {
        // given
        Place place = new Place(new Position("b2"));

        // when & then
        Assertions.assertThatThrownBy(
                () -> place.move("c3")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
