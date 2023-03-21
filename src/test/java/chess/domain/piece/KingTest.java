package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

	@ParameterizedTest
	@DisplayName("킹은 팀과 관계 없이 모든 방향으로 1칸 이동할 수 있다.")
	@CsvSource({"0,1","1,1","1,0","1,-1","0,-1","-1,-1","-1,0","-1,1"})
	void kingCanMoveEveryDirectionTest(int x, int y) {
		King whiteKing = new King(Team.WHITE);
		King blackKing = new King(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteKing.isMobile(relativePosition));
		assertTrue(blackKing.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("킹은 팀과 관계 없이 어떤 방향으로든 1칸 초과하여 이동할 수 없다.")
	@CsvSource({"0,1","1,1","1,0","1,-1","0,-1","-1,-1","-1,0","-1,1"})
	void kingCannotMoveMoreThan1Test(int x, int y) {
		int multiplier = 2;
		King whiteKing = new King(Team.WHITE);
		King blackKing = new King(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(multiplier * x, multiplier * y);

		assertFalse(whiteKing.isMobile(relativePosition));
		assertFalse(blackKing.isMobile(relativePosition));
	}
}
