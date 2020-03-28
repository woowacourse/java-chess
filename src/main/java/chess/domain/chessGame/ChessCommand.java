package chess.domain.chessGame;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import chess.domain.chessPiece.pieceType.PieceColor;

public enum ChessCommand {

	START("start"),
	END("end", ChessCommand::playChessGameByEnd),
	MOVE("move", ChessCommand::playChessGameByMove),
	STATUS("status", ChessCommand::playChessGameByStatus);

	private static final int STATUS_ARGUMENTS_SIZE = 1;
	private static final int MOVE_ARGUMENTS_SIZE = 2;
	private static final int NOT_STATUS_RESULT = 0;

	private final String command;
	private final BiFunction<ChessGame, List<String>, Double> playChessGameByCommand;

	ChessCommand(String command, BiFunction<ChessGame, List<String>, Double> playChessGameByCommand) {
		this.command = command;
		this.playChessGameByCommand = playChessGameByCommand;
	}

	ChessCommand(String command) {
		this(command, (chessGame, arguments) -> {
			throw new UnsupportedOperationException("실행할 수 있는 기능이 없습니다.");
		});
	}

	private static double playChessGameByMove(ChessGame chessGame, List<String> arguments) {
		validate(arguments, MOVE_ARGUMENTS_SIZE);
		chessGame.move(arguments);
		return NOT_STATUS_RESULT;
	}

	private static double playChessGameByStatus(ChessGame chessGame, List<String> arguments) {
		validate(arguments, STATUS_ARGUMENTS_SIZE);
		return chessGame.status(arguments);
	}

	private static double playChessGameByEnd(ChessGame chessGame, List<String> strings) {
		chessGame.end();
		return NOT_STATUS_RESULT;
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

	public void playChessGame(ChessGame chessGame, List<String> arguments, BiConsumer<PieceColor, Double> printStatus) {
		validate(chessGame, arguments, printStatus);
		double score = this.playChessGameByCommand.apply(chessGame, arguments);

		if (this.equals(STATUS)) {
			printStatus.accept(chessGame.getCurrentTurnColor(), score);
		}
	}

	private void validate(ChessGame chessGame, List<String> arguments, BiConsumer<PieceColor, Double> printStatus) {
		Objects.requireNonNull(chessGame, "체스 게임이 null입니다.");
		Objects.requireNonNull(arguments, "명령어 인자가 null입니다.");
		Objects.requireNonNull(printStatus, "점수 출력 함수가 null입니다.");
	}

}
