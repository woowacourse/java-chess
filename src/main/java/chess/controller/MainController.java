package chess.controller;

import java.util.Map;

import chess.service.ChessService;
import chess.view.Command;
import chess.view.CommandDto;
import chess.view.InputRenderer;
import chess.view.InputView;
import chess.view.OutputView;

public class MainController {

	private final Map<Command, Controller> controllers;
	private ControllerState controllerState;

	public MainController() {
		controllers = Map.of(
			Command.START, new StartController(this),
			Command.MOVE, new MoveController(this),
			Command.END, new EndController(this));
		controllerState = ControllerState.RUNNABLE;
	}

	public void run() {
		OutputView.printInitialMessage();
		ChessService service = ChessService.temp();
		do {
			ChessService effectivelyFinalService = service;
			service = ExceptionHandler.RetryIfThrowsException(() -> {
				CommandDto commandDto = readCommand();
				Controller currentController = controllers.get(commandDto.getCommand());
				return currentController.run(commandDto, effectivelyFinalService);
			});
		} while (controllerState == ControllerState.RUNNABLE);
	}

	private CommandDto readCommand() {
		return ExceptionHandler.RetryIfThrowsException(() ->
			InputRenderer.toCommandDto(InputView.readCommand()));
	}

	public void disableState() {
		this.controllerState = ControllerState.UNAVAILABLE;
	}

	public void enableMoveControllerState() {
		Controller moveController = controllers.get(Command.MOVE);
		moveController.enableState();
	}
}
