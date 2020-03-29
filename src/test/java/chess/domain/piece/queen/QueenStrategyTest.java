package chess.domain.piece.queen;

import static org.assertj.core.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

class QueenStrategyTest {
	private Piece whiteQueen;
	private Map<Position, Team> boardDto;

	@BeforeEach
	void setUp() {
		whiteQueen = Queen.of(Team.WHITE, Position.of("c4"));
		boardDto = new HashMap<>();
		boardDto.put(Position.of("c4"), Team.WHITE);
	}

	@ParameterizedTest
	@DisplayName("진행 방향에 장애물이 없는 경우")
	@ValueSource(strings = {"a6", "a2", "g8", "f1", "c2", "c8", "a4", "h4"})
	void anyOnPath(String target) {
		assertThat(whiteQueen.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@DisplayName("도착지에 적팀 기물이 있는 경우")
	@ValueSource(strings = {"a6", "a2", "g8", "f1", "c2", "c8", "a4", "h4"})
	void enemyOnTarget(String target) {
		boardDto.put(Position.of(target), Team.BLACK);
		assertThat(whiteQueen.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(Queen.class);
	}

	@ParameterizedTest
	@DisplayName("도착지에 아군 기물이 있는 경우")
	@ValueSource(strings = {"a6", "a2", "g8", "f1", "c2", "c8", "a4", "h4"})
	void allyOnTarget(String target) {
		boardDto.put(Position.of(target), Team.WHITE);
		assertThatThrownBy(() -> whiteQueen.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("목적지에 아군이 존재합니다.");
	}

	@ParameterizedTest
	@DisplayName("진행 방향에 적팀 기물이 있는 경우")
	@CsvSource(value = {"a6:b5", "a2:b3", "g8:e6", "f1:e2", "c2:c3", "c8:c7", "a4:b4", "h4:f4"}, delimiter = ':')
	void enemyOnPath(String target, String path) {
		boardDto.put(Position.of(path), Team.BLACK);
		assertThatThrownBy(() -> whiteQueen.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("해당 방향에 장애물이 존재합니다.");
	}

	@ParameterizedTest
	@DisplayName("진행 방향에 아군 기물이 있는 경우")
	@CsvSource(value = {"a6:b5", "a2:b3", "g8:e6", "f1:e2", "c2:c3", "c8:c7", "a4:b4", "h4:f4"}, delimiter = ':')
	void allyOnPath(String target, String path) {
		boardDto.put(Position.of(path), Team.WHITE);
		assertThatThrownBy(() -> whiteQueen.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("해당 방향에 장애물이 존재합니다.");
	}

	@ParameterizedTest
	@DisplayName("진행 방향이 옳지 못한 경우")
	@ValueSource(strings = {"b6", "d6", "e5", "e3", "d2", "b2"})
	void illegalDirectionTarget(String target) {
		assertThatThrownBy(() -> whiteQueen.move(Position.of("c4"), Position.of(target), boardDto))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessageContaining("해당 방향으로 이동할 수 없습니다.");
	}
}