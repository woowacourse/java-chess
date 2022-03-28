package chess.piece;

import static chess.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.game.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class QueenTest {

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"d4,d8", "d4,h8", "d4,h4", "d4,g1", "d4,d1", "d4,a1", "d4,a4", "d4,a7"})
    @DisplayName("퀸이 8방향으로 여러 칸 움직일 수 있는지 확인한다.")
    void canQueenMove(final String from, final String to) {
        final Piece piece = new Queen(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isTrue();
    }

    @ParameterizedTest(name = "from : {0}, to : {1}")
    @CsvSource(value = {"d4,b8", "d4,g8", "d4,g3", "d4,b3"})
    @DisplayName("퀸의 이동경로가 아닌지 확인한다.")
    void invalidQueenMove(final String from, final String to) {
        final Piece piece = new Queen(WHITE);

        assertThat(piece.canMove(Position.of(from), Position.of(to))).isFalse();
    }
}
