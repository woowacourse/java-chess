package chess.controller;

import chess.service.ChessService;
import chess.view.CommandDto;
import chess.view.OutputRenderer;
import chess.view.OutputView;

public class StartController extends Controller {

	private final static String CANNOT_INITIALIZE_GAME_ERROR_MESSAGE = "체스 게임을 초기화할 수 없는 상태입니다.";

	public StartController(final MainController mainController) {
		super(mainController, ControllerState.RUNNABLE);
	}

	@Override
	public ChessService run(final CommandDto commandDto, final ChessService service) {
		if (controllerState == ControllerState.RUNNABLE) {
			controllerState = ControllerState.UNAVAILABLE;
			ChessService newService = initializeChess();
			OutputView.printBoard(OutputRenderer.toBoardDto(newService.getBoard()));
			return newService;
		}
		throw new IllegalStateException(CANNOT_INITIALIZE_GAME_ERROR_MESSAGE);
	}

	private ChessService initializeChess() {
		mainController.enableMoveControllerState();
		return ChessService.create();
	}
}
