package chess.controller;

import chess.service.ChessService;
import chess.view.CommandDto;

public class EndController extends Controller {

	public EndController(final MainController mainController) {
		super(mainController, ControllerState.RUNNABLE);
	}

	@Override
	public ChessService run(final CommandDto commandDto, final ChessService service) {
		if (controllerState == ControllerState.RUNNABLE) {
			controllerState = ControllerState.UNAVAILABLE;
		}
		mainController.disableState();
		return service;
	}
}
