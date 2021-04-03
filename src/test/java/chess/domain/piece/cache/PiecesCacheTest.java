package chess.domain.piece.cache;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PiecesCacheTest {
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