package chess.domain.chesspiece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public class PawnTest {
	private ChessPiece whiteTeamPawn;
	private ChessPiece blackTeamPawn;

	@BeforeEach
	void setUp() {
		whiteTeamPawn = new Pawn(Position.of(2, 2), Team.WHITE);
		blackTeamPawn = new Pawn(Position.of(7, 2), Team.BLACK);
	}

	@DisplayName("화이트팀인 경우 위로 이동 테스트")
	@Test
	void validateMoveTest() {
		whiteTeamPawn.validateCanGo(new Blank(Position.of(3, 2)));

		assertThatThrownBy(() -> whiteTeamPawn.validateCanGo(new Blank(Position.of(3, 3))))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("화이트팀일 때 대각선으로 잡는 경우, 같은팀일 때")
	@Test
	void validateMoveTest2() {
		whiteTeamPawn.validateCanGo(new King(Position.of(3, 3), Team.BLACK));

		assertThatThrownBy(() -> blackTeamPawn.validateCanGo(new King(Position.of(3, 3), Team.WHITE)))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("블랙팀인 경우 아래로 이동 테스트")
	@Test
	void validateMoveTest3() {
		blackTeamPawn.validateCanGo(new Blank(Position.of(6, 2)));

		assertThatThrownBy(() -> blackTeamPawn.validateCanGo(new Blank(Position.of(6, 1))))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("블랙팀일 때 대각선으로 잡는 경우, BLANK일 때")
	@Test
	void validateMoveTest4() {
		blackTeamPawn.validateCanGo(new King(Position.of(6, 1), Team.WHITE));

		assertThatThrownBy(() -> blackTeamPawn.validateCanGo(new King(Position.of(6, 1), Team.BLANK)))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
