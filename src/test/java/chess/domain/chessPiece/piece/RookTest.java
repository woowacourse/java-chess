package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.MovePatternFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RookTest {
	@Test
	@DisplayName("이동 성공 테스트")
	void movable() {
		Position source = Position.of("d2");
		Position target = Position.of("d4");

		MovePattern movePattern = MovePatternFactory.findMovePattern(source, target);
		Piece rook = new Rook(source, new BlackTeam());

		rook.validateMovePattern(movePattern);
	}

	@Test
	@DisplayName("이동 실패 테스트")
	void isNotMovable() {
		Position source = Position.of("d2");
		Position target = Position.of("e3");

		MovePattern movePattern = MovePatternFactory.findMovePattern(source, target);
		Piece rook = new Rook(source, new BlackTeam());

		assertThatThrownBy(() -> rook.validateMovePattern(movePattern))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 말이 갈 수 없는 칸입니다");
	}
}
