package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movetype.MoveType;
import chess.domain.movetype.MoveTypeFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BishopTest {
	@Test
	@DisplayName("이동 성공 테스트")
	void movable() {
		Position source = Position.of("d2");
		Position target = Position.of("g5");

		MoveType moveType = MoveTypeFactory.of(source, target);
		Piece bishop = new Bishop(source, new BlackTeam());

		assertThat(bishop.isMovable(moveType)).isTrue();
	}

	@Test
	@DisplayName("이동 실패 테스트")
	void isNotMovable() {
		Position source = Position.of("d2");
		Position target = Position.of("d4");

		MoveType moveType = MoveTypeFactory.of(source, target);
		Piece bishop = new Bishop(source, new BlackTeam());

		assertThat(bishop.isMovable(moveType)).isFalse();
	}

}
