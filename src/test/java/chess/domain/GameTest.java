package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GameTest {
    @DisplayName("Game은 Board를 갖는다.")
    @Test
    void Should_Create_When_Game() {
        final Piece piece = new Piece(Role.KING, Camp.BLACK);
        final Square square = new Square(Rank.A, File.ONE, piece);
        final List<Square> squares = List.of(square);
        final Row row = new Row(squares);
        final List<Row> rows = List.of(row);
        final Board board = new Board(rows);

        final Game game = new Game(board);

        assertThat(game).isEqualTo(new Game(board));
    }
}
