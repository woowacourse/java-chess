package chess.controller;

import chess.domain.Board;
import chess.view.CommandDto;
import chess.view.OutputRenderer;
import chess.view.OutputView;

public class StartController extends Controller {

	private final static String CANNOT_INITIALIZE_GAME_ERROR_MESSAGE = "체스 게임을 초기화할 수 없는 상태입니다.";

	public StartController(final MainController mainController) {
		super(mainController, ControllerState.RUNNABLE);
	}

	@Override
	public Board run(final CommandDto commandDto, final Board board) {
		if (controllerState == ControllerState.RUNNABLE) {
			controllerState = ControllerState.UNAVAILABLE;
			Board newBoard = initBoard();
			OutputView.printBoard(OutputRenderer.toBoardDto(newBoard.getBoard()));
			return newBoard;
		}
		throw new IllegalStateException(CANNOT_INITIALIZE_GAME_ERROR_MESSAGE);
	}

	public Board initBoard() {
		mainController.enableMoveControllerState();
		return Board.create();
	}
}
