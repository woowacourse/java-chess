package chess.result;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.team.Team;

class ScoreTest {

	private static Stream<Arguments> providerScoreInfo() {
		return Stream.of(
			Arguments.of(
				new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.WHITE), false);
					put(new Pawn(Team.WHITE), false);
					put(new Pawn(Team.WHITE), true);
				}}, new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.BLACK), false);
					put(new Pawn(Team.BLACK), false);
				}}
				, 1
			),
			Arguments.of(
				new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.WHITE), false);
					put(new Pawn(Team.WHITE), false);
				}}, new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.BLACK), false);
					put(new Pawn(Team.BLACK), false);
				}}
				, 0
			),
			Arguments.of(
				new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.WHITE), false);
					put(new Pawn(Team.WHITE), false);
				}}, new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.BLACK), false);
					put(new Pawn(Team.BLACK), false);
					put(new Pawn(Team.WHITE), true);
				}}
				, -1
			)
		);
	}

	@DisplayName("피스, 세로줄의 폰의 정보들을 받아 점수를 계산")
	@Test
	void calculateScore() {
		Score score = new Score(new HashMap<Piece, Boolean>() {{
			put(new Pawn(Team.WHITE), false);
			put(new Pawn(Team.WHITE), false);
			put(new Pawn(Team.WHITE), true);
		}});
		assertThat(score.getAmount()).isEqualTo(2.5);
	}

	@DisplayName("두 스코어를 비교하여 compare value를 생성")
	@ParameterizedTest
	@MethodSource("providerScoreInfo")
	void compare(HashMap<Piece, Boolean> scoreInfo, HashMap<Piece, Boolean> otherInfo, int expect) {
		Score score = new Score(scoreInfo);
		Score other = new Score(otherInfo);
		assertThat(score.compare(other)).isEqualTo(expect);
	}
}