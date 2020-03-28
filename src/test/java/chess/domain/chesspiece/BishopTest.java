package chess.domain.chesspiece;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.Team;
import chess.domain.position.Position;
import chess.domain.position.Positions;

public class BishopTest {
	private ChessPiece bishop;

	@BeforeEach
	void setUp() {
		bishop = new Bishop(Position.of(4, 4), Team.BLACK);
	}

	@DisplayName("오른쪽 위 대각선의 경우")
	@Test
	void makePathTest() {
		Positions actualPositions = bishop.makePathAndValidate(new Blank(Position.of(7, 7)));
		Positions expectedPositions = new Positions(
			Arrays.asList(Position.of(5, 5), Position.of(6, 6)));

		assertThat(actualPositions).isEqualTo(expectedPositions);
	}

	@DisplayName("오른쪽 아래 대각선의 경우")
	@Test
	void makePathTest2() {
		Positions actualPositions = bishop.makePathAndValidate(new Blank(Position.of(1, 7)));
		Positions expectedPositions = new Positions(
			Arrays.asList(Position.of(3, 5), Position.of(2, 6)));

		assertThat(actualPositions).isEqualTo(expectedPositions);
	}

	@DisplayName("못 가는 경우")
	@Test
	void makePathTest3() {
		assertThatThrownBy(() -> bishop.makePathAndValidate(new Blank(Position.of(3, 4))))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
