package chess.domain.piece.pawn;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

class MovedPawnTest {
	private Piece whitePawn;
	private Map<Position, Team> boardDto;

	@BeforeEach
	void setUp() {
		whitePawn = MovedPawn.of(Team.WHITE, Position.of("c4"));
		boardDto = new HashMap<>();
		boardDto.put(Position.of("c4"), Team.WHITE);
	}

	@Test
	@DisplayName("진행 방향에 장애물이 없는 경우")
	void anyOnPath() {
		assertThat(whitePawn.move(Position.of("c4"), Position.of("c5"), boardDto))
			.isInstanceOf(MovedPawn.class);
	}

	@ParameterizedTest
	@DisplayName("대각선으로 이동하고, 대각선에 적군이 위치한 경우 정상 이동")
	@ValueSource(strings = {"d5", "b5"})
	void enemyOnTarget(String target) {
		boardDto.put(Position.of(target), Team.BLACK);
		assertThat(whitePawn.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(MovedPawn.class);
	}

	@ParameterizedTest
	@DisplayName("대각선에 적군이 없는 경우, 이동할 수 없습니다.")
	@ValueSource(strings = {"d5", "b5"})
	void NoEnemyOnDiagonalPath(String target) {
		assertThatThrownBy(() -> whitePawn.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("적군이 없는 곳이라면 Pawn은 대각선으로 이동할 수 없습니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"d5", "b5", "c5"})
	@DisplayName("목적지에 아군이 있는 경우 진행할 수 없습니다.")
	void allyOnTarget(String target) {
		boardDto.put(Position.of(target), Team.WHITE);
		assertThatThrownBy(() -> {
			whitePawn.move(Position.of("c4"), Position.of(target), boardDto);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("목적지에 아군이 존재합니다.");
	}

	@Test
	@DisplayName("폰의 기본전략에서는, 앞으로 두칸 전진할 수 없습니다.")
	void canNotMoveFrontDouble() {
		assertThatThrownBy(() -> {
			whitePawn.move(Position.of("c4"), Position.of("c6"), boardDto);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("도달할 수 없는 거리입니다.");
	}

	@Test
	@DisplayName("앞에 적군이 있으면 폰은 앞으로 전진할 수 없습니다.")
	void enemyOnFront() {
		boardDto.put(Position.of("c5"), Team.BLACK);
		assertThatThrownBy(() -> {
			whitePawn.move(Position.of("c4"), Position.of("c5"), boardDto);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("Pawn은 대각선으로만 공격할 수 있습니다.");
	}
}