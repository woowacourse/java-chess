package chess.result;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import chess.piece.Pawn;
import chess.piece.Piece;
import chess.team.Team;

class GameResultTest {

	private static Stream<Arguments> providerScore() {
		return Stream.of(
			Arguments.of(
				new Score(Team.WHITE, new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.WHITE), false);
				}}), new Score(Team.BLACK, new HashMap<>())
				, GameResult.WIN
			), Arguments.of(
				new Score(Team.WHITE, new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.WHITE), false);
				}}), new Score(Team.BLACK, new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.BLACK), false);
				}})
				, GameResult.DRAW
			), Arguments.of(
				new Score(Team.WHITE, new HashMap<>()),
				new Score(Team.WHITE, new HashMap<Piece, Boolean>() {{
					put(new Pawn(Team.WHITE), false);
				}})
				, GameResult.LOSE
			)
		);
	}

	@DisplayName("compare값을 통하여 승패정보를 제대로 가져오는지 확인")
	@ParameterizedTest
	@MethodSource("providerScore")
	void findResult(Score whiteTeam, Score blackTeam, GameResult gameResult) {
		GameResult actual = GameResult.findResult(whiteTeam, blackTeam);
		assertThat(actual).isEqualTo(gameResult);
	}
}