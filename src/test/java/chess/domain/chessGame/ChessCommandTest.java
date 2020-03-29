package chess.domain.chessGame;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

class ChessCommandTest {

	@Test
	void of_CommandArguments_GenerateInstance() {
		assertThat(ChessCommand.of(Arrays.asList("status", "white"))).isInstanceOf(ChessCommand.class);
	}

	@ParameterizedTest
	@NullAndEmptySource
	void of_NullAndEmptyCommandArguments_ExceptionThrown(List<String> commandArguments) {
		assertThatThrownBy(() -> ChessCommand.of(commandArguments))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 체스 명령어가 아닙니다.");
	}

	@Test
	void validateArgumentSize_InvalidArgumentSize_ExceptionThrown() {
		assertThatThrownBy(() -> ChessCommand.of(Arrays.asList("status")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("유효한 명령어 인자가 아닙니다.");
	}

	@Test
	void isStartChessCommand_StartCommandStatus_ReturnTrue() {
		List<String> commandArguments = Arrays.asList("start");

		assertThat(ChessCommand.of(commandArguments).isStartChessCommand()).isTrue();
	}

	@Test
	void isStartChessCommand_NotStartCommandStatus_ReturnFalse() {
		List<String> commandArguments = Arrays.asList("status", "white");

		assertThat(ChessCommand.of(commandArguments).isStartChessCommand()).isFalse();
	}

	@Test
	void isMoveChessCommand_MoveCommandStatus_ReturnTrue() {
		List<String> commandArguments = Arrays.asList("move", "b1", "b2");

		assertThat(ChessCommand.of(commandArguments).isMoveChessCommand()).isTrue();
	}

	@Test
	void isMoveChessCommand_NotMoveCommandStatus_ReturnFalse() {
		List<String> commandArguments = Arrays.asList("start");

		assertThat(ChessCommand.of(commandArguments).isMoveChessCommand()).isFalse();
	}

	@Test
	void isStatusChessCommand_StatusCommandStatus_ReturnTrue() {
		List<String> commandArguments = Arrays.asList("status", "white");

		assertThat(ChessCommand.of(commandArguments).isStatusChessCommand()).isTrue();
	}

	@Test
	void isStatusChessCommand_NotStatusCommandStatus_ReturnFalse() {
		List<String> commandArguments = Arrays.asList("end");

		assertThat(ChessCommand.of(commandArguments).isStatusChessCommand()).isFalse();
	}

	@Test
	void isEndChessCommand_EndCommandStatus_ReturnTrue() {
		List<String> commandArguments = Arrays.asList("end");

		assertThat(ChessCommand.of(commandArguments).isEndChessCommand()).isTrue();
	}

	@Test
	void isEndChessCommand_NotEndCommandStatus_ReturnFalse() {
		List<String> commandArguments = Arrays.asList("start");

		assertThat(ChessCommand.of(commandArguments).isEndChessCommand()).isFalse();
	}

	@Test
	void getSourcePosition_MoveCommandStatus_ReturnSourcePosition() {
		List<String> commandArguments = Arrays.asList("move", "b1", "b2");

		assertThat(ChessCommand.of(commandArguments).getSourcePosition()).isEqualTo(Position.of("b1"));
	}

	@Test
	void getTargetPosition_MoveCommandStatus_ReturnTargetPosition() {
		List<String> commandArguments = Arrays.asList("move", "b1", "b2");

		assertThat(ChessCommand.of(commandArguments).getTargetPosition()).isEqualTo(Position.of("b2"));
	}

	@Test
	void checkIsMoveCommandStatus_NotMoveCommandStatus_ExceptionThrown() {
		assertThatThrownBy(() -> ChessCommand.of(Arrays.asList("status", "white")).getSourcePosition())
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessage("move 명령어만 지원하는 기능입니다.");
	}

	@Test
	void getStatusPieceColor_StatusCommandStatus_ReturnStatusPieceColor() {
		List<String> commandArguments = Arrays.asList("status", "white");

		assertThat(ChessCommand.of(commandArguments).getStatusPieceColor()).isEqualTo(PieceColor.WHITE);
	}

	@Test
	void checkIsStatusCommandStatus_NotStatusCommandStatus_ExceptionThrown() {
		assertThatThrownBy(() -> ChessCommand.of(Arrays.asList("start")).getStatusPieceColor())
			.isInstanceOf(UnsupportedOperationException.class)
			.hasMessage("status 명령어만 지원하는 기능입니다.");
	}

}