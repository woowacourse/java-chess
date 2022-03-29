package chess.piece;

import static chess.piece.Color.BLACK;
import static chess.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.game.MoveCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PawnTest {

    @ParameterizedTest
    @ValueSource(strings = {"a2 a3", "a2 a4", "h2 h3", "h2 h4", "a7 a8", "h7 h8"})
    @DisplayName("화이트 폰이 전진할 수 있는지 확인한다.")
    void canMoveForwardWhite(final String command) {
        final Piece piece = new Pawn(WHITE);

        assertThat(piece.canMove(MoveCommand.of(command))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a2 b4", "h2 h5", "a3 a2", "a2 b2", "h8 h7", "a3, a5"})
    @DisplayName("화이트 폰의 이동경로가 아닌지 확인한다.")
    void invalidMoveWhite(final String command) {
        final Piece piece = new Pawn(WHITE);

        assertThat(piece.canMove(MoveCommand.of(command))).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a7 a5", "a7 a6", "h7 h5", "h7 h6", "a2 a1", "h2 h1"})
    @DisplayName("블랙 폰이 전진할 수 있는지 확인한다.")
    void canMoveForwardBlack(final String command) {
        final Piece piece = new Pawn(BLACK);

        assertThat(piece.canMove(MoveCommand.of(command))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"a7 b5", "h7 h3", "a2 a3", "a2 b2", "h7 h8", "a5, a3"})
    @DisplayName("블랙 폰의 이동경로가 아닌지 확인한다.")
    void invalidMoveBlack(final String command) {
        final Piece piece = new Pawn(BLACK);

        assertThat(piece.canMove(MoveCommand.of(command))).isFalse();
    }
}
