package chess.piece;

import chess.piece.King;
import chess.piece.Piece;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {
    @Test
    void King은_자신의_심볼을_반환한다() {
        Piece king = new King(Team.WHITE, 1, 'a');
        assertThat(king.symbol()).isEqualTo('k');
    }
}
