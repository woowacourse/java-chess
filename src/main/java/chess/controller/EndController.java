package chess.controller;

import chess.domain.Board;
import chess.view.CommandDto;

public class EndController extends Controller {

	public EndController(final MainController mainController) {
		super(mainController, ControllerState.RUNNABLE);
	}

	@Override
	public Board run(final CommandDto commandDto, final Board board) {
		if (controllerState == ControllerState.RUNNABLE) {
			controllerState = ControllerState.UNAVAILABLE;
		}
		mainController.disableState();
		return board;
	}
}
