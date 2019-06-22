package chess.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static chess.domain.PieceType.*;
import static org.assertj.core.api.Assertions.assertThat;

class ChessScoreCountTest {

    @Test
    void calculate() {

        ChessScoreCount scoreCount = new ChessScoreCount(new HashSet<>(Arrays.asList(
            ROOK_BLACK,
            BISHOP_BLACK,
            QUEEN_BLACK,
            QUEEN_WHITE,
            PAWN_WHITE,
            KNIGHT_WHITE
        )));

        assertThat(scoreCount.getScore(Team.BLACK)).isEqualTo(17);
        assertThat(scoreCount.getScore(Team.WHITE)).isEqualTo(12.5);
    }
}