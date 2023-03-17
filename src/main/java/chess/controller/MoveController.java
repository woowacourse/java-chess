package chess.controller;

import chess.service.ChessService;
import chess.domain.Position;
import chess.view.CommandDto;
import chess.view.OutputRenderer;
import chess.view.OutputView;

public class MoveController extends Controller {

	private final static String CANNOT_MOVE_PIECE_ERROR_MESSAGE = "체스 말을 이동할 수 없는 상태입니다.";

	public MoveController(final MainController mainController) {
		super(mainController, ControllerState.UNAVAILABLE);
	}

	@Override
	public ChessService run(final CommandDto commandDto, final ChessService service) {
		if (controllerState == ControllerState.RUNNABLE) {
			return move(commandDto, service);
		}
		throw new IllegalStateException(CANNOT_MOVE_PIECE_ERROR_MESSAGE);
	}

	private ChessService move(final CommandDto commandDto, final ChessService service) {
		Position source = commandDto.getSourcePosition();
		Position target = commandDto.getTargetPosition();
		ExceptionHandler.tryCatchStrategy(() -> {
			service.movePiece(source, target);
			OutputView.printBoard(OutputRenderer.toBoardDto(service.getBoard()));
		});
		return service;
	}
}
