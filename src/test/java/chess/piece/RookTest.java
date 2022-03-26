package chess.piece;

import static chess.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class RookTest {

    @ParameterizedTest
    @ValueSource(strings = {"d4 d8", "d4 h4", "d4 d1", "d4 a4"})
    @DisplayName("룩이 상하좌우로 움직일 수 있는지 확인한다.")
    void canRookMove(final String command) {
        final Piece piece = new Rook(WHITE);

        assertThat(piece.canMove(MoveCommand.of(command))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a1 h8", "d4 e5", "d4 h3", "d4 c3", "d4 a3"})
    @DisplayName("룩의 이동경로가 아닌지 확인한다.")
    void invalidRookMove(final String command) {
        final Piece piece = new Rook(WHITE);

        assertThat(piece.canMove(MoveCommand.of(command))).isFalse();
    }
}
