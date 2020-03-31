package domain.command;

import static domain.command.InvalidCommandException.*;

import java.util.Arrays;

public enum Command {
	START("start", true, false),
	END("end", true, true),
	MOVE("move", false, true),
	STATUS("status", false, true);

	private String command;
	private boolean isGameCommand;
	private boolean isChessCommand;

	Command(String command, boolean isGameCommand, boolean isChessCommand) {
		this.command = command;
		this.isGameCommand = isGameCommand;
		this.isChessCommand = isChessCommand;
	}

	public static Command ofGameCommand(String inputCommand) {
		return Arrays.stream(Command.values())
			.filter(command -> command.isGameCommand)
			.filter(gameCommand -> gameCommand.command.equals(inputCommand))
			.findFirst()
			.orElseThrow(() -> new InvalidCommandException(INVALID_GAME_COMMAND));
	}

	public static Command ofChessCommand(String inputCommand) {
		return Arrays.stream(Command.values())
			.filter(command -> command.isChessCommand)
			.filter(chessCommand -> chessCommand.command.equals(inputCommand))
			.findFirst()
			.orElseThrow(() -> new InvalidCommandException(INVALID_CHESS_COMMAND));
	}

	public Boolean isMove(){
		return MOVE.equals(this);
	}

	public Boolean isStatus(){
		return STATUS.equals(this);
	}

	public Boolean isNotEnd(){
		return END != this;
	}
}
