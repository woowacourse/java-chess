package chess.result;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
				new ArrayList<Piece>() {{
					add(Pawn.of(Team.WHITE));
					add(Pawn.of(Team.WHITE));
					add(Pawn.of(Team.WHITE));
				}}, new ArrayList<Piece>() {{
					add(Pawn.of(Team.BLACK));
					add(Pawn.of(Team.BLACK));
				}}
				, 1
			),
			Arguments.of(
				new ArrayList<Piece>() {{
					add(Pawn.of(Team.WHITE));
					add(Pawn.of(Team.WHITE));
				}}, new ArrayList<Piece>() {{
					add(Pawn.of(Team.BLACK));
					add(Pawn.of(Team.BLACK));
				}}
				, 0
			),
			Arguments.of(
				new ArrayList<Piece>() {{
					add(Pawn.of(Team.WHITE));
					add(Pawn.of(Team.WHITE));
				}}, new ArrayList<Piece>() {{
					add(Pawn.of(Team.BLACK));
					add(Pawn.of(Team.BLACK));
					add(Pawn.of(Team.BLACK));
				}}
				, -1
			)
		);
	}

	@DisplayName("피스, 세로줄의 폰의 정보들을 받아 점수를 계산")
	@Test
	void calculateScore() {
		Score score = new Score(Arrays.asList(Pawn.of(Team.WHITE)
			, Pawn.of(Team.WHITE), Pawn.of(Team.WHITE)),
			1);
		assertThat(score.getAmount()).isEqualTo(2.5);
	}

	@DisplayName("두 스코어를 비교하여 compare value를 생성")
	@ParameterizedTest
	@MethodSource("providerScoreInfo")
	void compare(List<Piece> pieces, List<Piece> otherPieces, int expect) {
		Score score = new Score(pieces, 0);
		Score other = new Score(otherPieces, 0);
		System.out.println(score.getAmount());
		System.out.println(other.getAmount());
		assertThat(score.compare(other)).isEqualTo(expect);
	}
}