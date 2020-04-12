package domain.piece;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.piece.position.Direction;
import domain.piece.team.Team;

/**
 *   class description
 *
 *   @author ParkDooWon
 */
public class PawnMoveValidatorTest {
	@ParameterizedTest
	@CsvSource({"N, WHITE", "NW, WHITE", "S, BLACK", "SE, BLACK"})
	void isValidPawnDirectionTest(Direction direction, Team team) {
		assertThat(PawnMoveValidator.isValidPawnDirection(direction, team)).isTrue();
	}

	@ParameterizedTest
	@CsvSource({"2, WHITE, true", "7, BLACK, true", "7, WHITE, false", "2, BLACK, false"})
	void isInitialRankTest(int rank, Team team, boolean expected) {
		assertThat(PawnMoveValidator.isInitialRank(rank, team)).isEqualTo(expected);
	}
}
