package domain.piece.position;

import static domain.piece.position.Direction.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DirectionTest {
	@DisplayName("모든 방향 리스트 반환")
	@Test
	void everyDirection() {
		List<Direction> expected = Arrays.asList(N, S, E, W, NE, NW, SE, SW);
		assertThat(Direction.everyDirection()).isEqualTo(expected);
	}

	@DisplayName("직선 방향 리스트 반환")
	@Test
	void linearDirection() {
		List<Direction> expected = Arrays.asList(N, S, E, W);
		assertThat(Direction.linearDirection()).isEqualTo(expected);
	}

	@DisplayName("대각선 방향 리스트 반환")
	@Test
	void diagonalDirection() {
		List<Direction> expected = Arrays.asList(NE, NW, SE, SW);
		assertThat(Direction.diagonalDirection()).isEqualTo(expected);
	}

	@DisplayName("Knight 방향 리스트 반환")
	@Test
	void knightDirection() {
		List<Direction> expected = Arrays.asList(NNE, NNW, SSE, SSW, NEE, NWW, SEE, SWW);
		assertThat(Direction.knightDirection()).isEqualTo(expected);
	}

	@DisplayName("White Pawn 방향 리스트 반환")
	@Test
	void whitePawnDirection() {
		List<Direction> expected = Arrays.asList(N, NE, NW);
		assertThat(Direction.whitePawnDirection()).isEqualTo(expected);
	}

	@DisplayName("Black Pawn 방향 리스트 반환")
	@Test
	void blackPawnDirection() {
		List<Direction> expected = Arrays.asList(S, SE, SW);
		assertThat(Direction.blackPawnDirection()).isEqualTo(expected);
	}
}
