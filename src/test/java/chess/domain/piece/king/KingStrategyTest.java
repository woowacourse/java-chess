package chess.domain.piece.king;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

class KingStrategyTest {
	private Piece whiteKing;
	private Map<Position, Team> boardDto;

	@BeforeEach
	void setUp() {
		whiteKing = King.of(Team.WHITE, Position.of("c4"));
		boardDto = new HashMap<>();
		boardDto.put(Position.of("c4"), Team.WHITE);
	}

	@ParameterizedTest
	@DisplayName("진행 방향에 장애물이 없는 경우")
	@ValueSource(strings = {"d3", "d4", "d5", "c5", "b5", "b4", "b3"})
	void anyOnPath(String target) {
		assertThat(whiteKing.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(King.class);
	}

	@ParameterizedTest
	@DisplayName("도착지에 적팀 기물이 있는 경우")
	@ValueSource(strings = {"d3", "d4", "d5", "c5", "b5", "b4", "b3"})
	void enemyOnTarget(String target) {
		boardDto.put(Position.of(target), Team.BLACK);
		assertThat(whiteKing.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(King.class);
	}

	@ParameterizedTest
	@DisplayName("도착지에 아군 기물이 있는 경우")
	@ValueSource(strings = {"d3", "d4", "d5", "c5", "b5", "b4", "b3"})
	void allyOnTarget(String target) {
		boardDto.put(Position.of(target), Team.WHITE);
		assertThatThrownBy(() -> whiteKing.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("목적지에 아군이 존재합니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"a2", "c2", "e2", "e4", "e6", "c6", "a6", "a4"})
	@DisplayName("방향은 옳으나, 진행거리가 범위 초과인 경우")
	void overDistanceRange(String target) {
		assertThatThrownBy(() -> {
			whiteKing.move(Position.of("c4"), Position.of(target), boardDto);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("도달할 수 없는 거리입니다.");
	}

	@ParameterizedTest
	@ValueSource(strings = {"a3", "a5", "b6", "d6", "e5", "e3", "d2", "b2"})
	@DisplayName("진행방향이 이동할 수 없는 방향일 때")
	void illegalDirection(String target) {
		assertThatThrownBy(() -> {
			whiteKing.move(Position.of("c4"), Position.of(target), boardDto);
		}).isInstanceOf(IllegalArgumentException.class)
			.hasMessageMatching("해당 방향으로 이동할 수 없습니다.");
	}
}