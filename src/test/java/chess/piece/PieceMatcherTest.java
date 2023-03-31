package chess.piece;

import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceMatcher;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.view.SymbolMatcher;
import chess.domain.piece.Team;
import chess.domain.piece.coordinate.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PieceMatcherTest {

    private Coordinate coordinate;

    @BeforeEach
    void setUp() {
        coordinate = Coordinate.createCoordinate("1", "a");
    }

    @Test
    void King_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.KING, Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new King(Team.WHITE, coordinate));
    }

    @Test
    void Queen_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.QUEEN, Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Queen(Team.WHITE, coordinate));
    }

    @Test
    void Rook_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.ROOK, Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Rook(Team.WHITE, coordinate));
    }

    @Test
    void Knight_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.KNIGHT, Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Knight(Team.WHITE, coordinate));
    }

    @Test
    void Bishop_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.BISHOP, Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Bishop(Team.WHITE, coordinate));
    }

    @Test
    void Pawn_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.PAWN, Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Pawn(Team.WHITE, coordinate));
    }

    @Test
    void Empty_심볼에_맞는_Piece_구현체_반환() {
        Piece piece = PieceMatcher.of(SymbolMatcher.EMPTY, Team.WHITE, coordinate);
        assertThat(piece).isEqualTo(new Empty(Team.WHITE, coordinate));
    }
}
