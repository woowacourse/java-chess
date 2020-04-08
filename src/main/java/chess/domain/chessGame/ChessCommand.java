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

	private final CommandType commandType;
	private final List<String> commandArguments;

	private ChessCommand(CommandType commandType, List<String> commandArguments) {
		this.commandType = commandType;
		this.commandArguments = commandArguments;
	}

	public static ChessCommand of(List<String> commandArguments) {
		validate(commandArguments);

		CommandType commandType = CommandType.of(commandArguments.get(COMMAND_STATUS_INDEX));
		validateArgumentsSize(commandType, commandArguments.size());
		return new ChessCommand(commandType, commandArguments);
	}

	private static void validateArgumentsSize(CommandType commandType, int argumentsSize) {
		if (!commandType.isCorrectArgumentsSize(argumentsSize)) {
			throw new IllegalArgumentException("유효한 명령어 인자가 아닙니다.");
		}
	}

	private static void validate(List<String> commandArguments) {
		if (Objects.isNull(commandArguments) || commandArguments.isEmpty()) {
			throw new IllegalArgumentException("유효한 체스 명령어가 아닙니다.");
		}
	}

	public boolean isStartChessCommand() {
		return this.commandType.isStartCommandType();
	}

	public boolean isMoveChessCommand() {
		return this.commandType.isMoveCommandType();
	}

	public boolean isStatusChessCommand() {
		return this.commandType.isStatusCommandType();
	}

	public boolean isEndChessCommand() {
		return this.commandType.isEndCommandType();
	}

	public Position getSourcePosition() {
		checkIsMoveCommandType();
		return Position.of(commandArguments.get(SOURCE_POSITION_INDEX));
	}

	public Position getTargetPosition() {
		checkIsMoveCommandType();
		return Position.of(commandArguments.get(TARGET_POSITION_INDEX));
	}

	private void checkIsMoveCommandType() {
		if (!this.commandType.isMoveCommandType()) {
			throw new UnsupportedOperationException("move 명령어만 지원하는 기능입니다.");
		}
	}

	public PieceColor getStatusPieceColor() {
		checkIsStatusCommandType();
		return PieceColor.of(commandArguments.get(STATUS_PIECE_COLOR_INDEX));
	}

	private void checkIsStatusCommandType() {
		if (!this.commandType.isStatusCommandType()) {
			throw new UnsupportedOperationException("status 명령어만 지원하는 기능입니다.");
		}
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final ChessCommand that = (ChessCommand)o;
		return commandType == that.commandType &&
			Objects.equals(commandArguments, that.commandArguments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(commandType, commandArguments);
	}
}
