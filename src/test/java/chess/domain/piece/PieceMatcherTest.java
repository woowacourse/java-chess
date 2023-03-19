package chess.domain.piece;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static chess.fixture.CoordinateFixture.A_ONE;
import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceMatcherTest {
    @Test
    void King_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('k', Team.WHITE, A_ONE);
        assertThat(piece).isEqualTo(new King(Team.WHITE, A_ONE));
    }
    
    @Test
    void Queen_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('q', Team.WHITE, A_ONE);
        assertThat(piece).isEqualTo(new Queen(Team.WHITE, A_ONE));
    }
    
    @Test
    void Rook_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('r', Team.WHITE, A_ONE);
        assertThat(piece).isEqualTo(new Rook(Team.WHITE, A_ONE));
    }
    
    @Test
    void Knight_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('n', Team.WHITE, A_ONE);
        assertThat(piece).isEqualTo(new Knight(Team.WHITE, A_ONE));
    }
    
    @Test
    void Bishop_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('b', Team.WHITE, A_ONE);
        assertThat(piece).isEqualTo(new Bishop(Team.WHITE, A_ONE));
    }
    
    @Test
    void Pawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('p', Team.WHITE, A_ONE);
        assertThat(piece).isEqualTo(new Pawn(Team.WHITE, A_ONE));
    }
    
    @Test
    void Empty_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('e', Team.WHITE, A_ONE);
        assertThat(piece).isEqualTo(new Empty(Team.WHITE, A_ONE));
    }
}
