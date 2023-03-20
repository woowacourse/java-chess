package chess.domain.piece;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import chess.domain.position.RelativePosition;
import chess.domain.Team;

class KnightTest {

	@ParameterizedTest
	@DisplayName("나이트는 팀과 관계 없이 모든 방향의 L자 모양으로 이동할 수 있다.")
	@CsvSource({"1,2","2,1","2,-1","1,-2","-1,-2","-2,-1","-2,1","-1,2"})
	void knightCanMoveEveryDirectionTest(int x, int y) {
		Knight whiteKnight = new Knight(Team.WHITE);
		Knight blackKnight = new Knight(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteKnight.isMobile(relativePosition));
		assertTrue(blackKnight.isMobile(relativePosition));
	}
}
