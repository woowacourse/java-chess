package chess.beforedb.domain.piece;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {
    private List<Piece> expectedPieces;

    @BeforeEach
    void setUp() {
        expectedPieces = Arrays.asList(
            new Pawn(BLACK),
            new Rook(BLACK),
            new Knight(BLACK),
            new Bishop(BLACK),
            new Queen(BLACK),
            new King(BLACK),
            new Pawn(WHITE),
            new Rook(WHITE),
            new Knight(WHITE),
            new Bishop(WHITE),
            new Queen(WHITE),
            new King(WHITE)
        );
    }

    @DisplayName("기물 캐싱")
    @Test
    void piecesCaching() {
        List<Piece> cachedPieces = new ArrayList<>();
        for (TeamColor teamColor : TeamColor.values()) {
            assertPiecesCachingWithColor(teamColor, cachedPieces);
        }
        assertThat(cachedPieces).containsExactlyInAnyOrderElementsOf(expectedPieces);
    }

    private void assertPiecesCachingWithColor(TeamColor teamColor, List<Piece> cachedPieces) {
        for (PieceType pieceType : PieceType.values()) {
            Piece cachedPiece = Piece.of(pieceType, teamColor);
            cachedPieces.add(cachedPiece);
        }
    }
}