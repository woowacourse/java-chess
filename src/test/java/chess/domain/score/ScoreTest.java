package chess.domain.score;

import chess.domain.piece.GamePiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.ChessPiece.*;
import static chess.domain.player.Player.BLACK;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    private Map<GamePiece, Integer> piecesCount;

    @BeforeEach
    void setUp() {
        piecesCount = new HashMap<>();
        piecesCount.put(GamePiece.of(KNIGHT, BLACK), 2);
        piecesCount.put(GamePiece.of(BISHOP, BLACK), 1);
        piecesCount.put(GamePiece.of(PAWN, BLACK), 2);
    }

    @Test
    void create() {
        int sameFilePawnCount = 2;
        Score score = Score.of(piecesCount, sameFilePawnCount);

        assertThat(score.getScore()).isEqualTo(9);
    }
}