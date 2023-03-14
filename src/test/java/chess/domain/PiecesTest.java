package chess.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PiecesTest {

    @Test
    @DisplayName("체스 판 기물들이 올바른 개수로 생성되어야 한다.")
    void createWhitePieces() {
        // given, when
        Pieces pieces = Pieces.createWhitePieces();

        // then
        Assertions.assertAll(
                () -> {
                    long count = pieces.getShapeCount(Shape.PAWN);
                    assertThat(count).isEqualTo(8);
                },

                () -> {
                    long count = pieces.getShapeCount(Shape.BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getShapeCount(Shape.ROOK);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getShapeCount(Shape.BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getShapeCount(Shape.KING);
                    assertThat(count).isEqualTo(1);
                },

                () -> {
                    long count = pieces.getShapeCount(Shape.QUEEN);
                    assertThat(count).isEqualTo(1);
                }
        );
    }

}
