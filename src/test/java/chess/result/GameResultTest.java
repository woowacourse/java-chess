package chess.result;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
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
				new ArrayList<Piece>() {{
					add(Pawn.of(Team.WHITE));
				}},
				new ArrayList<Piece>(),
				GameResult.WIN
			), Arguments.of(
				new ArrayList<Piece>() {{
					add(Pawn.of(Team.WHITE));
				}},
				new ArrayList<Piece>() {{
					add(Pawn.of(Team.BLACK));
				}},
				GameResult.DRAW
			), Arguments.of(
				new ArrayList<Piece>(),
				new ArrayList<Piece>() {{
					add(Pawn.of(Team.BLACK));
				}},
				GameResult.LOSE
			)
		);
	}

	@DisplayName("compare값을 통하여 승패정보를 제대로 가져오는지 확인")
	@ParameterizedTest
	@MethodSource("providerScore")
	void findResult(List<Piece> whiteTeam, List<Piece> blackTeam, GameResult expect) {
		Score whiteTeamScore = new Score(whiteTeam, 0);
		Score blackTeamScore = new Score(blackTeam, 0);

		GameResult actual = GameResult.findResult(whiteTeamScore, blackTeamScore);
		assertThat(actual).isEqualTo(expect);
	}
}