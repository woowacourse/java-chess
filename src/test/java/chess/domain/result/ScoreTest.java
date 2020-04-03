package chess.domain.result;

import chess.domain.piece.GamePiece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static chess.domain.piece.ChessPiece.*;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreTest {

    private Map<GamePiece, Integer> piecesCount;

    @BeforeEach
    void setUp() {
        piecesCount = new HashMap<>();
        piecesCount.put(BLACK_KNIGHT.getGamePiece(), 2);
        piecesCount.put(BLACK_BISHOP.getGamePiece(), 1);
        piecesCount.put(BLACK_PAWN.getGamePiece(), 2);
    }

    @Test
    void create() {
        int sameFilePawnCount = 2;
        Score score = Score.of(piecesCount, sameFilePawnCount);

        assertThat(score.getScore()).isEqualTo(9);
    }
}