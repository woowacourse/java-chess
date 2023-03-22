package chess.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.Shape.BISHOP;
import static chess.domain.Shape.KING;
import static chess.domain.Shape.PAWN;
import static chess.domain.Shape.QUEEN;
import static chess.domain.Shape.ROOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

final class PiecesTest {

    Pieces whitePieces;
    Pieces blackPieces;

    @BeforeEach
    void setUp() {
        this.whitePieces = Pieces.createWhitePieces();
        blackPieces = Pieces.createBlackPieces(whitePieces);
    }

    @Test
    @DisplayName("체스 판 기물들이 올바른 개수로 생성되어야 한다.")
    void createWhitePieces() {
        // given, when
        Pieces pieces = Pieces.createWhitePieces();

        // then
        assertAll(
                () -> {
                    long count = pieces.getShapeCount(PAWN);
                    assertThat(count).isEqualTo(8);
                },

                () -> {
                    long count = pieces.getShapeCount(BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getShapeCount(ROOK);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getShapeCount(BISHOP);
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getShapeCount(KING);
                    assertThat(count).isEqualTo(1);
                },

                () -> {
                    long count = pieces.getShapeCount(QUEEN);
                    assertThat(count).isEqualTo(1);
                }
        );
    }

}
