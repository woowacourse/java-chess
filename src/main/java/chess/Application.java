package chess;

import chess.controller.ChessController;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Application {

	private static final ChessController chessController = new ChessController();
	private static final int COMMAND_INDEX = 0;

	private static Map<Command, Consumer<List<String>>> functionByCommand = createFunctionByCommand();

	private static Map<Command, Consumer<List<String>>> createFunctionByCommand() {
		Map<Command, Consumer<List<String>>> functionByCommand = new EnumMap<>(Command.class);
		functionByCommand.put(Command.START, command -> start());
		functionByCommand.put(Command.MOVE, command -> move(command.get(1), command.get(2)));
		functionByCommand.put(Command.STATUS, command -> executeStatus());
		functionByCommand.put(Command.END, command -> end());

		return functionByCommand;
	}

	public static void main(String[] args) {
		InputView.printCommandGuide();
		while(!chessController.isFinish()) {
			List<String> userInput = InputView.requestCommand();
			Command command = Command.find(userInput.get(COMMAND_INDEX));
			functionByCommand.get(command).accept(userInput);
		}
	}

	private static void start() {
		OutputView.printBoard(chessController.start());
	}

	private static void end() {
		OutputView.printWinner(chessController.end());
	}

	private static void move(String source, String target) {
		OutputView.printBoard(chessController.processMove(source, target));
	}

	private static void executeStatus() {
		OutputView.printScore(chessController.processStatus());
	}
}
