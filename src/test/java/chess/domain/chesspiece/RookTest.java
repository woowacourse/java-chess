package chess.domain.chesspiece;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public class RookTest {
	@DisplayName("못 가는 경우")
	@Test
	void makePathTest1() {
		ChessPiece rook = new Rook(Position.of(4, 4), Team.WHITE);

		assertThatThrownBy(() -> rook.makePathAndValidate(new Blank(Position.of(3, 5))))
			.isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("갈 수 있는 경우")
	@Test
	void makePathTest2() {
		ChessPiece rook = new Rook(Position.of(4, 4), Team.WHITE);
		Positions actualPositions = rook.makePathAndValidate(new Blank(Position.of(4, 1)));
		Positions expectedPositions = new Positions(
			Arrays.asList(Position.of(4, 3), Position.of(4, 2)));

		assertThat(actualPositions).isEqualTo(expectedPositions);
	}
}
