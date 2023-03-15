package chess.domain.pieces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlaceTest {

    @Test
    @DisplayName("빈공간은 움직이면 에러를 발생시킨다.")
    void throws_exceptions_when_place_move() {
        // given
        Place place = new Place(new Name("."));

        // when & then
        Assertions.assertThatThrownBy(
                () -> place.canMove("c3", "c4")
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
