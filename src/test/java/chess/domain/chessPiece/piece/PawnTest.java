package chess.domain.chessPiece.piece;

import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.chessPiece.team.WhiteTeam;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.MovePatternFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PawnTest {
	@Test
	@DisplayName("폰 2칸이동 테스트 성공")
	void isMovable() {
		Position sourcePosition = Position.of("b2");
		Position targetPosition = Position.of("b4");

		Pawn blackPawn = new Pawn(sourcePosition, new BlackTeam());
		MovePattern movePattern = MovePatternFactory.findMovePattern(sourcePosition, targetPosition);

		blackPawn.validateMovable(movePattern, null);
	}

	@Test
	@DisplayName("폰 2칸이동 테스트 실패")
	void isMovable2() {
		Position sourcePosition = Position.of("b3");
		Position targetPosition = Position.of("b5");

		Pawn blackPawn = new Pawn(sourcePosition, new BlackTeam());
		MovePattern movePattern = MovePatternFactory.findMovePattern(sourcePosition, targetPosition);

		assertThatThrownBy(() -> blackPawn.validateMovable(movePattern, null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 말이 갈 수 없는 칸입니다.");
	}

	@Test
	@DisplayName("폰 공격 테스트")
	void isMovableAttack() {
		Pawn blackPawn = new Pawn(Position.of("b2"), new BlackTeam());
		Piece whitePawn = new Pawn(Position.of("c3"), new WhiteTeam());
		MovePattern movePattern = MovePatternFactory.findMovePattern(blackPawn.position, whitePawn.position);

		blackPawn.validateMovable(movePattern, whitePawn);
	}

	@Test
	@DisplayName("폰 뒤로 이동 테스트")
	void isMovableFalse() {
		Pawn blackPawn = new Pawn(Position.of("b2"), new BlackTeam());
		MovePattern movePattern = MovePatternFactory.findMovePattern(blackPawn.position, Position.of("b1"));
		assertThatThrownBy(() -> blackPawn.validateMovable(movePattern, null))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("해당 말이 갈 수 없는 칸입니다.");
	}
}
