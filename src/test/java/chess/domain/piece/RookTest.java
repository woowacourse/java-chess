package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.RelativePosition;
import chess.domain.Team;

class RookTest {

	@ParameterizedTest
	@DisplayName("룩은 팀과 관계 없이 십자가 방향으로 거리 제한 없이 이동할 수 있다.")
	@CsvSource({"0,1","1,0","0,-1","-1,0","0,100","100,0","0,-100","-100,0"})
	void rookCanMoveCrossDirectionTest(int x, int y) {
		Rook whiteRook = new Rook(Team.WHITE);
		Rook blackRook = new Rook(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteRook.isMobile(relativePosition));
		assertTrue(blackRook.isMobile(relativePosition));
	}

	@ParameterizedTest
	@DisplayName("룩은 팀과 관계 없이 대각선으로 이동할 수 없다.")
	@CsvSource({"1,1","1,-1","-1,-1","-1,1"})
	void rookCannotMoveDiagonalDirectionTest(int x, int y) {
		Rook whiteRook = new Rook(Team.WHITE);
		Rook blackRook = new Rook(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertFalse(whiteRook.isMobile(relativePosition));
		assertFalse(blackRook.isMobile(relativePosition));
	}
}
