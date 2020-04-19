package chess.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.domain.chessGame.ChessCommand;

class ChessHistoryEntityTest {

	private static final String MOVE_COMMAND = "move";

	@ParameterizedTest
	@NullSource
	void validate_NullStart_ExceptionThrown(final String start) {
		assertThatThrownBy(() -> ChessHistoryEntity.of(1, 1, start, "b2", LocalDateTime.now()))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("출발 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void validate_NullEnd_ExceptionThrown(final String end) {
		assertThatThrownBy(() -> ChessHistoryEntity.of(1, 1, "b1", end, LocalDateTime.now()))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("도착 위치가 null입니다.");
	}

	@Test
	void of_HistoryIdAndGameIdAndStartAndEndAndCreatedTime_GenerateInstance() {
		assertThat(ChessHistoryEntity.of(1, 1, "b1", "b2", LocalDateTime.now())).isInstanceOf(ChessHistoryEntity.class);
	}

	@Test
	void of_GameIdAndStartAndEndAndCreatedTime_GenerateInstance() {
		assertThat(ChessHistoryEntity.of(1, "b1", "b2", LocalDateTime.now())).isInstanceOf(ChessHistoryEntity.class);
	}

	@Test
	void of_StartAndEnd_GenerateInstance() {
		assertThat(ChessHistoryEntity.of("b1", "b2")).isInstanceOf(ChessHistoryEntity.class);
	}

	@ParameterizedTest
	@NullSource
	void of_NullChessHistoryEntity_ExceptionThrown(final ChessHistoryEntity entity) {
		assertThatThrownBy(() -> ChessHistoryEntity.of(1, entity))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("엔티티가 null입니다.");
	}

	@Test
	void of_HistoryIdAndChessHistoryEntity_GenerateInstance() {
		final ChessHistoryEntity entity = ChessHistoryEntity.of("b1", "b2");

		assertThat(ChessHistoryEntity.of(1, entity)).isInstanceOf(ChessHistoryEntity.class);
	}

	@Test
	void of_ChessIdAndStartAndEnd_GenerateInstance() {
		assertThat(ChessHistoryEntity.of("b1", "b2")).isInstanceOf(ChessHistoryEntity.class);
	}

	@Test
	void generateMoveCommand_MoveCommandFromStartToEnd_ReturnChessCommand() {
		final String start = "b1";
		final String end = "b2";
		final ChessHistoryEntity chessHistoryEntity = ChessHistoryEntity.of(start, end);

		final ChessCommand expected = ChessCommand.of(Arrays.asList(MOVE_COMMAND, start, end));
		assertThat(chessHistoryEntity.generateMoveCommand()).isEqualTo(expected);
	}

}