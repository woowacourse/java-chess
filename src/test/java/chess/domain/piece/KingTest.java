package chess.domain.piece;

import chess.domain.Team;
import chess.domain.position.RelativePosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KingTest {

	@ParameterizedTest
	@DisplayName("킹은 팀과 관계 없이 모든 방향으로 1칸 이동할 수 있다.")
	@CsvSource({"0,1","1,1","1,0","1,-1","0,-1","-1,-1","-1,0","-1,1"})
	void kingCanMoveEveryDirectionTest(int x, int y) {
		King whiteKing = King.from(Team.WHITE);
		King blackKing = King.from(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(x, y);

		assertTrue(whiteKing.isMobile(relativePosition, new EmptyPiece()));
		assertTrue(blackKing.isMobile(relativePosition, new EmptyPiece()));
	}

	@ParameterizedTest
	@DisplayName("킹은 팀과 관계 없이 어떤 방향으로든 1칸 초과하여 이동할 수 없다.")
	@CsvSource({"0,1","1,1","1,0","1,-1","0,-1","-1,-1","-1,0","-1,1"})
	void kingCannotMoveMoreThan1Test(int x, int y) {
		int multiplier = 2;
		King whiteKing = King.from(Team.WHITE);
		King blackKing = King.from(Team.BLACK);
		RelativePosition relativePosition = new RelativePosition(multiplier * x, multiplier * y);

		assertFalse(whiteKing.isMobile(relativePosition, new EmptyPiece()));
		assertFalse(blackKing.isMobile(relativePosition, new EmptyPiece()));
	}

	@Test
	@DisplayName("이동하려는 위치에 있는 말의 팀 색깔이 같으면 예외처리한다.")
	void sameTeamTest() {
		King whiteKing = King.from(Team.WHITE);

		assertThatThrownBy(() -> whiteKing.isMobile(new RelativePosition(0, 1), Pawn.from(Team.WHITE))).isInstanceOf(IllegalArgumentException.class)
				.hasMessage("이동하고자 하는 자리에 같은 팀이 존재합니다.");
	}
}
