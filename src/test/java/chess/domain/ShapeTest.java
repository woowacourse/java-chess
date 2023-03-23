package chess.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ShapeTest {

    @Test
    @DisplayName("white 플레이어가 가진 기물의 이름을 입력받아 해당 기물의 모양을 반환한다.")
    void findShapeByWhiteName() {
        // given, when, then
        Assertions.assertAll(
                () -> assertThat(Shape.findShapeByWhiteName('p'))
                        .isEqualTo(Shape.PAWN),

                () -> assertThat(Shape.findShapeByWhiteName('k'))
                        .isEqualTo(Shape.KING),

                () -> assertThat(Shape.findShapeByWhiteName('q'))
                        .isEqualTo(Shape.QUEEN),

                () -> assertThat(Shape.findShapeByWhiteName('r'))
                        .isEqualTo(Shape.ROOK),

                () -> assertThat(Shape.findShapeByWhiteName('b'))
                        .isEqualTo(Shape.BISHOP),

                () -> assertThat(Shape.findShapeByWhiteName('n'))
                        .isEqualTo(Shape.KNIGHT)
        );
    }

}
