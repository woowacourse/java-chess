package chess.domain.piece;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    private final Piece white = new Knight(false);
    private final Piece black = new Knight(true);
    private final Position source = new Position("e", "4");

    @ParameterizedTest
    @DisplayName("이동 가능한지 판단하는 기능")
    @ValueSource(strings = {"d,6", "d,1", "f,6", "f,1", "c,3", "c,2", "g,3", "g,2"})
    void canMove(final String input) {
        final String[] inputs = input.split(",");
        assertThat(white.canMove(source, new Position(inputs[0], inputs[1]), new King(true))).isTrue();
        assertThat(white.canMove(source, new Position(inputs[0], inputs[1]), new Blank())).isTrue();
        assertThat(black.canMove(source, new Position(inputs[0], inputs[1]), new King(false))).isTrue();
    }
}