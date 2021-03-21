package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class KnightTest {

    private final Piece white = new Knight(Team.WHITE);
    private final Piece black = new Knight(Team.BLACK);
    private final Position source = new Position("e", "4");

    @ParameterizedTest
    @DisplayName("이동 가능한지 판단하는 기능")
    @ValueSource(strings = {"d,6", "d,2", "f,6", "f,2", "c,5", "c,3", "g,5", "g,3"})
    void canMove(final String input) {
        final String[] inputs = input.split(",");
        final Position position = new Position(inputs[0], inputs[1]);
        assertThat(white.canMove(source, position, new Queen(Team.BLACK)))
            .isTrue();
        assertThat(white.canMove(source, position, Blank.getInstance()))
            .isTrue();
        assertThat(black.canMove(source, position, new Queen(Team.WHITE)))
            .isTrue();
    }
}