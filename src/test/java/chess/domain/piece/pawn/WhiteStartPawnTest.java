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
class WhiteStartPawnTest {
    private Piece whiteStartPawn;
    
    @BeforeEach
    void setUp() {
        whiteStartPawn = new WhiteStartPawn(Team.WHITE);
    }
    
    @Test
    void King은_자신의_pieceType을_반환한다() {
        assertThat(whiteStartPawn.pieceType()).isEqualTo(PieceType.WHITE_START_PAWN);
    }
    
    @Test
    void King은_자신의_Directions를_반환한다() {
        assertThat(whiteStartPawn.directions())
                .contains(NORTH, NORTH_EAST, NORTH_WEST, NORTH_NORTH);
    }
}
