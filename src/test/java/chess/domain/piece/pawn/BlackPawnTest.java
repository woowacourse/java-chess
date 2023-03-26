package chess.domain.piece.pawn;

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
class BlackPawnTest {
    private Piece blackPawn;
    
    @BeforeEach
    void setUp() {
        blackPawn = new BlackPawn(Team.BLACK);
    }
    
    @Test
    void King은_자신의_pieceType을_반환한다() {
        assertThat(blackPawn.pieceType()).isEqualTo(PieceType.BLACK_PAWN);
    }
    
    @Test
    void King은_자신의_Directions를_반환한다() {
        assertThat(blackPawn.directions())
                .contains(SOUTH, SOUTH_EAST, SOUTH_WEST);
    }
}