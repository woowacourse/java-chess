package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RookTest {
    private final Piece white = new Rook(false);
    private final Piece black = new Rook(true);
    private final Position source = new Position("e", "4");

    @ParameterizedTest
    @DisplayName("이동 가능한지 판단하는 기능")
    @ValueSource(strings = {"d,4", "e,5", "f,4", "e,3"})
    void canMove(final String input) {
        final String[] inputs = input.split(",");
        assertThat(white.canMove(source, new Position(inputs[0], inputs[1]), new Queen(true))).isTrue();
        assertThat(white.canMove(source, new Position(inputs[0], inputs[1]), new Blank())).isTrue();
        assertThat(black.canMove(source, new Position(inputs[0], inputs[1]), new Queen(false))).isTrue();
    }
}