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
                    long count = pieces.getPieces().stream()
                            .filter(piece -> piece.getShape() == Shape.PAWN)
                            .count();
                    assertThat(count).isEqualTo(8);
                },

                () -> {
                    long count = pieces.getPieces().stream()
                            .filter(piece -> piece.getShape() == Shape.BISHOP)
                            .count();
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getPieces().stream()
                            .filter(piece -> piece.getShape() == Shape.ROOK)
                            .count();
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getPieces().stream()
                            .filter(piece -> piece.getShape() == Shape.KNIGHT)
                            .count();
                    assertThat(count).isEqualTo(2);
                },

                () -> {
                    long count = pieces.getPieces().stream()
                            .filter(piece -> piece.getShape() == Shape.KING)
                            .count();
                    assertThat(count).isEqualTo(1);
                },

                () -> {
                    long count = pieces.getPieces().stream()
                            .filter(piece -> piece.getShape() == Shape.QUEEN)
                            .count();
                    assertThat(count).isEqualTo(1);
                }
        );
    }

}
