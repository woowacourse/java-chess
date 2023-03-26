package chess.domain.winningstatus;

import chess.domain.piece.Team;
import chess.domain.piece.state.King;
import chess.domain.piece.state.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class WinningStatusByScoreTest {
    private static final King KING_WHITE = new King(Team.WHITE);
    private static final Pawn PAWN_WHITE = new Pawn(Team.WHITE);
    private static final King KING_BLACK = new King(Team.BLACK);
    private static final Pawn PAWN_BLACK = new Pawn(Team.BLACK);
    private static WinningStatusByScore winningStatusByScore;

    @BeforeEach
    void setUp() {
        Map<Team, Score> scores = new EnumMap<>(Team.class);
        scores.put(Team.WHITE, new Score(List.of(KING_WHITE, PAWN_WHITE), 0));
        scores.put(Team.BLACK, new Score(List.of(KING_BLACK, PAWN_BLACK, PAWN_BLACK), 0));

        winningStatusByScore = new WinningStatusByScore(scores);
    }

    @Test
    void 승자가_결정되었는지_물어보면_false를_반환한다() {
        assertThat(winningStatusByScore.isWinnerDetermined()).isFalse();
    }

    @Test
    void 점수를_반환_한다() {
        Map<Team, Score> scores = winningStatusByScore.getScores();

        assertThat(scores.get(Team.WHITE).getScore()).isEqualTo(1);
        assertThat(scores.get(Team.BLACK).getScore()).isEqualTo(2);
    }

    @Test
    void 점수가_더_높은_팀을_승자로_반환한다() {
        assertThat(winningStatusByScore.getWinner()).isEqualTo(Team.BLACK);
    }
}