package chess.piece;

import chess.piece.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PawnTest {
    @Test
    void Pawn은_자신의_심볼을_반환한다() {
        Piece pawn = new Pawn(Team.WHITE, new Coordinate(1, 'a'));
        assertThat(pawn.symbol()).isEqualTo('p');
    }
}
