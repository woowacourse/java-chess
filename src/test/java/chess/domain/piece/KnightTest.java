package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {

	@ParameterizedTest
	@DisplayName("나이트는 팀과 관계 없이 모든 방향의 L자 모양으로 이동할 수 있다.")
	@CsvSource({"1,2","2,1","2,-1","1,-2","-1,-2","-2,-1","-2,1","-1,2"})
	void knightCanMoveEveryDirectionTest(int x, int y) {
		Knight whiteKnight = Knight.from(Team.WHITE);
		Knight blackKnight = Knight.from(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteKnight.isMobile(relativePosition));
		assertTrue(blackKnight.isMobile(relativePosition));
	}
}
