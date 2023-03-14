package piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import piece.coordinate.Coordinate;

import static org.assertj.core.api.Assertions.*;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceMatcherTest {
    @Test
    void King_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('k', Team.WHITE, new Coordinate(1, 'a'));
        assertThat(piece).isEqualTo(new King(Team.WHITE, 1, 'a'));
    }
    
    @Test
    void Queen_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('q', Team.WHITE, new Coordinate(1, 'a'));
        assertThat(piece).isEqualTo(new Queen(Team.WHITE, 1, 'a'));
    }
    
    @Test
    void Rook_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('r', Team.WHITE, new Coordinate(1, 'a'));
        assertThat(piece).isEqualTo(new Rook(Team.WHITE, 1, 'a'));
    }
    
    @Test
    void Knight_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('n', Team.WHITE, new Coordinate(1, 'a'));
        assertThat(piece).isEqualTo(new Knight(Team.WHITE, 1, 'a'));
    }
    
    @Test
    void Bishop_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('b', Team.WHITE, new Coordinate(1, 'a'));
        assertThat(piece).isEqualTo(new Bishop(Team.WHITE, 1, 'a'));
    }
    
    @Test
    void Pawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('p', Team.WHITE, new Coordinate(1, 'a'));
        assertThat(piece).isEqualTo(new Pawn(Team.WHITE, 1, 'a'));
    }
    
    @Test
    void Empty_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of('e', Team.WHITE, new Coordinate(1, 'a'));
        assertThat(piece).isEqualTo(new Empty(Team.WHITE, 1, 'a'));
    }
}
