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
class KnightTest {
    private Piece knight;
    
    @BeforeEach
    void setUp() {
        knight = new Knight(Team.WHITE);
    }
    
    @Test
    void King은_자신의_pieceType을_반환한다() {
        assertThat(knight.pieceType()).isEqualTo(PieceType.KNIGHT);
    }
    
    @Test
    void King은_자신의_Directions를_반환한다() {
        assertThat(knight.directions())
                .contains(EAST_EAST_NORTH, EAST_EAST_SOUTH, SOUTH_SOUTH_EAST, SOUTH_SOUTH_WEST,
                WEST_WEST_SOUTH, WEST_WEST_NORTH, NORTH_NORTH_WEST, NORTH_NORTH_EAST);
    }
}
