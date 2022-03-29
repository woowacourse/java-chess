package chess;

import chess.controller.ChessController;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Application {

	private static final ChessController chessController = new ChessController();
	private static final int COMMAND_INDEX = 0;
	public static final int SOURCE_INDEX = 1;
	public static final int TARGET_INDEX = 2;

	private static Map<Command, Runnable> functionByCommand = createFunctionByCommand();

	private static Map<Command, Runnable> createFunctionByCommand() {
		Map<Command, Runnable> functionByCommand = new EnumMap<>(Command.class);
		functionByCommand.put(Command.START, Application::start);
		functionByCommand.put(Command.STATUS, Application::executeStatus);
		functionByCommand.put(Command.END, Application::end);
		return functionByCommand;
	}

	public static void main(String[] args) {
		InputView.printCommandGuide();
		while(!chessController.isFinish()) {
			processCommand();
		}
	}

	private static void processCommand() {
		List<String> userInput = InputView.requestCommand();
		Command command = Command.find(userInput.get(COMMAND_INDEX));
		if (command.isMove()) {
			move(userInput.get(SOURCE_INDEX), userInput.get(TARGET_INDEX));
			return;
		}
		functionByCommand.get(command).run();
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
