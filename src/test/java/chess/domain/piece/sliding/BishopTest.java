package chess.domain.piece.sliding;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.domain.direction.Direction.*;
import static chess.domain.direction.Direction.NORTH_NORTH;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BishopTest {
    private Piece bishop;
    
    @BeforeEach
    void setUp() {
        bishop = new Bishop(Team.WHITE);
    }
    
    @Test
    void King은_자신의_pieceType을_반환한다() {
        assertThat(bishop.pieceType()).isEqualTo(PieceType.BISHOP);
    }
    
    @Test
    void King은_자신의_Directions를_반환한다() {
        assertThat(bishop.directions())
                .contains(NORTH_EAST, SOUTH_EAST, SOUTH_WEST, NORTH_WEST);
    }
}
