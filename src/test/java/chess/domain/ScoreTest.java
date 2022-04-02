package chess.domain;

import chess.domain.piece.Team;
import chess.domain.postion.File;
import chess.domain.postion.Position;
import chess.domain.postion.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @DisplayName("초기 백팀 점수 테스트")
    @Test
    void whiteScore() {
        Score score = Score.from(BoardFixture.setup());

        assertThat(score.whiteScore()).isEqualTo(38.0);
    }

    @DisplayName("초기 흑팀 점수 테스트")
    @Test
    void blackScore() {
        Score score = Score.from(BoardFixture.setup());

        assertThat(score.blackScore()).isEqualTo(38.0);
    }

    @DisplayName("폰이 같은 rank에 있을 때 점수 테스트")
    @Test
    void pawnScoreHalf_black() {
        // 흑팀 폰이 백팀 폰을 먹은 경우
        Board board = BoardFixture.setup()
                .movePiece(new Position(File.A, Rank.TWO), new Position(File.A, Rank.FOUR), Team.WHITE)
                .movePiece(new Position(File.H, Rank.SEVEN), new Position(File.H, Rank.FIVE), Team.BLACK)
                .movePiece(new Position(File.A, Rank.FOUR), new Position(File.A, Rank.FIVE), Team.WHITE)
                .movePiece(new Position(File.H, Rank.FIVE), new Position(File.H, Rank.FOUR), Team.BLACK)
                .movePiece(new Position(File.A, Rank.FIVE), new Position(File.A, Rank.SIX), Team.WHITE)
                .movePiece(new Position(File.B, Rank.SEVEN), new Position(File.A, Rank.SIX), Team.BLACK);

        Score score = Score.from(board);

        assertThat(score.blackScore()).isEqualTo(37.0);
    }

    @DisplayName("폰이 같은 rank에 있을 때 점수 테스트")
    @Test
    void pawnScoreHalf_white() {
        Board board = BoardFixture.setup()
                .movePiece(new Position(File.A, Rank.TWO), new Position(File.A, Rank.FOUR), Team.WHITE)
                .movePiece(new Position(File.H, Rank.SEVEN), new Position(File.H, Rank.FIVE), Team.BLACK)
                .movePiece(new Position(File.A, Rank.FOUR), new Position(File.A, Rank.FIVE), Team.WHITE)
                .movePiece(new Position(File.H, Rank.FIVE), new Position(File.H, Rank.FOUR), Team.BLACK)
                .movePiece(new Position(File.A, Rank.FIVE), new Position(File.A, Rank.SIX), Team.WHITE)
                .movePiece(new Position(File.B, Rank.SEVEN), new Position(File.A, Rank.SIX), Team.BLACK);

        Score score = Score.from(board);

        assertThat(score.whiteScore()).isEqualTo(37.0);
    }
}
