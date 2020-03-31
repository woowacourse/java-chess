package chess.domain.chessGame;

import java.util.List;
import java.util.Objects;

import chess.domain.chessPiece.pieceType.PieceColor;
import chess.domain.position.Position;

public class ChessCommand {

	private static final int COMMAND_STATUS_INDEX = 0;
	private static final int SOURCE_POSITION_INDEX = 1;
	private static final int TARGET_POSITION_INDEX = 2;
	private static final int STATUS_PIECE_COLOR_INDEX = 1;

	private final CommandStatus commandStatus;
	private final List<String> commandArguments;

	private ChessCommand(CommandStatus commandStatus, List<String> commandArguments) {
		this.commandStatus = commandStatus;
		this.commandArguments = commandArguments;
	}

	public static ChessCommand of(List<String> commandArguments) {
		validate(commandArguments);

		CommandStatus commandStatus = CommandStatus.of(commandArguments.get(COMMAND_STATUS_INDEX));
		validateArgumentsSize(commandStatus, commandArguments.size());
		return new ChessCommand(commandStatus, commandArguments);
	}

	private static void validateArgumentsSize(CommandStatus commandStatus, int argumentsSize) {
		if (!commandStatus.isCorrectArgumentsSize(argumentsSize)) {
			throw new IllegalArgumentException("유효한 명령어 인자가 아닙니다.");
		}
	}

	private static void validate(List<String> commandArguments) {
		if (Objects.isNull(commandArguments) || commandArguments.isEmpty()) {
			throw new IllegalArgumentException("유효한 체스 명령어가 아닙니다.");
		}
	}

	public boolean isStartChessCommand() {
		return this.commandStatus.isStartCommandStatus();
	}

	public boolean isMoveChessCommand() {
		return this.commandStatus.isMoveCommandStatus();
	}

	public boolean isStatusChessCommand() {
		return this.commandStatus.isStatusCommandStatus();
	}

	public boolean isEndChessCommand() {
		return this.commandStatus.isEndCommandStatus();
	}

	public Position getSourcePosition() {
		checkIsMoveCommandStatus();
		return Position.of(commandArguments.get(SOURCE_POSITION_INDEX));
	}

	public Position getTargetPosition() {
		checkIsMoveCommandStatus();
		return Position.of(commandArguments.get(TARGET_POSITION_INDEX));
	}

	private void checkIsMoveCommandStatus() {
		if (!this.commandStatus.isMoveCommandStatus()) {
			throw new UnsupportedOperationException("move 명령어만 지원하는 기능입니다.");
		}
	}

	public PieceColor getStatusPieceColor() {
		checkIsStatusCommandStatus();
		return PieceColor.of(commandArguments.get(STATUS_PIECE_COLOR_INDEX));
	}

	private void checkIsStatusCommandStatus() {
		if (!this.commandStatus.isStatusCommandStatus()) {
			throw new UnsupportedOperationException("status 명령어만 지원하는 기능입니다.");
		}
	}

}
