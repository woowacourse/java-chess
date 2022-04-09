package chess;

import chess.controller.ConsoleController;
import chess.domain.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Application {

	private static final ConsoleController consoleController = new ConsoleController();
	private static final int COMMAND_INDEX = 0;
	private static final int SOURCE_INDEX = 1;
	private static final int TARGET_INDEX = 2;

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
		while(!consoleController.isFinish() && !consoleController.isKingDeath()) {
			processCommand();
		}
		if (consoleController.isKingDeath()) {
			OutputView.printWinner(consoleController.getCurrentWinner());
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
		OutputView.printBoard(consoleController.start());
	}

	private static void end() {
		consoleController.end();
	}

	private static void move(String source, String target) {
		OutputView.printBoard(consoleController.processMove(source, target));
	}

	private static void executeStatus() {
		OutputView.printScore(consoleController.processStatus());
	}
}
