package chess.piece;

import static chess.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RookTest {

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"d4,d8", "d4,h4", "d4,d1", "d4,a4"})
    @DisplayName("룩은 상하좌우로 움직인다.")
    void canRookMove(final String from, final String to) {
        final Piece piece = new Rook(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isTrue();
    }

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"a1,h8", "d4,e5", "d4,h3", "d4,c3", "d4,a3"})
    @DisplayName("룩의 이동경로 외에는 움직일 수 없다.")
    void invalidRookMove(final String from, final String to) {
        final Piece piece = new Rook(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isFalse();
    }
}
