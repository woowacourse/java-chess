package chess.domain.piece.nonsliding;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.direction.Direction.*;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class KingTest {
    private Piece king;
    
    @BeforeEach
    void setUp() {
        king = new King(Team.WHITE);
    }
    
    @Test
    void King은_자신의_pieceType을_반환한다() {
        assertThat(king.pieceType()).isEqualTo(PieceType.KING);
    }
    
    @Test
    void King은_자신의_Directions를_반환한다() {
        assertThat(king.directions())
                .contains(EAST, WEST, SOUTH, NORTH, NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }
}
