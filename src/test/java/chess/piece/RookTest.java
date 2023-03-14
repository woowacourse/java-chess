package chess.piece;

import chess.piece.Piece;
import chess.piece.Rook;
import chess.piece.Team;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RookTest {
    @Test
    void Rook은_자신의_심볼을_반환한다() {
        Piece rook = new Rook(Team.WHITE, 1, 'a');
        assertThat(rook.symbol()).isEqualTo('r');
    }
}
