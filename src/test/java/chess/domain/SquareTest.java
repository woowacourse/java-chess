package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SquareTest {
    @DisplayName("스퀘어는 랭크와 파일과 피스를 갖는다.")
    @Test
    void Should_Create_When_Square1() {
        final Square square = new Square(Rank.A, File.ONE, new Piece(Role.KING, Camp.BLACK));

        assertThat(square).isEqualTo(new Square(Rank.A, File.ONE, new Piece(Role.KING, Camp.BLACK)));
    }


    @DisplayName("스퀘어는 랭크와 파일을 갖는다.")
    @Test
    void Should_Create_When_Square2() {
        final Square square = new Square(Rank.A, File.ONE);

        assertThat(square).isEqualTo(new Square(Rank.A, File.ONE));
    }
}
