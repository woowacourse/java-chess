package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.MovePatternFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BishopTest {
	private Piece bishop;
	private Position source;

	@BeforeEach
	void setUp() {
		source = Position.of("c1");
		bishop = new Bishop(source, new BlackTeam());
	}

	@DisplayName("movable 의 인자가 null 일때 테스트")
	@Test
	void validateMovePatternNull() {
		assertThatThrownBy(() -> bishop.validateMovePattern(null, null))
				.isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("이동 성공 테스트")
	void validateMovePattern() {
		Position target = Position.of("a3");

		MovePattern movePattern = MovePatternFactory.findMovePattern(source, target);

		bishop.validateMovePattern(movePattern, null);
	}

	@Test
	@DisplayName("이동 실패 테스트")
	void validateMovePatternWithError() {
		Position target = Position.of("a4");
		assertThatThrownBy(() -> MovePatternFactory.findMovePattern(source, target))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 말이 갈 수 없는 칸입니다");
	}
}
