package chess.piece;

import static chess.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"d4 a7", "d4 h8", "d4 g1", "d4 a1"})
    @DisplayName("비숍이 대각선으로 움직일 수 있는지 확인한다.")
    void canBishopMove(final String command) {
        final Piece piece = new Bishop(WHITE);

        assertThat(piece.canMove(MoveCommand.of(command))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"d4 d8", "d4, h4", "d4 d1", "d4 a4", "d4 h7", "d4 h1", "d4 a2", "d4 a8"})
    @DisplayName("비숍의 이동경로가 아닌지 확인한다.")
    void invalidBishopMove(final String command) {
        final Piece piece = new Bishop(WHITE);

        assertThat(piece.canMove(MoveCommand.of(command))).isFalse();
    }
}
