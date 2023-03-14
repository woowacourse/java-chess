package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RowTest {
    @DisplayName("Row는 Square 리스트를 갖는다.")
    @Test
    void Should_Create_When_Row() {
        final Piece piece = new Piece(Role.KING, Camp.BLACK);
        final Square square = new Square(Rank.A, File.ONE, piece);
        final List<Square> squares = List.of(square);

        final Row row = new Row(squares);

        assertThat(row).isEqualTo(new Row(squares));
    }
}
