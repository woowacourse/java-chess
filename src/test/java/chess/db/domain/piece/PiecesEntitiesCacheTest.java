package chess.db.domain.piece;

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

class PiecesEntitiesCacheTest {
    private List<PieceEntity> expectedPieces;

    @BeforeEach
    void setUp() {
        expectedPieces = Arrays.asList(
            new PawnEntity(BLACK),
            new RookEntity(BLACK),
            new KnightEntity(BLACK),
            new BishopEntity(BLACK),
            new QueenEntity(BLACK),
            new KingEntity(BLACK),
            new PawnEntity(WHITE),
            new RookEntity(WHITE),
            new KnightEntity(WHITE),
            new BishopEntity(WHITE),
            new QueenEntity(WHITE),
            new KingEntity(WHITE)
        );
    }

    @DisplayName("기물 캐싱")
    @Test
    void piecesCaching() {
        List<PieceEntity> cachedPieces = new ArrayList<>();
        for (TeamColor teamColor : TeamColor.values()) {
            assertPiecesCachingWithColor(teamColor, cachedPieces);
        }
        assertThat(cachedPieces).containsExactlyInAnyOrderElementsOf(expectedPieces);
    }

    private void assertPiecesCachingWithColor(TeamColor teamColor, List<PieceEntity> cachedPieces) {
        for (PieceType pieceType : PieceType.values()) {
            PieceEntity cachedPiece = PieceEntity.of(pieceType, teamColor);
            cachedPieces.add(cachedPiece);
        }
    }
}