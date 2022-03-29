package chess.piece;

import static chess.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BishopTest {

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"d4,a7", "d4,h8", "d4,g1", "d4,a1"})
    @DisplayName("비숍은 대각선으로 움직인다.")
    void canBishopMove(final String from, final String to) {
        final Piece piece = new Bishop(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isTrue();
    }

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"d4,d8", "d4,h4", "d4,d1", "d4,a4", "d4,h7", "d4,h1", "d4,a2", "d4,a8"})
    @DisplayName("비숍은 대각선 외에 다른 경로로 움직일 수 없다.")
    void invalidBishopMove(final String from, final String to) {
        final Piece piece = new Bishop(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isFalse();
    }
}
