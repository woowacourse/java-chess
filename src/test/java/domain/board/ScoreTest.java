package domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.Score;
import domain.board.fixture.RookBoard;
import domain.piece.team.Team;

public class ScoreTest {
	@DisplayName("처음 상태의 보드판 점수 확인")
	@Test
	void getScore_InitialBoard_IsBlack38AndWhit38() {
		Map<Team, Double> allScore = Score.calculateScore(BoardFactory.create().getPieces(), Team.values());
		assertThat(allScore.get(Team.WHITE)).isEqualTo(38.0);
		assertThat(allScore.get(Team.BLACK)).isEqualTo(38.0);
	}

	@DisplayName("White팀 Rook 2개, Black팀 Rook 1개가 생존했을 때 점수 확인")
	@Test
	void getScore_Black1RookAndWhite2Rook_Black5White10() {
		Map<Team, Double> allScore = Score.calculateScore(new RookBoard().create().getPieces(), Team.values());
		assertThat(allScore.get(Team.WHITE)).isEqualTo(10);
		assertThat(allScore.get(Team.BLACK)).isEqualTo(5);
	}
}
