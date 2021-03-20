package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class QueenTest {

    private final Piece white = new Queen(Team.WHITE);
    private final Piece black = new Queen(Team.BLACK);
    private final Position source = new Position("e", "4");

    @ParameterizedTest
    @DisplayName("이동 가능한지 판단하는 기능")
    @ValueSource(strings = {"d,4", "e,5", "f,4", "e,3", "d,5", "f,5", "d,3", "f,3"})
    void canMove(final String input) {
        final String[] inputs = input.split(",");
        assertThat(white.canMove(source, new Position(inputs[0], inputs[1]), new Pawn(Team.BLACK)))
            .isTrue();
        assertThat(white.canMove(source, new Position(inputs[0], inputs[1]), Blank.getInstance()))
            .isTrue();
        assertThat(black.canMove(source, new Position(inputs[0], inputs[1]), new Pawn(Team.WHITE)))
            .isTrue();
    }
}