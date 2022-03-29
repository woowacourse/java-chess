package chess.piece;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"a2,a3", "a2,a4", "h2,h3", "h2,h4", "a7,a8", "h7,h8"})
    @DisplayName("화이트 폰은 전진만 가능하다.")
    void canMoveForwardWhite(final String from, final String to) {
        final Piece piece = new Pawn(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isTrue();
    }

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"a2,b4", "h2,h5", "a3,a2", "a2,b2", "h8,h7", "a3,a5"})
    @DisplayName("화이트 폰은 전진외에 다른 경로로 이동할 수 없다.")
    void invalidMoveWhite(final String from, final String to) {
        final Piece piece = new Pawn(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isFalse();
    }

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"a7,a5", "a7,a6", "h7,h5", "h7,h6", "a2,a1", "h2,h1"})
    @DisplayName("블랙 폰이 전진만 가능하다.")
    void canMoveForwardBlack(final String from, final String to) {
        final Piece piece = new Pawn(BLACK);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isTrue();
    }

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"a7,b5", "h7,h3", "a2,a3", "a2,b2", "h7,h8", "a5,a3"})
    @DisplayName("블랙 폰의 전진외에 다른 경로로 이동할 수 없다.")
    void invalidMoveBlack(final String from, final String to) {
        final Piece piece = new Pawn(BLACK);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isFalse();
    }
}
