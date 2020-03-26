package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movetype.MoveType;
import chess.domain.movetype.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QueenTest {
	@Test
	@DisplayName("이동 성공 테스트")
	void movable() {
		Position source = Position.of("d2");
		Position target = Position.of("f2");

		MoveType moveType = MoveTypeFactory.of(source, target);
		Piece queen = new Queen(source, new BlackTeam());

		assertThat(queen.isMovable(moveType)).isTrue();
	}

	@Test
	@DisplayName("이동 실패 테스트")
	void isNotMovable() {
		Position source = Position.of("d2");
		Position target = Position.of("f3");

		MoveType moveType = MoveTypeFactory.of(source, target);
		Piece queen = new Queen(source, new BlackTeam());

		assertThat(queen.isMovable(moveType)).isFalse();
	}

}
