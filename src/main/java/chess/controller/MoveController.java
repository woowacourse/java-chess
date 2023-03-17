package chess.controller;

import chess.domain.Board;
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
	public Board run(final CommandDto commandDto, final Board board) {
		if (controllerState == ControllerState.RUNNABLE) {
			return move(commandDto, board);
		}
		throw new IllegalStateException(CANNOT_MOVE_PIECE_ERROR_MESSAGE);
	}

	private Board move(final CommandDto commandDto, final Board board) {
		Position source = commandDto.getSourcePosition();
		Position target = commandDto.getTargetPosition();
		ExceptionHandler.tryCatchStrategy(() -> {
			board.movePiece(source, target);
			OutputView.printBoard(OutputRenderer.toBoardDto(board.getBoard()));
		});
		return board;
	}
}
