package chess.domain;

import chess.domain.chessPiece.piece.Pawn;
import chess.domain.chessPiece.piece.Rook;
import chess.domain.chessPiece.position.File;
import chess.domain.chessPiece.position.Position;
import chess.domain.chessPiece.position.Rank;
import chess.domain.chessPiece.team.BlackTeam;
import chess.domain.movepattern.MovePattern;
import chess.domain.movepattern.MovePatternFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PositionTest {
	@Test
	@DisplayName("of 테스트 - String 입력")
	void of() {
		Position result = Position.of(File.F, Rank.SIX);

		assertThat(Position.of("f6")).isEqualTo(result);
	}

	@DisplayName("of 테스트 - position 입력")
	@Test
	void of2() {
		Position position = Position.of("a1");
		Position newPosition = Position.of(position);

		assertThat(position == newPosition).isFalse();
	}

	@DisplayName("이동경로에 다른 말이 있을 때 이동 하려던 말이 원래 위치에 있는지 테스트")
	@Test
	void move() {
		Position sourcePosition = Position.of("a1");
		Position obstaclePosition = Position.of("a4");
		Position targetPosition = Position.of("a5");
		MovePattern movePattern = MovePatternFactory.findMovePattern(sourcePosition, targetPosition);

		Rook rook = new Rook(sourcePosition, new BlackTeam());
		Pawn pawn = new Pawn(obstaclePosition, new BlackTeam());
	}
}
