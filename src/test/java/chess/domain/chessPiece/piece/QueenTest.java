package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.MovePatternFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class QueenTest {
	@Test
	@DisplayName("이동 성공 테스트")
	void movable() {
		Position source = Position.of("d2");
		Position target = Position.of("f2");

		MovePattern movePattern = MovePatternFactory.findMovePattern(source, target);
		Piece queen = new Queen(source, new BlackTeam());

		queen.validateMovePattern(movePattern, null);
	}

	@Test
	@DisplayName("이동 실패 테스트")
	void isNotMovable() {
		Position source = Position.of("d2");
		Position target = Position.of("f3");

		MovePattern movePattern = MovePatternFactory.findMovePattern(source, target);
		Piece queen = new Queen(source, new BlackTeam());

		assertThatThrownBy(() -> queen.validateMovePattern(movePattern, null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 말이 갈 수 없는 칸입니다");
	}

}
