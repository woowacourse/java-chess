package chess.domain.chesspiece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;

public class ChessPieceTest {
	private ChessPiece pawn;

	@BeforeEach
	void setUp() {
		pawn = new Pawn(Position.of(2, 2), Team.BLACK);
	}

	@Test
	void isMatchTeamTest() {
		assertThat(pawn.isMatchTeam(Team.BLACK)).isTrue();
		assertThat(pawn.isMatchTeam(Team.WHITE)).isFalse();
	}

	@Test
	void isNotMatchTeam() {
		assertThat(pawn.isNotMatchTeam(Team.WHITE)).isTrue();
		assertThat(pawn.isNotMatchTeam(Team.BLANK)).isTrue();
		assertThat(pawn.isNotMatchTeam(Team.BLACK)).isFalse();
	}

	@Test
	void isSameTeamTest() {
		ChessPiece blackTeamKing = new King(Position.of(2, 2), Team.BLACK);
		ChessPiece whiteTeamKing = new King(Position.of(2, 2), Team.WHITE);

		assertThat(pawn.isSameTeam(blackTeamKing)).isTrue();
		assertThat(pawn.isSameTeam(whiteTeamKing)).isFalse();

	}

	@Test
	void equalsPositionTest() {
		assertThat(pawn.equalsPosition(Position.of(2, 2))).isTrue();
	}

}
