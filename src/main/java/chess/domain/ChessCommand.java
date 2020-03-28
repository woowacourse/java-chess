package chess.domain;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum ChessCommand {

	START("start"),
	END("end"),
	MOVE("move", ChessCommand::playChessGameByMove),
	STATUS("status", ChessCommand::playChessGameByStatus);

	private static final int STATUS_ARGUMENTS_SIZE = 1;
	private static final int MOVE_ARGUMENTS_SIZE = 2;

	private final String command;
	private final BiConsumer<ChessGame, List<String>> playChessGameByCommand;

	ChessCommand(String command, BiConsumer<ChessGame, List<String>> playChessGameByCommand) {
		this.command = command;
		this.playChessGameByCommand = playChessGameByCommand;
	}

	ChessCommand(String command) {
		this(command, (chessGame, arguments) -> {
			throw new IllegalArgumentException("실행할 수 있는 기능이 없습니다.");
		});
	}

	private static void playChessGameByMove(ChessGame chessGame, List<String> arguments) {
		validate(arguments, MOVE_ARGUMENTS_SIZE);
		chessGame.move(arguments);
	}

	private static void playChessGameByStatus(ChessGame chessGame, List<String> arguments) {
		validate(arguments, STATUS_ARGUMENTS_SIZE);
		chessGame.status(arguments);
	}

	private static void validate(List<String> arguments, int argumentsSize) {
		if (arguments.size() != argumentsSize) {
			throw new IllegalArgumentException("명령어의 인자가 유효하지 않습니다.");
		}
	}

	public static ChessCommand of(String inputChessCommend) {
		return Arrays.stream(values())
			.filter(chessCommand -> chessCommand.command.equals(inputChessCommend))
			.findFirst()
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 명령을 입력하였습니다."));
	}

	public boolean isStartChessCommand() {
		return this.equals(START);
	}

	public void playChessGame(ChessGame chessGame, List<String> arguments) {
		this.playChessGameByCommand.accept(chessGame, arguments);
	}

}
