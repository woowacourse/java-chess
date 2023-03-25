package chess.piece;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.view.SymbolMatcher;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmptyTest {

    @Test
    void Empty은_자신의_심볼을_반환한다() {
        Piece empty = new Empty(Team.WHITE, Coordinate.createCoordinate("1", "a"));
        assertThat(empty.symbol()).isEqualTo(SymbolMatcher.EMPTY);
    }
}
