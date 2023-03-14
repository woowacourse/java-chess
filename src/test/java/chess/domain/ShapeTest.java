package chess.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ShapeTest {

    @Test
    @DisplayName("white 플레이어가 가진 기물의 이름을 입력받아 해당 기물의 모양을 반환한다.")
    void findByWhiteName() {
        // given, when, then
        Assertions.assertAll(
                () -> assertThat(Shape.findByWhiteName('p'))
                        .isEqualTo(Shape.PAWN),

                () -> assertThat(Shape.findByWhiteName('k'))
                        .isEqualTo(Shape.KING),

                () -> assertThat(Shape.findByWhiteName('q'))
                        .isEqualTo(Shape.QUEEN),

                () -> assertThat(Shape.findByWhiteName('r'))
                        .isEqualTo(Shape.ROOK),

                () -> assertThat(Shape.findByWhiteName('b'))
                        .isEqualTo(Shape.BISHOP),

                () -> assertThat(Shape.findByWhiteName('n'))
                        .isEqualTo(Shape.KNIGHT)
        );

    }
}
