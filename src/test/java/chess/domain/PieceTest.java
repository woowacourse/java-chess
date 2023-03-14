package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    @DisplayName("피스는 역할과 진영을 갖는다.")
    @Test
    void Should_Create_When_Piece1() {
        final Piece piece = new Piece(Role.KING, Camp.BLACK);

        assertThat(piece).isEqualTo(new Piece(Role.KING, Camp.BLACK));
    }

    @DisplayName("피스는 빈 역할과 진영을 갖는다.")
    @Test
    void Should_Create_When_Piece2() {
        final Piece piece = new Piece();

        assertThat(piece).isEqualTo(new Piece(Role.EMPTY, Camp.EMPTY));
    }
}
